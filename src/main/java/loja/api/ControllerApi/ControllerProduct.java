package loja.api.ControllerApi;

import loja.api.model.Product;
import loja.api.dto.ProductDto;
import loja.api.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("product")
public class ControllerProduct {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<Product> cadastrarProduct(@RequestBody ProductDto productDto){
        Product newProduct = productService.salvedProduct(productDto);
        if(newProduct != null){
            URI location = URI.create("/produtos/" + newProduct.getId());
            return ResponseEntity.created(location).body(newProduct);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductId(@PathVariable Long id){
        return ResponseEntity.ok().body( productService.findById(id));
    }

    @GetMapping("/contain/{name}")
    public ResponseEntity<List<Product>> findProductId(@PathVariable String name){
        List<Product> productList = productService.findByNameContainingIgnoreCase(name);
        return ResponseEntity.ok().body(productList);
    }

    @GetMapping("/find-by-category/{category}")
    public ResponseEntity<List<Product>> findProductCategory(@PathVariable String category){
        List<Product> productList = productService.findByCategory(category.toUpperCase());
        return ResponseEntity.ok().body(productList);
    }

}
