package loja.api.model;

import jakarta.persistence.*;
import loja.api.dto.CategoryDto;
import lombok.*;

@Table(name = "CATEGORY", schema = "business")
@Entity(name = "Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Column(columnDefinition = "json")
    private String fields;
    Boolean active;

    public Category(CategoryDto categoryDto){
        this.name = categoryDto.name();
        this.fields = categoryDto.fields().toString();
    }
}
