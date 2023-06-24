package com.example.project.config;

import com.example.project.dto.ResponseData;
import com.example.project.exception.client_user.ClientUserExistByPhoneNumber;
import com.example.project.exception.client_user.ClientUserNotFoundByIdException;
import com.example.project.exception.delivery.DeliveryNotFoundByIdException;
import com.example.project.exception.transport.TransportExistByNumberException;
import com.example.project.exception.transport.TransportNotFoundByIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ClientUserExistByPhoneNumber.class)
    public ResponseData<Object> handleClientUserExistByPhoneNumber(ClientUserExistByPhoneNumber e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ClientUserNotFoundByIdException.class)
    public ResponseData<Object> handleClientUserNotFoundByIdException(ClientUserNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DeliveryNotFoundByIdException.class)
    public ResponseData<Object> handleDeliveryNotFoundByIdException(DeliveryNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TransportExistByNumberException.class)
    public ResponseData<Object> handleTransportExistByNumberException(TransportExistByNumberException e) {
        return ResponseData.errorOf(e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TransportNotFoundByIdException.class)
    public ResponseData<Object> handleTransportNotFoundByIdException(TransportNotFoundByIdException e) {
        return ResponseData.errorOf(e.getMessage());
    }
}
