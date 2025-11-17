package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {

    private final UUID id;
    public final String name;
    public final String manual;

    public Article(String name, String manual) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.manual = manual;
    }

    public String getName() {
        return name;
    }

    public String getManual() {
        return manual;
    }

    @Override
    public String toString() {
        return name + '\'' +
                manual;
    }

    @Override
    public String searchTerm() {
        return name + manual;
    }

    @Override
    public String typeContent() {
        return "ARTICLE";
    }

    @Override
    public String getSearchableName() {
        return getName();

    }

    @Override
    public UUID getId() {
        return id;
    }
}
