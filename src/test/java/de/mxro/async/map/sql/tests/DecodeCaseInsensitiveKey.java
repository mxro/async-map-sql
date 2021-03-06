package de.mxro.async.map.sql.tests;

import delight.functional.Function;

public class DecodeCaseInsensitiveKey implements Function<String, String> {

    @Override
    public String apply(final String input) {

        String res = "";

        int i = 0;
        while (i < input.length()) {

            final char testChar = input.charAt(i);

            if (testChar != '^') {
                res += testChar;
            } else {
                i++;
                res += Character.toUpperCase(input.charAt(i));
            }

            i++;

        }

        return res;
    }
}
