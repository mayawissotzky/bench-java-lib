package io.github.mayawissotzky.slugkit;

import com.ibm.icu.text.Transliterator;

final class Transliterate {

    private static final Transliterator TO_LATIN =
            Transliterator.getInstance("Any-Latin; Latin-ASCII");

    private Transliterate() {
    }

    static String toAscii(String input) {
        synchronized (TO_LATIN) {
            return TO_LATIN.transliterate(input);
        }
    }
}
