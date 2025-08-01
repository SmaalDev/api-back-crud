package loja.api.model;

import jakarta.persistence.*;
import loja.api.dto.ProductDto;

import lombok.*;



import java.util.Map;

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
    private Integer subCategory;
    @Column(nullable = false)
    private Boolean status;
    private String marca;
    @Column(columnDefinition = "json")
    private String fields;

    public Product(ProductDto produtoDto, String fields) {
        this.name = produtoDto.name();
        this.price = produtoDto.price();
        this.description = produtoDto.description();
        this.status = produtoDto.status();
        this.fields = fields;
    }
}
