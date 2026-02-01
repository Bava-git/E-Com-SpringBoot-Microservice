package com.khapara.productservice.utilities;

public class SlugUtil {

    public static String toSlug(String input) {
        return input.toLowerCase().replaceAll("[^a-z0-9\\s]", "") // remove special chars
                .trim()
                .replaceAll("\\s+", "-"); // replace spaces with hyphens
    }

}
