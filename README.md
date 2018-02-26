# PantsuCat-API
Java API implementation for https://pantsu.cat/

# Usage
You can either compile this library from source or let sbt do that for you.

Include this repository as a project in your build.sbt and let your projects depend on it:
``` sbt
lazy val pantsuCat = RootProject(uri("https://github.com/kaysubs/PantsuCat-API.git"))
dependsOn(pantsuCat)
```

The entry point of this API is the `PantsuCatApi` interface.

Here's an example usage:
``` Java
SearchResponse response = PantsuCatApi.getInstance().search(new SearchRequest()
        .setSearchTerm("Overlord")
        .setCategories(Category.Anime.ENGLISH)
        .setSortedBy(SearchRequest.Sort.SEEDERS)
);

for(TorrentInfo result : response.getTorrents())
    System.out.println(result.getName());
```

For more examples you may have a look at the `de.kaysubs.tracker.pantsucat.examples` package.
