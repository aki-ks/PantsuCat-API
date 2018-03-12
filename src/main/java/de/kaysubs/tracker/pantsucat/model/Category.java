package de.kaysubs.tracker.pantsucat.model;

import java.util.Arrays;

public interface Category {
    String getName();
    String getSearchId();

    SoftwareMainCategory software = new SoftwareMainCategory();
    AudioMainCategory audio = new AudioMainCategory();
    AnimeMainCategory anime = new AnimeMainCategory();
    LiteratureMainCategory literature = new LiteratureMainCategory();
    LiveActionMainCategory liveAction = new LiveActionMainCategory();
    PicturesMainCategory pictures = new PicturesMainCategory();

    MainCategory[] mainCategories = new MainCategory[] {
            software, audio, anime, literature, liveAction, pictures
    };

    static MainCategory fromId(int mainId) {
        return Arrays.stream(mainCategories)
                .filter(e -> e.getId() == mainId).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No MainCategory with id " + mainId));
    }

    static SubCategory fromId(int mainId, int subId) {
        return fromId(mainId).getSubcategoryFromId(subId);
    }

    class SoftwareMainCategory extends MainCategory {
        public final SubCategory applications = new SubCategory(this, "Applications", 1);
        public final SubCategory games = new SubCategory(this, "Games", 2);

        public SoftwareMainCategory() {
            super(1, "Software");
        }

    }

    class AudioMainCategory extends MainCategory {
        public final SubCategory lossless = new SubCategory(this, "Lossless", 3);
        public final SubCategory lossy = new SubCategory(this, "Lossy", 4);

        public AudioMainCategory() {
            super(2, "Audio");
        }
    }

    class AnimeMainCategory extends MainCategory {
        public final SubCategory musicVideo = new SubCategory(this, "MusicVideo", 12);
        public final SubCategory english = new SubCategory(this, "English", 5);
        public final SubCategory nonEnglish = new SubCategory(this, "NonEnglish", 13);
        public final SubCategory raw = new SubCategory(this, "Raw", 6);

        public AnimeMainCategory() {
            super(3, "Anime");
        }
    }

    class LiteratureMainCategory extends MainCategory {
        public final SubCategory english = new SubCategory(this, "English", 7);
        public final SubCategory nonEnglish = new SubCategory(this, "NonEnglish", 14);
        public final SubCategory raw = new SubCategory(this, "Raw", 8);

        public LiteratureMainCategory() {
            super(4, "Literature");
        }
    }

    class LiveActionMainCategory extends MainCategory {
        public final SubCategory english = new SubCategory(this, "English", 9);
        public final SubCategory idol = new SubCategory(this, "Idol", 10);
        public final SubCategory nonEnglish = new SubCategory(this, "NonEnglish", 18);
        public final SubCategory raw = new SubCategory(this, "Raw", 11);

        public LiveActionMainCategory() {
            super(5, "LiveAction");
        }
    }

    class PicturesMainCategory extends MainCategory {
        public final SubCategory graphics = new SubCategory(this, "Graphics", 15);
        public final SubCategory photos = new SubCategory(this, "Photos", 16);

        public PicturesMainCategory() {
            super(6, "Pictures");
        }
    }
}
