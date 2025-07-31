package loja.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CategoryDto(
        @NotBlank
        String name,
        List<String> fields
) {
}
