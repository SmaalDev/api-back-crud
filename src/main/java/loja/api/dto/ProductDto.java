package loja.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record ProductDto(
        @NotBlank(message = "O campo 'name' não pode estar em branco")
        String name,
        @NotNull
        Double price,
        String description,
        @NotNull
        String category,
        Boolean status,
        String mark,
        Map<String,String> fields
) { }
