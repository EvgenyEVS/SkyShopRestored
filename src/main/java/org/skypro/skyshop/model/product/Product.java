package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final String nameProduct;
    protected final UUID id;


    public Product(String nameProduct) {
        isCorrectName(nameProduct);
        this.nameProduct = nameProduct;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return nameProduct;
    }

    public abstract int getPrice();

    public abstract boolean isSpecial(Product product);

    public static void isCorrectName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Строка пустая. Заполните наименование продукта.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameProduct);
    }

    @Override
    public String searchTerm() {
        return nameProduct;
    }

    @Override
    public String typeContent() {
        return "PRODUCT";
    }

    @Override
    public String getSearchableName() {
        return getName();

    }

}
