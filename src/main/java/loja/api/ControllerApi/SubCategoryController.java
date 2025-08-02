package loja.api.ControllerApi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import loja.api.dto.SubCategoryDto;

import loja.api.model.SubCategory;
import loja.api.dto.SubCategoryUpdateDto;
import loja.api.services.SubCategoryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@RestController
@RequestMapping("subCategory")
public class SubCategoryController {
    @Autowired
    SubCategoryService subCategoryService;

    @PostMapping()
    public ResponseEntity<SubCategory> saveCategory(@Valid @RequestBody SubCategoryDto subCategoryDto){
         SubCategory newSubCategory = subCategoryService.saveCategory(subCategoryDto);
        if (newSubCategory != null) {
            URI location = URI.create("/produtos/" + newSubCategory.getId());
            return ResponseEntity.created(location).body(newSubCategory);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/findById")
    public ResponseEntity<SubCategory> findById(@RequestParam Long id){
        return ResponseEntity.ok().body(subCategoryService.findById(id));
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<SubCategory>> findByName(@RequestParam String name){
        return ResponseEntity.ok().body(subCategoryService.findByNameContaining(name));
    }

    @GetMapping()
    public ResponseEntity<List<SubCategory>> findAll(){
        return ResponseEntity.ok().body(subCategoryService.findAll());
    }

    @PatchMapping()
    public ResponseEntity<Map<String,Boolean>> updateCategory(@RequestParam Long id, @RequestBody SubCategoryUpdateDto subCategoryUpdateDto){
        return ResponseEntity.ok().body(subCategoryService.updateCategory(id, subCategoryUpdateDto));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteLogicCategory(@RequestBody @NotNull Long id){
        return ResponseEntity.ok().body(subCategoryService.deleteLogicCategory(id));
    }
}
