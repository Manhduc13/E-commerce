package com.example.ecommerce.controller.admin;

import com.example.ecommerce.dto.response.ProductResponse;
import com.example.ecommerce.service.admin.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AdminProductController {
    ProductService productService;

    @PostMapping("/product")
    public ProductResponse addProduct(@ModelAttribute ProductResponse request) throws IOException {
        log.info("Received request to add product");
        return productService.addProduct(request);
    }

    @GetMapping("/search/{name}")
    public List<ProductResponse> findAllByName(@PathVariable String name){
        return productService.findAllByNameContaining(name);
    }
}
