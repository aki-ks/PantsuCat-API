# PantsuCat-API
Java API implementation for https://pantsu.cat/

# Installation
One way to use this library is to inculude it through jitpack.
How to use it with your build tool is explained at [their page](https://jitpack.io/#aki-ks/PantsuCat-API).

If you're building with sbt, you may add this library as a remote project in your `build.sbt` and let your project depend on it.
Sbt builds the library from source and will apply all new commits to the repository.
``` sbt
lazy val pantsuCat = RootProject(uri("https://github.com/kaysubs/PantsuCat-API.git"))
dependsOn(pantsuCat)
```

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
