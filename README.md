# PantsuCat-API
Java API implementation for https://pantsu.cat/

# Installation
The recommended way to use this library is to add the github repository as a project in your build.sbt and let your project depend on it.
Sbt builds the library from source and will apply all updates in the repository.
``` sbt
lazy val pantsuCat = RootProject(uri("https://github.com/kaysubs/PantsuCat-API.git"))
dependsOn(pantsuCat)
```
Of course you may also use a different build tool that support that feature or manually compile this library and its dependencies.

# Usage
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
