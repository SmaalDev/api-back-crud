package loja.api.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import loja.api.dto.ProductDto;
import loja.api.enums.Category;
import lombok.*;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
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
    @Column(nullable = false)
    private Boolean active;

    public Product(ProductDto produtoDto) {
        this.name = produtoDto.name();
        this.price = produtoDto.price();
        this.description = produtoDto.description();
        this.category = produtoDto.category();
        this.active = produtoDto.active();
    }

    public Long getId() {
        return id;
    }
}
