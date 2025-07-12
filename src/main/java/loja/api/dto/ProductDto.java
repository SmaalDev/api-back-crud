package loja.api.dto;

import loja.api.enums.Category;

public record ProductDto(
        String name,
        Double price,
        String description,
        Category category
) { }
