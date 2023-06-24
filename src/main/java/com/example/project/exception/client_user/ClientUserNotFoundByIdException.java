package com.example.project.exception.client_user;

import lombok.Getter;

@Getter
public class ClientUserNotFoundByIdException extends RuntimeException {
    private final Long id;

    public ClientUserNotFoundByIdException(Long id) {
        super("error.not_found.client_user.by_id");
        this.id = id;
    }
}
