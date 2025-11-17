package org.skypro.skyshop.controller;

import org.skypro.skyshop.basket.UserBasket;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/shop")
public class ShopController {
    private final StorageService storageService;
    private final BasketService basketService;
    private final SearchService searchService;


    public ShopController(StorageService storageService, BasketService basketService, SearchService searchService) {
        this.storageService = storageService;
        this.basketService = basketService;
        this.searchService = searchService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getProductMap().values();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        return storageService.getArticleMap().values();
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        try {
            basketService.addForID(id);
            return "*Продукт успешно добавлен*";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }

    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

    @GetMapping("/search")
    public List<Searchable> searchResult(@RequestParam String pattern) {
        return searchService.searchResultList(pattern);
    }
}

