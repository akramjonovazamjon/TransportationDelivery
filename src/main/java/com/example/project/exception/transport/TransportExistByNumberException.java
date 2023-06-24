package com.example.project.exception.transport;

import lombok.Getter;

@Getter
public class TransportExistByNumberException extends RuntimeException {
    private final String number;

    public TransportExistByNumberException(String number) {
        super("error.duplicate.transport.by_number");
        this.number = number;
    }
}
