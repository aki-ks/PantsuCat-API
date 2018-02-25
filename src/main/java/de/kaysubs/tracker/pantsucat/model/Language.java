package de.kaysubs.tracker.pantsucat.model;

public enum Language {
    CATALAN("ca"),
    GERMAN("de"),
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    HUNGARIAN("hu"),
    INDONESIAN("id"),
    ICELANDIC("is"),
    ITALIAN("it"),
    JAPANESE("ja"),
    KOREAN("ko"),
    NORWEGIAN("nb"),
    DUTCH("nl"),
    PORTUGUESE("pt"),
    ROMANIAN("ro"),
    RUSSIAN("ru"),
    SWEDISH("sv"),
    THAI("th"),
    CHINESE("zh"),
    TRADITIONAL_CHINESE("zh-Hant");

    public static Language forId(String id) {
        for(Language l : values())
            if(l.getId().equals(id))
                return l;

        throw new IllegalArgumentException("No Language with id " + id);
    }

    private final String id;

    Language(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
