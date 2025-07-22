package loja.api.model;

import jakarta.persistence.*;
import loja.api.dto.CategoryDto;
import loja.api.services.MapToJsonConverter;
import lombok.*;

import java.util.Map;

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
    @Column(columnDefinition = "TEXT")
    @Convert(converter = MapToJsonConverter.class)
    Fields fields;

    public Category(CategoryDto categoryDto){
        this.name = categoryDto.name();
        this.fields = new Fields(categoryDto.fields());
    }
}
