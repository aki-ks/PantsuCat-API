package de.kaysubs.tracker.pantsucat.model;

public class SubCategory implements Category {

    private final MainCategory mainCategory;
    private final String name;
    private final int id;

    public SubCategory(MainCategory mainCategory, String name, int id) {
        this.mainCategory = mainCategory;
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public MainCategory getMainCategory() {
        return mainCategory;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getSearchId() {
        return getMainCategory().getId() + "_" + getId();
    }
}
