package com.example.ecommerce.dto.response;

import com.example.ecommerce.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductResponse {
    Long id;
    String name;
    String description;
    Long price;
    Long category_id;
    String category_name;
    byte[] returnImage; // save image to db
    MultipartFile image; // get image from front-end
}
