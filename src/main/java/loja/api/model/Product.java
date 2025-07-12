package loja.api.model;

import jakarta.persistence.*;
import loja.api.dto.ProductDto;
import loja.api.enums.Category;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Product(ProductDto produtoDto) {
        this.name = produtoDto.name();
        this.price = produtoDto.price();
        this.description = produtoDto.description();
        this.category = produtoDto.category();
    }

    public Long getId() {
        return id;
    }
}
