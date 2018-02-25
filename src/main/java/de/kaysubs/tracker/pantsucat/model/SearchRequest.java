package de.kaysubs.tracker.pantsucat.model;

import java.util.Optional;
import java.util.OptionalInt;

public class SearchRequest {
    private Optional<Category[]> categories = Optional.empty();
    private Optional<String> searchTerm = Optional.empty();
    private OptionalInt page = OptionalInt.empty();
    private OptionalInt resultsPerPage = OptionalInt.empty();
    private OptionalInt userId = OptionalInt.empty();
    private OptionalInt fromId = OptionalInt.empty();
    private Optional<TorrentStatus> status = Optional.empty();
    private OptionalInt maxAge = OptionalInt.empty();
    private Optional<DateFilter> dateFilter = Optional.empty();
    private Optional<SizeFilter> sizeFilter = Optional.empty();
    private Optional<Sort> sort = Optional.empty();
    private Optional<Order> order = Optional.empty();
    private Optional<Language[]> languages = Optional.empty();

    public enum DateUnit {
        DAYS("d"), MONTHS("m"), YEARS("y");

        private final String id;

        DateUnit(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public enum SizeUnit {
        BYTES("b"), KILOBYTES("k"), MEGABYTES("m"), GIGABYTES("g");

        private final String id;

        SizeUnit(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    public static class DateFilter {
        private OptionalInt from;
        private OptionalInt to;
        private DateUnit dateUnit;

        public DateFilter(DateUnit dateUnit) {
            this.dateUnit = dateUnit;
        }

        public OptionalInt getFrom() {
            return from;
        }

        public DateFilter setFrom(Integer from) {
            this.from = from == null ? OptionalInt.empty() : OptionalInt.of(from);
            return this;
        }

        public OptionalInt getTo() {
            return to;
        }

        public DateFilter setTo(Integer to) {
            this.to = to == null ? OptionalInt.empty() : OptionalInt.of(to);
            return this;
        }

        public DateUnit getDateUnit() {
            return dateUnit;
        }
    }

    public static class SizeFilter {
        private OptionalInt min;
        private OptionalInt max;
        private SizeUnit sizeUnit;

        public SizeFilter(SizeUnit sizeUnit) {
            this.sizeUnit = sizeUnit;
        }

        public OptionalInt getMin() {
            return min;
        }

        public SizeFilter setMin(Integer min) {
            this.min = min == null ? OptionalInt.empty() : OptionalInt.of(min);
            return this;
        }

        public OptionalInt getMax() {
            return max;
        }

        public SizeFilter setMax(Integer max) {
            this.max = max == null ? OptionalInt.empty() : OptionalInt.of(max);
            return this;
        }

        public SizeUnit getSizeUnit() {
            return sizeUnit;
        }

    }

    public enum Sort {
        ID(0), NAME(1), DATE(2), DOWNlOADS(3), SIZE(4), SEEDERS(5), LEECHERS(6), COMPLETED(7);

        private final int id;

        Sort(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public enum Order {
        ASCENDING, DESCENDING;
    }

    public Optional<Category[]> getCategories() {
        return categories;
    }

    public SearchRequest setCategories(Category... categories) {
        this.categories = Optional.ofNullable(categories);
        return this;
    }

    public Optional<String> getSearchTerm() {
        return searchTerm;
    }

    public SearchRequest setSearchTerm(String searchTerm) {
        this.searchTerm = Optional.ofNullable(searchTerm);
        return this;
    }

    public OptionalInt getPage() {
        return page;
    }

    public SearchRequest setPage(Integer page) {
        this.page = page == null ? OptionalInt.empty() : OptionalInt.of(page);
        return this;
    }

    public OptionalInt getResultsPerPage() {
        return resultsPerPage;
    }

    /**
     * Set amount of torrents returned per page
     *
     * As of 17.02.2018
     * default: 50
     * maximum: 300
     */
    public SearchRequest setResultsPerPage(Integer resultsPerPage) {
        this.resultsPerPage = resultsPerPage == null ? OptionalInt.empty() : OptionalInt.of(resultsPerPage);
        return this;
    }

    public OptionalInt getUserId() {
        return userId;
    }

    public SearchRequest setUserId(Integer userId) {
        this.userId = userId == null ? OptionalInt.empty() : OptionalInt.of(userId);
        return this;
    }

    public OptionalInt getFromId() {
        return fromId;
    }

    /**
     * List only Torrents with an id greater/newer than the specified id.
     */
    public SearchRequest setFromId(Integer fromId) {
        this.fromId = fromId == null ? OptionalInt.empty() : OptionalInt.of(fromId);
        return this;
    }

    public Optional<TorrentStatus> getStatus() {
        return status;
    }

    /**
     * Broken on server-side (17.02.2018)
     * e.g. TRUSTED_USERS will return only A_PLUS torrents and
     * A_PLUS will return no torrents
     */
    public SearchRequest setStatus(TorrentStatus torrentStatus) {
        this.status = Optional.ofNullable(torrentStatus);
        return this;
    }

    public OptionalInt getMaxAge() {
        return maxAge;
    }

    /**
     * Broken on the server-side (17.02.2018)
     *
     * Returns only torrents that have been uploaded in the last x days.
     * This feature seems to be.
     *
     * @param maxAge in days
     */
    public SearchRequest setMaxAge(Integer maxAge) {
        this.maxAge = maxAge == null ? OptionalInt.empty() : OptionalInt.of(maxAge);
        return this;
    }

    public Optional<DateFilter> getDateFilter() {
        return dateFilter;
    }

    /**
     * Seems to be broken on server-side (17.02.2018)
     */
    public SearchRequest setDateFilter(DateFilter date) {
        this.dateFilter = Optional.ofNullable(date);
        return this;
    }

    public Optional<SizeFilter> getSizeFilter() {
        return sizeFilter;
    }

    /**
     * Seems to be broken on Server-side (17.02.2018)
     */
    public SearchRequest setSizeFilter(SizeFilter sizeFilter) {
        this.sizeFilter = Optional.ofNullable(sizeFilter);
        return this;
    }

    public Optional<Sort> getSortedBy() {
        return sort;
    }

    public SearchRequest setSortedBy(Sort sort) {
        this.sort = Optional.ofNullable(sort);
        return this;
    }

    public Optional<Order> getOrder() {
        return order;
    }

    public SearchRequest setOrder(Order order) {
        this.order = Optional.ofNullable(order);
        return this;
    }

    public Optional<Language[]> getLanguages() {
        return languages;
    }

    /**
     * List only torrents that contains all of the specified Languages.
     */
    public SearchRequest setLanguages(Language... languages) {
        this.languages = Optional.ofNullable(languages);
        return this;
    }
}
