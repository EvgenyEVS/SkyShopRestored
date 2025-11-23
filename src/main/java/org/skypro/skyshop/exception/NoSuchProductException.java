package org.skypro.skyshop.exception;

import java.util.UUID;

public class NoSuchProductException extends RuntimeException {

    public NoSuchProductException(UUID id) {
        super("Товар с ID " + id + " не существует");
    }

    public NoSuchProductException() {
        super("Такого товара не существует. Проверьте ID");
    }
}
