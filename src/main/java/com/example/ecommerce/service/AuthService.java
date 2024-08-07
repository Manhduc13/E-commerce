package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.AuthenticationRequest;
import com.example.ecommerce.dto.request.SignUpRequest;
import com.example.ecommerce.dto.response.AuthenticationResponse;
import com.example.ecommerce.dto.response.UserResponse;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.UserRole;
import com.example.ecommerce.exception.AppException;
import com.example.ecommerce.exception.ErrorCode;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.utils.JwtUtils;
import com.nimbusds.jose.KeyLengthException;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthService {

    UserRepository userRepository;
    UserService userService;
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;

    @PostConstruct
    public void createAdminAccount(){
        User user = userRepository.findByRole(UserRole.ADMIN);

        if(user == null){
            User admin = User.builder()
                    .email("admin@gmail.com")
                    .name("admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .role(UserRole.ADMIN)
                    .build();
            userRepository.save(admin);
            log.info("An admin account has been created with default info (email/password: admin@gmail.com/admin");
        }
    }

    public AuthenticationResponse signin(AuthenticationRequest request) throws KeyLengthException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(request.getEmail());
        User user = userRepository.findFirstByEmail(userDetails.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        final String jwt = jwtUtils.generateToken(userDetails);

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .role(user.getRole())
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public UserResponse createUser (SignUpRequest request){

        if(existedEmail(request.getEmail())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .role(UserRole.CUSTOMER)
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .build();

        User createdUser = userRepository.save(user);

        return UserResponse.builder()
                .id(createdUser.getId())
                .email(createdUser.getEmail())
                .name(createdUser.getName())
                .role(createdUser.getRole())
                .build();
    }

    public boolean existedEmail(String email){
        return userRepository.findFirstByEmail(email).isPresent();
    }
}
