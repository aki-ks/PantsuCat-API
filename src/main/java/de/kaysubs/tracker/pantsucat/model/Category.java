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
        Applications(1), Games(2);
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
        Lossless(3), Lossy(2);
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
        Music(12), English(5), NonEnglish(13), Raw(6);
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
        English(7), NonEnglish(14), Raw(8);
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
        English(9), Idol(10), NonEnglish(18), Raw(11);
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
        Graphics(15), Photos(16);
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
