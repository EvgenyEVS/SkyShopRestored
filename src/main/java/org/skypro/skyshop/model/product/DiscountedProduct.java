package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int price;
    private int discount;


    public DiscountedProduct(String nameProduct, int price, int discount) {
        super(nameProduct);



        if (price <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть больше 0.");
        }
        this.price = price;

        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть в пределах от 0 до 100.");
        }
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return (int) (price * (1 - discount / 100d));
    }

    @Override
    public boolean isSpecial(Product product) {
        return true;
    }

    @Override
    public String toString() {
        return "<" + getName() + ">: " + "<" + price + ">" + "(" + discount + ")";
    }

    @Override
    public UUID getId() {
        return id;
    }
}
