package com.example.project.exception.delivery;

import lombok.Getter;

@Getter
public class DeliveryNotFoundByIdException extends RuntimeException {
    private final Long id;

    public DeliveryNotFoundByIdException(Long id) {
        super("error.not_found.delivery.by_id");
        this.id = id;
    }
}
