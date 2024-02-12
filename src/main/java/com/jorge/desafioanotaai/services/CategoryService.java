package com.jorge.desafioanotaai.services;

import com.jorge.desafioanotaai.domain.category.Category;
import com.jorge.desafioanotaai.domain.category.CategoryDTO;
import com.jorge.desafioanotaai.domain.category.exceptions.CategoryNotFoundException;
import com.jorge.desafioanotaai.repositories.CategoryRepository;
import com.jorge.desafioanotaai.services.aws.AwsSnsService;
import com.jorge.desafioanotaai.services.aws.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repository;
    private final AwsSnsService snsService;


    public CategoryService(CategoryRepository repository, AwsSnsService snsService) {
        this.repository = repository;
        this.snsService = snsService;
    }

    public Category insert(CategoryDTO categoryData){
        Category newCategory = new Category(categoryData);
        this.repository.save(newCategory);

        this.snsService.publish(new MessageDTO(newCategory.toString())); // send all product information

        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryData){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if (!categoryData.title().isEmpty()) category.setTitle(categoryData.title());
        if (!categoryData.description().isEmpty()) category.setDescription(categoryData.description());
        if (!categoryData.ownerId().isEmpty()) category.setOwnerId(categoryData.ownerId());

        this.snsService.publish(new MessageDTO(category.toString())); // send all product information

        this.repository.save(category);
        return category;
    }

    public void delete(String id){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        this.repository.delete(category);
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Optional<Category> getById(String id){
        return this.repository.findById(id);
    }
}
