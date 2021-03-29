package com.entreprise.demo.exception;
import lombok.Data;


@Data
public class ValidationException extends RuntimeException {

    public ValidationException(String msg) {
        super(msg);
    }
}
