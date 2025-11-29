package org.skypro.skyshop;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.basket.BasketItem;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;


    @Test
    void whenNonExistentProductAdded_thenThrowException() {
        //given
        Map<UUID, Product> productMap = new HashMap<>();

        UUID notebookID = randomUUID();
        UUID nonExistentID = randomUUID();

        Product notebook = new SimpleProduct("Notebook", 90000);
        productMap.put(notebookID, notebook);


        //when/then
        when(storageService.getProductById(notebookID)).thenReturn(Optional.of(notebook));
        when(storageService.getProductById(nonExistentID)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> basketService.addForID(notebookID));
        assertThrows(NoSuchProductException.class, () -> basketService.addForID(nonExistentID));
    }

    @Test
    void whenProductBasketIsEmpty_thenReturnEmptyMap() {

        //given
        when(productBasket.getProductMap()).thenReturn(new HashMap<>());

        //when
        UserBasket result = basketService.getUserBasket();

        // then
        assertNotNull(result);
        assertTrue(result.getBasketItemList().isEmpty());
    }

    @Test
    void whenProductBasketIsFilled_thenReturnFilledMap() {

        //given
        Map<UUID, Product> productMap = new HashMap<>();
        Map<UUID, Integer> productBasketMap = new HashMap<>();

        Product keyboard = new FixPriceProduct("Keyboard");
        UUID keyboardID = randomUUID();

        productMap.put(keyboardID, keyboard);
        productBasketMap.put(keyboardID, 4);

        when(productBasket.getProductMap()).thenReturn(productBasketMap);
        when(storageService.getProductMap()).thenReturn(productMap);

        //when
        UserBasket result = basketService.getUserBasket();

        // then
        assertNotNull(result);
        assertEquals(1, result.getBasketItemList().size());

        BasketItem basketItem = result.getBasketItemList().get(0);
        assertEquals(keyboard, basketItem.getProduct());
        assertEquals(4, basketItem.getQuantity());
    }

    @Test
    void whenProductIsAdded_thenCallAddProductMethod() {

        UUID keyboardID = randomUUID();
        Product keyboard = new FixPriceProduct("Keyboard");

        when(storageService.getProductById(keyboardID)).thenReturn(Optional.of(keyboard));

        basketService.addForID(keyboardID);

        verify(productBasket).addProduct(keyboardID);
    }


}
