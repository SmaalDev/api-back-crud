package loja.api.ControllerApi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import loja.api.dto.CategoryDto;

import loja.api.model.Category;
import loja.api.services.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<Category> fyndById(Long id){
        return ResponseEntity.ok().body(categoryService.findById(id));
    }

    @GetMapping()
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok().body(categoryService.findAll());
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteLogicCategory(@RequestBody @NotNull Long id){
        return ResponseEntity.ok().body(categoryService.deleteLogicCategory(id));
    }

}
