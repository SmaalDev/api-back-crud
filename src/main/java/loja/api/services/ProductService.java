package loja.api.services;

import jakarta.transaction.Transactional;
import loja.api.dto.ProductDto;
import loja.api.exceptions.BusinessException;
import loja.api.model.Product;
import loja.api.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @Transactional
    public void updateProduct (Long id, ProductDto productDto){
        Product product = repository.findById(id)
                .orElseThrow(() -> new BusinessException(ID_NON_EXISTENT.getMessage() + id));
        StringBuilder status = new StringBuilder("Os campos: ");

        product.setName(productDto.name());
        product.setPrice(productDto.price());
        product.setDescription(productDto.description());
        product.setActive(productDto.active());
    }

    @Transactional
    public String updatePartialProduct(Long id, Map<String, Object> fields) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new BusinessException(ID_NON_EXISTENT.getMessage() + id));

        StringBuilder status = new StringBuilder();

        fields.forEach((key, value) -> {
            switch (key) {
                case "name" -> {
                    if (!Objects.equals(product.getName(), value)) {
                        product.setName((String) value);
                    } else {
                        status.append(" \"name\" ");
                    }
                }
                case "price" -> {
                    Double newPrice = Double.valueOf(value.toString());
                    if (!Objects.equals(product.getPrice(), newPrice)) {
                        product.setPrice(newPrice);
                    } else {
                        status.append(" \"price\" ");
                    }
                }
                case "description" -> {
                    if (!Objects.equals(product.getDescription(), value.toString())) {
                        product.setDescription(value.toString());
                    } else {
                        status.append(" \"description\" ");
                    }
                }
                case "active" -> {
                    Boolean newActive = Boolean.valueOf(value.toString());
                    if (!Objects.equals(product.getActive(), newActive)) {
                        product.setActive(newActive);
                    } else {
                        status.append(" \"active\" ");
                    }
                }
                default -> throw new BusinessException(CAMP_NON_EXISTENT.getMessage() + key);
            }
        });

        if (!status.equals("Os campos: ")) {
            status.append("estão semelhante ao cadastrado e por isso não sofreram alteração");
        }
        else{
            status.setLength(0);
            status.append("Produto atualizado.");
        }

        return status.toString();
    }

    @Transactional
    public String deleteLogicProduct(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new BusinessException(ID_NON_EXISTENT.getMessage() + id));
        product.setActive(false);
        return product.getName() + " logicamente deletado";
    }

    public String deleteById(Long id){
        Product product = repository.findById(id)
                .orElseThrow(() -> new BusinessException(ID_NON_EXISTENT.getMessage() + id));
        repository.deleteById(id);
        return product.getName() + " deletado";
    }
}
