package de.kaysubs.tracker.pantsucat.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public abstract class MainCategory implements Category {

    private final int id;
    private final String name;
    private SubCategory[] subCategories;

    public MainCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SubCategory[] getSubCategories() {
        if(subCategories == null) {
            subCategories = Arrays.stream(getClass().getDeclaredFields())
                    .filter(field -> SubCategory.class.isAssignableFrom(field.getType()))
                    .map(field -> {
                        try {
                            return (SubCategory) field.get(this);
                        } catch (IllegalAccessException e) {
                            throw new IllegalStateException("This should never happen. All fields are public.", e);
                        }
                    }).toArray(SubCategory[]::new);
        }

        return subCategories;
    }

    public String getSearchId() {
        return getId() + "_";
    }

    public SubCategory getSubcategoryFromId(int subcategoryId) {
        return Arrays.stream(getSubCategories())
                .filter(c -> c.getId() == subcategoryId).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No subcategory with id " + subcategoryId));
    }
}
