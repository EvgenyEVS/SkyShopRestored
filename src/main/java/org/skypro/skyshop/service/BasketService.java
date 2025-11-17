package org.skypro.skyshop.service;

import org.skypro.skyshop.basket.BasketItem;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addForID(UUID id) {
        if (storageService.getProductById(id).isPresent() == false) {
            throw new IllegalArgumentException("Товара с таким ID не существует");
        } else {
            productBasket.addProduct(id);
        }
    }

    public UserBasket getUserBasket() {
        return new UserBasket(productBasket.getProductMap().entrySet().stream()
                .map(s -> new BasketItem(storageService.getProductMap().get(s.getKey()), s.getValue()))
                .toList());
    }


}


