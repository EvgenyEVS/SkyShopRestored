package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXPRICE = 999;

    public FixPriceProduct(String nameProduct) {
        super(nameProduct);
    }

    @Override
    public int getPrice() {
        return FIXPRICE;
    }

    @Override
    public boolean isSpecial(Product product) {
        return true;
    }

    @Override
    public String toString() {
        return "<" + getName() + ">:" + "<" + "Фиксированная цена " + FIXPRICE + ">";

    }

    @Override
    public UUID getId() {
        return id;
    }
}
