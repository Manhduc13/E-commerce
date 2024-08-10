package com.example.ecommerce.service.admin;

import com.example.ecommerce.dto.request.SearchProductRequest;
import com.example.ecommerce.dto.response.ProductResponse;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.AppException;
import com.example.ecommerce.exception.ErrorCode;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProductService {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    public ProductResponse addProduct(ProductResponse request) throws IOException {

        log.info("Adding product with name: {}", request.getName());
        log.info("Category ID: {}", request.getCategory_id());

       Category category = categoryRepository.findById(request.getCategory_id()).orElseThrow(() -> new AppException(ErrorCode.CATE_NOT_FOUND));

       Product product = Product.builder()
               .name(request.getName())
               .price(request.getPrice())
               .description(request.getDescription())
               .image(request.getImage().getBytes())
               .category(category)
               .build();

       Product newProduct = productRepository.save(product);

       return newProduct.getProductResponse();
    }

    public List<ProductResponse> getAllProduct(){
        return productRepository.findAll().stream().map(Product::getProductResponse).toList();
    }

    public List<ProductResponse> findAllByNameContaining(String name){
        return productRepository.findAllByNameContaining(name).stream().map(Product::getProductResponse).toList();
    }

//    public List<ProductResponse> searchProduct(SearchProductRequest request){
//        Category category = categoryRepository.findByName(request.getCategoryName()).orElseThrow(() -> new AppException(ErrorCode.CATE_NOT_FOUND));
//
//        Product product = Product.builder()
//                .name(request.getProductName())
//                .category(category)
//                .build();
//
//
//        ExampleMatcher exampleMatcher =ExampleMatcher.matchingAll()
//                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
//                .withMatcher("")
//    }
}
