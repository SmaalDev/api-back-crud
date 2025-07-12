package loja.api.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class BusinessException extends EntityNotFoundException {
    public BusinessException(String message) {
        super(message);
    }
}
