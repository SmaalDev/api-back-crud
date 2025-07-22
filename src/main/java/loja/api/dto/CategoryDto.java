package loja.api.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public record CategoryDto(
        @NotBlank
        String name,
        @NotBlank
        Map<String, String> fields
) {
}
