package loja.api.ControllerApi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import loja.api.model.Product;
import loja.api.dto.ProductDto;
import loja.api.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ControllerProduct {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> cadastrarProduct(@RequestBody @Valid ProductDto productDto) {
        Product newProduct = productService.salvedProduct(productDto);
        if (newProduct != null) {
            URI location = URI.create("/produtos/" + newProduct.getId());
            return ResponseEntity.created(location).body(newProduct);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductId(@PathVariable @NotBlank Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping("/contain/{name}")
    public ResponseEntity<List<Product>> findProductId(@PathVariable @NotBlank String name) {
        List<Product> productList = productService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok().body(productList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable @NotNull Long id,
            @RequestBody @Valid ProductDto productDto
    ) {
        productService.updateProduct(id, productDto);
        return ResponseEntity.ok().body(productDto.name() + " atualizado.");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updatePartialProduct(@PathVariable Long id,
                                                       @RequestBody Map<String, Object> fields) {
        String status = productService.updatePartialProduct(id, fields);
        return ResponseEntity.ok().body(status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        String message = productService.deleteById(id);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/delete-logic/{id}")
    public ResponseEntity<String> deleteLogicProduct(@PathVariable Long id){
        String message = productService.deleteLogicProduct(id);
        return ResponseEntity.ok().body(message);
    }
}