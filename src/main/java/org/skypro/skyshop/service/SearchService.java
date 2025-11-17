package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }


    public List<Searchable> searchResultList(String search) {
        Map<UUID, Searchable> searchableMap = storageService.searchableMap(storageService.getProductMap(), storageService.getArticleMap());

        List<Searchable> resultSearch = searchableMap.values().stream()
                .filter(Objects::nonNull)
                .filter(s -> s.searchTerm().toLowerCase().contains(search.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));

        return resultSearch;
    }

}
