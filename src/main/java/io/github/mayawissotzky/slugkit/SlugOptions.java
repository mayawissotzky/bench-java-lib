package io.github.mayawissotzky.slugkit;

public record SlugOptions(char separator, int maxLength, boolean lowercase) {

    public static final SlugOptions DEFAULT = new SlugOptions('-', 80, true);

    public SlugOptions {
        if (maxLength < 1) {
            throw new IllegalArgumentException("maxLength must be positive");
        }
        if (Character.isLetterOrDigit(separator)) {
            throw new IllegalArgumentException("separator must not be a letter or digit");
        }
    }

    public SlugOptions withSeparator(char sep) {
        return new SlugOptions(sep, maxLength, lowercase);
    }

    public SlugOptions withMaxLength(int max) {
        return new SlugOptions(separator, max, lowercase);
    }
}
