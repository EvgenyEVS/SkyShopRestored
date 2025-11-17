package org.skypro.skyshop.basket;

import java.util.List;

public class UserBasket {
    private List<BasketItem> basketItemList;
    private Integer total = 0;


    public UserBasket(List<BasketItem> basketItemList) {
        this.basketItemList = basketItemList;
    }

    public List<BasketItem> getBasketItemList() {
        return basketItemList;
    }

    public Integer getTotal() {
        total = basketItemList.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        return total;
    }

}
