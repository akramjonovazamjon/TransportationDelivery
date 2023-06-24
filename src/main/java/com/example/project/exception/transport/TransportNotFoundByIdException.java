package com.example.project.exception.transport;

import lombok.Getter;

@Getter
public class TransportNotFoundByIdException extends RuntimeException {
    private final Long id;

    public TransportNotFoundByIdException(Long id) {
        super("error.not_found.transport.by_id");
        this.id = id;
    }
}
