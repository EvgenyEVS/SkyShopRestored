package org.skypro.skyshop.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Component
public class ProductBasket {
    private final Map<UUID, Integer> productMap;

    public ProductBasket() {
        this.productMap = new HashMap<>();
    }

    public void addProduct(UUID id) {
        productMap.put(id, productMap.getOrDefault(id, 0) + 1);
    }

    public Map<UUID, Integer> getProductMap() {
        return Collections.unmodifiableMap(productMap);
    }
}
