package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int price;

    public SimpleProduct() {
        super(" - ");
        this.price = 0;
    }

    public SimpleProduct(String nameProduct, int price) {
        super(nameProduct);
        if (price <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть больше 0.");
        }
        this.price = price;
    }


    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public boolean isSpecial(Product product) {
        return false;
    }

    @Override
    public String toString() {
        return "<" + getName() + ">: " + "<" + price + ">";
    }

    @Override
    public UUID getId() {
        return id;
    }
}
