package com.example.ecommerce.entity;

import com.example.ecommerce.dto.response.ProductResponse;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Lob
    String description;
    Long price;
    @Lob
    @Column(columnDefinition = "longblob")
    byte[] image;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Category category;

    public ProductResponse getProductResponse(){
        return ProductResponse.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .returnImage(image)
                .category_id(category.getId())
                .category_name(category.getName())
                .build();
    }
}
