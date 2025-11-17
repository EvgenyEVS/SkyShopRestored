package org.skypro.skyshop.basket;

import org.skypro.skyshop.model.product.Product;

public class BasketItem {
    private Product product;
    private Integer quantity;

    public BasketItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }


}
