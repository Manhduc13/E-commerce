package com.example.ecommerce.service.admin;

import com.example.ecommerce.dto.response.CategoryResponse;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.exception.AppException;
import com.example.ecommerce.exception.ErrorCode;
import com.example.ecommerce.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryResponse addCategory(CategoryResponse request){
        if(existedCategory(request.getName())){
            throw new AppException(ErrorCode.CATE_EXISTED);
        }

        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        Category newCate = categoryRepository.save(category);

        return newCate.getCategoryResponse();
    }

    public List<CategoryResponse> getAllCategory(){
        return categoryRepository.findAll().stream().map(Category::getCategoryResponse).toList();
    }

    public boolean existedCategory(String name){
        return categoryRepository.findByName(name).isPresent();
    }
}
