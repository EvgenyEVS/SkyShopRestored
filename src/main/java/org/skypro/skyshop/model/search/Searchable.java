package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    public String searchTerm();

    public String typeContent();

    public String getSearchableName();

    public default String getStringRepresentation() {
        return this.getSearchableName() + " - " + this.getClass().getSimpleName();
    }

    UUID getId();
}


