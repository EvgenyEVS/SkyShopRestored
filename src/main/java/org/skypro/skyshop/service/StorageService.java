package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class StorageService {
    public String getTestMessage() {
        return "Hello from StorageService";
    }

    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();

        addProduct(new SimpleProduct("Notebook", 90000));
        addProduct(new DiscountedProduct("Monitor", 20000, 40));
        addProduct(new FixPriceProduct("Keyboard"));

        addArticle(new Article("Книга изобретений 20 века", "В этом справочнике собраны самые \" +\n" +
                "                        \"интересные изобретения за 20 век с коротким описанием. \" +\n" +
                "                        \"Хронологический порядок и поиск по стране, автору \" +\n" +
                "                        \"реализован в соответствующих таблицах"));
        addArticle(new Article("Мотоцикл Honda VFR", "Мотоцикл Honda VFR (Хонда ВФР), \" +\n" +
                "                        \"он же Выфер - замечательный представитель класса Спорт-турист, \" +\n" +
                "                        \"подарит Вам настоящую свободу перемещений, как будто у Вас выросли крылья!"));
        addArticle(new Article("Птицы Австралии", "Всякие разные птицы там летают. \" +\n" +
                "                \"крылья у одной такие, крылья у другой сякие, и как раз крылья уже 3 раза упоминаются)))"));

    }

    public Map<UUID, Searchable> searchableMap(Map<UUID, Product> products, Map<UUID, Article> articles) {
        return Stream.of(products, articles)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }


    public Map<UUID, Product> getProductMap() {
        return productMap;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public Map<UUID, Article> getArticleMap() {
        return articleMap;
    }


    public void addProduct(Product product) {

        productMap.put(product.getId(), product);
    }

    private void addArticle(Article article) {
        articleMap.put(article.getId(), article);
    }


}