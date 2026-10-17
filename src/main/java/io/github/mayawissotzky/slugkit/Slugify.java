package io.github.mayawissotzky.slugkit;

import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public final class Slugify {

    private final SlugOptions options;

    public Slugify() {
        this(SlugOptions.DEFAULT);
    }

    public Slugify(SlugOptions options) {
        this.options = options;
    }

    public String slugify(String text) {
        if (StringUtils.isBlank(text)) {
            return "";
        }
        String ascii = Transliterate.toAscii(text.strip());
        if (options.lowercase()) {
            ascii = ascii.toLowerCase(Locale.ROOT);
        }
        StringBuilder out = new StringBuilder(ascii.length());
        boolean pendingSep = false;
        for (char c : ascii.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                if (pendingSep && out.length() > 0) {
                    out.append(options.separator());
                }
                out.append(c);
                pendingSep = false;
            } else {
                pendingSep = true;
            }
        }
        return truncate(out.toString());
    }

    private String truncate(String slug) {
        if (slug.length() <= options.maxLength()) {
            return slug;
        }
        String cut = slug.substring(0, options.maxLength());
        int lastSep = cut.lastIndexOf(options.separator());
        return lastSep > 0 ? cut.substring(0, lastSep) : cut;
    }
}
