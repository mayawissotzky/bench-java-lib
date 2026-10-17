package io.github.mayawissotzky.slugkit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SlugifyTest {

    private final Slugify slugify = new Slugify();

    @Test
    void collapsesPunctuationAndSpaces() {
        assertEquals("hello-world", slugify.slugify("  Hello,   World! "));
    }

    @Test
    void transliteratesAccents() {
        assertEquals("creme-brulee", slugify.slugify("Crème Brûlée"));
    }

    @Test
    void truncatesOnSeparatorBoundary() {
        Slugify shortSlug = new Slugify(SlugOptions.DEFAULT.withMaxLength(12));
        assertEquals("one-two", shortSlug.slugify("one two three four"));
    }

    @Test
    void blankInputGivesEmptySlug() {
        assertEquals("", slugify.slugify("   "));
    }
}
