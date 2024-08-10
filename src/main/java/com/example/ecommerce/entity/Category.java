package com.example.ecommerce.entity;

import com.example.ecommerce.dto.response.CategoryResponse;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Lob
    String description;

    public CategoryResponse getCategoryResponse(){
        return CategoryResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .build();
    }

}
