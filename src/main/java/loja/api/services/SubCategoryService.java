package loja.api.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import loja.api.dto.SubCategoryDto;
import loja.api.exceptions.CategoryException;
import loja.api.model.SubCategory;
import loja.api.dto.SubCategoryUpdateDto;
import loja.api.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static loja.api.enums.CategoryExceptionMessage.EXISTING_CATEGORY;
import static loja.api.enums.CategoryExceptionMessage.ID_NON_EXISTENT;
import static loja.api.enums.Exceptionmessage.NONE_PROD_CAD;

@Service
public class SubCategoryService {
    @Autowired
    SubCategoryRepository subCategoryRepository;
    ObjectMapper mapper = new ObjectMapper();

    public SubCategory saveCategory(SubCategoryDto subCategoryDto){
        List<SubCategory> categories = findByNameIgnoreCase(subCategoryDto.name());
        if(categories.isEmpty()){
            return subCategoryRepository.save(new SubCategory(subCategoryDto));
        }else{
            throw new CategoryException(EXISTING_CATEGORY.getMessage() + subCategoryDto.name());
        }
    }

    public SubCategory findById(Long id){
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ID_NON_EXISTENT.getMessage() + id));
    }

    public List<SubCategory> findAll(){
        List<SubCategory> categories = subCategoryRepository.findAll();

        if (categories.isEmpty()){
            throw new CategoryException(NONE_PROD_CAD.getMessage());
        }
        return categories;
    }

    public List<SubCategory> findByNameIgnoreCase(String name){
        return subCategoryRepository.findByNameIgnoreCase(name);
    }

    public List<SubCategory> findByNameContaining(String name){
        return subCategoryRepository.findByNameContainingIgnoreCase(name);
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
        SubCategory newSubCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ID_NON_EXISTENT.getMessage() ));
        newSubCategory.setActive(false);
        return newSubCategory.getName() + " logicamente excluída";
    }

    @Transactional
    public Map<String, Boolean> updateCategory(Long id, SubCategoryUpdateDto subCategoryUpdateDto) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ID_NON_EXISTENT.getMessage() + id));
        Map<String, Boolean> updateFields = new HashMap<>();

        if(subCategoryUpdateDto.name() != null) {
            if (subCategoryUpdateDto.name().equals(subCategory.getName())){
                subCategory.setName(subCategoryUpdateDto.name());
                updateFields.put("name", true);
            }else{
                updateFields.put("name", false);
            }
        }
        if(subCategoryUpdateDto.idCategory() != null) {
            if (subCategoryUpdateDto.idCategory().equals(subCategory.getIdCategory())){
                subCategory.setIdCategory(subCategoryUpdateDto.idCategory());
                updateFields.put("idCategory", true);
            }else{
                updateFields.put("idCategory", false);
            }
        }

        return updateFields;
    }
}
