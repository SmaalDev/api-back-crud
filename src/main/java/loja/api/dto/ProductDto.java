package loja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import loja.api.enums.Category;

public record ProductDto(
        @NotBlank(message = "O campo 'name' não pode estar em branco")
        String name,
        @NotNull
        Double price,
        String description,
        @NotNull
        Category category,
        Boolean active
) { }
