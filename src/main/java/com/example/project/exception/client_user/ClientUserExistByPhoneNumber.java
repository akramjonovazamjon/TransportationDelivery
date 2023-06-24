package com.example.project.exception.client_user;

import lombok.Getter;

@Getter
public class ClientUserExistByPhoneNumber extends RuntimeException {
    private final String phoneNumber;

    public ClientUserExistByPhoneNumber(String phoneNumber) {
        super("error.duplicate.client_user.by_phone_number");
        this.phoneNumber = phoneNumber;
    }
}
