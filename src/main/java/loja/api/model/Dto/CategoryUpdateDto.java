package loja.api.model.Dto;

import java.util.ArrayList;

public record CategoryUpdateDto(
        String name,
        ArrayList<String> fields
) {
}
