package com.example.ecommerce.dto.response;

import com.example.ecommerce.enums.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long id;
    String name;
    String email;
    String password;
    UserRole role;
}
