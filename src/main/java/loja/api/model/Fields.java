package loja.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import loja.api.services.MapToJsonConverter;

import java.util.Map;

public class Fields {
    MapToJsonConverter mapToJsonConverter;
    String fields;

    public Fields(@NotBlank Map<String, String> fields) {
        this.fields = mapToJsonConverter.convertToDatabaseColumn(fields);
    }
}
