package loja.api.services;

import loja.api.dto.CategoryDto;
import loja.api.model.Category;
import loja.api.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    Category saveCategory(CategoryDto categoryDto){
        return categoryRepository.save(new Category(categoryDto));
    }
}
