package loja.api.services;

import jakarta.transaction.Transactional;
import loja.api.dto.ProductDto;
import loja.api.enums.Category;
import loja.api.exceptions.BusinessException;
import loja.api.model.Product;
import loja.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static loja.api.enums.Exceptionmessage.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Transactional
    public Product salvedProduct(ProductDto productDto){
        return repository.save(new Product(productDto));
    }

    public Product findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new BusinessException(ID_NON_EXISTENT.getMessage() + id));
    }

    public List<Product> findAll(){
        List<Product> productList = repository.findAll();
        if(productList.isEmpty()){
            throw new BusinessException(NONE_PROD_CAD.getMessage());
        }
        return productList;
    }

    public List<Product> findByNameContainingIgnoreCase(String name){
        return repository.findByNameContainingIgnoreCase(name);
    }

    public List<Product> findByCategory(String category) {
        try {
            Category categoryValid = Category.valueOf(category.toUpperCase());
            List<Product> productList = repository.findByCategory(categoryValid);
            if(productList.isEmpty()){
                throw new BusinessException(NONE_PROD_CAT.getMessage() + category);
            }
            return productList;
        }catch (Exception e){
            throw new BusinessException("Categoria não existe.");
        }

    }
}
