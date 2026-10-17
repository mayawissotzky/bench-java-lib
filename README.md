# slugkit

Turn arbitrary text into URL-friendly slugs, with transliteration for non-Latin scripts.

```java
Slugify slugify = new Slugify();
slugify.slugify("Crème Brûlée, Paris!"); // "creme-brulee-paris"

new Slugify(SlugOptions.DEFAULT.withSeparator('_').withMaxLength(20))
        .slugify("A rather long article title"); // "a_rather_long"
```

## Install

```xml
<dependency>
  <groupId>io.github.mayawissotzky</groupId>
  <artifactId>slugkit</artifactId>
  <version>0.4.1</version>
</dependency>
```

## Releasing

Maintainers run `scripts/release.sh <version>` from a clean checkout with a GPG key and
Maven Central credentials configured in `~/.m2/settings.xml`. Pushing the tag triggers the
GitHub release workflow.

## License

MIT
