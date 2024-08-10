package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.AuthenticationRequest;
import com.example.ecommerce.dto.request.SignUpRequest;
import com.example.ecommerce.dto.response.AuthenticationResponse;
import com.example.ecommerce.dto.response.CategoryResponse;
import com.example.ecommerce.dto.response.ProductResponse;
import com.example.ecommerce.dto.response.UserResponse;
import com.example.ecommerce.service.admin.CategoryService;
import com.example.ecommerce.service.admin.ProductService;
import com.example.ecommerce.service.auth.AuthService;
import com.nimbusds.jose.KeyLengthException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {

    AuthService authService;
    ProductService productService;
    CategoryService categoryService;

    @PostMapping("/login")
    public AuthenticationResponse signin(@RequestBody AuthenticationRequest request) throws KeyLengthException {
        return authService.signin(request);
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody SignUpRequest request){
        return authService.createUser(request);
    }

    @GetMapping("/product")
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/category")
    public List<CategoryResponse> getAllCategory(){
        return categoryService.getAllCategory();
    }

    @GetMapping("/search/{name}")
    public List<ProductResponse> findAllByName(@PathVariable String name){
        return productService.findAllByNameContaining(name);
    }

}
