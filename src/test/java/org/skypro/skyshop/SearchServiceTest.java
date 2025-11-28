package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;


    @Test
    void whenSearchResultListIsEmpty_thenResultSearchListIsEmpty() {
        String search = "кириллица";

        Map<UUID, Article> articlesMap = new HashMap<>();

        Map<UUID, Product> productMap = new HashMap<>();

        Map<UUID, Searchable> searchebleMap = new HashMap<>();
        searchebleMap.putAll(articlesMap);
        searchebleMap.putAll(productMap);
        searchebleMap.clear();

        when(storageService.getProductMap()).thenReturn(productMap);
        when(storageService.getArticleMap()).thenReturn(articlesMap);
        when(storageService.searchableMap(productMap, articlesMap)).thenReturn(searchebleMap);

        //When
        List<Searchable> result = searchService.searchResultList(search);

        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void whenSearchResultListNotContainsSearch_thenResultSearchListIsEmpty() {
        String search = "кириллица";

        Map<UUID, Article> articlesMap = Map.of(
                UUID.randomUUID(), new Article("fghjk", "4166421"),
                UUID.randomUUID(), new Article("1564652", "ndsflnm,//;'")
        );

        Map<UUID, Product> productMap = Map.of(
                UUID.randomUUID(), new SimpleProduct("notebook", 4568),
                UUID.randomUUID(), new DiscountedProduct("Monitor", 4568, 50),
                UUID.randomUUID(), new FixPriceProduct("keyboard")
        );

        Map<UUID, Searchable> searchebleMap = new HashMap<>();
        searchebleMap.putAll(articlesMap);
        searchebleMap.putAll(productMap);

        when(storageService.getProductMap()).thenReturn(productMap);
        when(storageService.getArticleMap()).thenReturn(articlesMap);
        when(storageService.searchableMap(productMap, articlesMap)).thenReturn(searchebleMap);

        //When
        List<Searchable> result = searchService.searchResultList(search);

        //Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    @Test
    void whenSearchResultListContainsSearch_thenResultSearchListIsNotEmpty() {
        String search = "Мото";
        Article hondaVFR = new Article("Мотоцикл Honda VFR",
                "Выфер - замечательный представитель класса Спорт-турист,");

        Map<UUID, Article> articlesMap = Map.of(
                UUID.randomUUID(), hondaVFR,
                UUID.randomUUID(), new Article("Книга изобретений 20 века",
                        "интересные изобретения за 20 век с коротким описанием.")
        );

        Map<UUID, Product> productMap = Map.of(
                UUID.randomUUID(), new SimpleProduct("notebook", 4568),
                UUID.randomUUID(), new DiscountedProduct("Monitor", 4568, 50),
                UUID.randomUUID(), new FixPriceProduct("keyboard")
        );

        Map<UUID, Searchable> searchebleMap = new HashMap<>();
        searchebleMap.putAll(articlesMap);
        searchebleMap.putAll(productMap);

        when(storageService.getProductMap()).thenReturn(productMap);
        when(storageService.getArticleMap()).thenReturn(articlesMap);
        when(storageService.searchableMap(productMap, articlesMap)).thenReturn(searchebleMap);

        //When
        List<Searchable> result = searchService.searchResultList(search);

        //Then
        assertNotNull(result);
        assertTrue(result.contains(hondaVFR));
        assertEquals(1, result.size());
    }


}
