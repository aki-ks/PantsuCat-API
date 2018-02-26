package de.kaysubs.tracker.pantsucat.model;

public interface Category {
    String name();
    MainCategory getMainCategory();
    int getSubCategoryId();

    default int getMainCategoryId() {
        return getMainCategory().getId();
    }

    default String getId() {
        return getMainCategoryId() + "_" + getSubCategoryId();
    }

    static Category fromId(int mainId, int subId) {
        return MainCategory.fromId(mainId).subcategoryFromId(subId);
    }

    enum Software implements Category {
        APPLICATIONS(1), GAMES(2);
        private final int subId;

        Software(int subId) {
            this.subId = subId;
        }

        @Override
        public int getSubCategoryId() {
            return this.subId;
        }

        @Override
        public MainCategory getMainCategory() {
            return MainCategory.SOFTWARE;
        }
    }

    enum Audio implements Category {
        LOSSLESS(3), LOSSY(2);
        private final int subId;

        Audio(int subId) {
            this.subId = subId;
        }

        @Override
        public int getSubCategoryId() {
            return this.subId;
        }

        @Override
        public MainCategory getMainCategory() {
            return MainCategory.AUDIO;
        }
    }

    enum Anime implements Category {
        MUSIC(12), ENGLISH(5), NON_ENGLISH(13), RAW(6);
        private final int subId;

        Anime(int subId) {
            this.subId = subId;
        }

        @Override
        public int getSubCategoryId() {
            return this.subId;
        }

        @Override
        public MainCategory getMainCategory() {
            return MainCategory.ANIME;
        }
    }

    enum Literature implements Category {
        ENGLISH(7), NON_ENGLISH(14), RAW(8);
        private final int subId;

        Literature(int subId) {
            this.subId = subId;
        }

        @Override
        public int getSubCategoryId() {
            return this.subId;
        }

        @Override
        public MainCategory getMainCategory() {
            return MainCategory.LITERATURE;
        }
    }

    enum LiveAction implements Category {
        ENGLISH(9), IDOL(10), NON_ENGLISH(18), RAW(11);
        private final int subId;

        LiveAction(int subId) {
            this.subId = subId;
        }

        @Override
        public int getSubCategoryId() {
            return this.subId;
        }

        @Override
        public MainCategory getMainCategory() {
            return MainCategory.LIVEACTION;
        }
    }

    enum Pictures implements Category {
        GRAPHICS(15), PICTURES(16);
        private final int subId;

        Pictures(int subId) {
            this.subId = subId;
        }

        @Override
        public int getSubCategoryId() {
            return this.subId;
        }

        @Override
        public MainCategory getMainCategory() {
            return MainCategory.PICTURES;
        }
    }
}
