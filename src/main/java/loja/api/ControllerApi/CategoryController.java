package loja.api.ControllerApi;

import jakarta.validation.Valid;
import loja.api.dto.CategoryDto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {
    //ResponseEntity<String> registerCategory(@Valid CategoryDto categoryDto){}
}
