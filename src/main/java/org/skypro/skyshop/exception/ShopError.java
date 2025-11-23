package org.skypro.skyshop.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class ShopError {
    private final String code;
    private final String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
