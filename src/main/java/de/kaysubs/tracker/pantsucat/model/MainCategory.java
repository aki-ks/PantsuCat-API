package de.kaysubs.tracker.pantsucat.model;

public enum MainCategory {
    SOFTWARE(1, Category.Software.values()),
    AUDIO(2, Category.Audio.values()),
    ANIME(3, Category.Anime.values()),
    LITERATURE(4, Category.Literature.values()),
    LIVEACTION(5, Category.LiveAction.values()),
    PICTURES(6, Category.Pictures.values());

    private final int id;
    private final Category[] subCategories;

    MainCategory(int id, Category[] subCategories) {
        this.id = id;
        this.subCategories = subCategories;
    }

    public int getId() {
        return id;
    }

    public Category[] getSubCategories() {
        return subCategories;
    }

    public static MainCategory fromId(int id) {
        for(MainCategory c : values())
            if(c.id == id)
                return c;

        throw new IllegalArgumentException("No MainCategory with id " + id);
    }

    public Category subcategoryFromId(int subcategoryId) {
        for(Category c : getSubCategories())
            if(c.getSubCategoryId() == subcategoryId)
                return c;

        throw new IllegalArgumentException("No subcategory with id " + subcategoryId);
    }
}