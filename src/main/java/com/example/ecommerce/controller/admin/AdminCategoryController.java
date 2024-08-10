package com.example.ecommerce.controller.admin;

import com.example.ecommerce.dto.response.CategoryResponse;
import com.example.ecommerce.service.admin.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminCategoryController {

    CategoryService categoryService;

    @PostMapping("/category")
    public CategoryResponse addCategory(@RequestBody CategoryResponse request){
        return categoryService.addCategory(request);
    }

}
