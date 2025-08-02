package loja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubCategoryDto(
        @NotBlank
        String name,
        @NotNull
        Long idCategory
) {
}
