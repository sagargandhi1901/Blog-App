package com.sagar.blog.service.impl;

import com.sagar.blog.exception.ResourceNotFoundException;
import com.sagar.blog.model.Category;
import com.sagar.blog.payload.CategoryDTO;
import com.sagar.blog.repository.CategoryRepository;
import com.sagar.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.sagar.blog.constants.ApiConstant.CATEGORY;
import static com.sagar.blog.constants.ApiConstant.ID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        Category addedCategory = this.repository.save(category);
        return this.modelMapper.map(addedCategory, CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
        Category retrivedCategory = this.repository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
        retrivedCategory.setCategoryTitle(categoryDTO.getCategoryTitle());
        retrivedCategory.setCategoryDescription(categoryDTO.getCategoryDescription());

        Category updatedCategory = this.repository.save(retrivedCategory);
        return this.modelMapper.map(updatedCategory, CategoryDTO.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category retrivedCategory = this.repository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
        this.repository.delete(retrivedCategory);
    }

    @Override
    public CategoryDTO getCategory(Integer categoryId) {
        Category retrivedCategory = this.repository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException(CATEGORY, ID, categoryId));
        return this.modelMapper.map(retrivedCategory, CategoryDTO.class);
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.repository.findAll();
        return categories.stream().map(
                (category) -> this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }
}
