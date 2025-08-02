package loja.api.model;

import jakarta.persistence.*;
import loja.api.dto.SubCategoryDto;
import lombok.*;

@Table(name = "sub_category", schema = "business")
@Entity(name = "subCategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "ID")
    private Long id;
    private String name;
    @Column(name = "CATEGORY_ID")
    private Long idCategory;
    @Column(nullable = false)
    private Boolean active = true;

    public SubCategory(SubCategoryDto subCategoryDto){
        this.name = subCategoryDto.name();
        this.idCategory = subCategoryDto.idCategory();
    }
}
