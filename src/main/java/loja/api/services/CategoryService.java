package loja.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import loja.api.dto.CategoryDto;
import loja.api.exceptions.CategoryException;
import loja.api.model.Category;
import loja.api.model.Dto.CategoryUpdateDto;
import loja.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static loja.api.enums.CategoryExceptionMessage.EXISTING_CATEGORY;
import static loja.api.enums.CategoryExceptionMessage.ID_NON_EXISTENT;
import static loja.api.enums.Exceptionmessage.NONE_PROD_CAD;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    ObjectMapper mapper = new ObjectMapper();

    public Category saveCategory(CategoryDto categoryDto){
        List<Category> categories = findByNameIgnoreCase(categoryDto.name());
        if(categories.isEmpty()){
            return categoryRepository.save(new Category(categoryDto));
        }else{
            throw new CategoryException(EXISTING_CATEGORY.getMessage() + categoryDto.name());
        }
    }

    public Category findById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ID_NON_EXISTENT.getMessage() + id));
    }

    public List<Category> findAll(){
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()){
            throw new CategoryException(NONE_PROD_CAD.getMessage());
        }
        return categories;
    }

    public List<Category> findByNameIgnoreCase(String name){
        return categoryRepository.findByNameIgnoreCase(name);
    }

    public List<Category> findByNameContaining(String name){
        return categoryRepository.findByNameContainingIgnoreCase(name);
    }

    public String convertMapToString(Map<String,String> fields){
        try{
            return mapper.writeValueAsString(fields);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public Map convertStringToMap(String json){
        try{
            return mapper.readValue(json, Map.class);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String deleteLogicCategory(Long id) {
        Category newCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ID_NON_EXISTENT.getMessage() ));
        newCategory.setActive(false);
        return newCategory.getName() + " logicamente excluída";
    }

    @Transactional
    public Map<String, Boolean> updateCategory(Long id, CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ID_NON_EXISTENT.getMessage() + id));
        Map<String, Boolean> updateFields = new HashMap<>();

        if(categoryUpdateDto.name(). != null) {
            if (categoryUpdateDto.name().equals(category.getName())){
                category.setName(categoryUpdateDto.name());
                updateFields.put("name", true);
            }else{
                updateFields.put("name", false);
            }
        }
        if(categoryUpdateDto.fields() != null) {
            if (categoryUpdateDto.fields().equals(category.getFields())){
                category.setFields(categoryUpdateDto.fields());
                updateFields.put("fields", true);
            }else{
                updateFields.put("fields", false);
            }
        }

        return updateFields;
    }
}
