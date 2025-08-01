package loja.api.ControllerApi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import loja.api.dto.CategoryDto;

import loja.api.model.Category;
import loja.api.model.Dto.CategoryUpdateDto;
import loja.api.services.CategoryService;
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
@RequestMapping("category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/saveCategory")
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody CategoryDto categoryDto){
         Category newCategory = categoryService.saveCategory(categoryDto);
        if (newCategory != null) {
            URI location = URI.create("/produtos/" + newCategory.getId());
            return ResponseEntity.created(location).body(newCategory);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/findById")
    public ResponseEntity<Category> findById(@RequestParam Long id){
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Category>> findByName(@RequestParam String name){
        return ResponseEntity.ok().body(categoryService.findByNameContaining(name));
    }

    @GetMapping()
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @PatchMapping()
    public ResponseEntity<Map<String,Boolean>> updateCategory(@RequestParam Long id, @RequestBody CategoryUpdateDto categoryUpdateDto){
        return ResponseEntity.ok().body(categoryService.updateCategory(id, categoryUpdateDto));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteLogicCategory(@RequestBody @NotNull Long id){
        return ResponseEntity.ok().body(categoryService.deleteLogicCategory(id));
    }
}
