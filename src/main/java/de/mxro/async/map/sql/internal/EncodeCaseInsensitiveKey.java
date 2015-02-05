package de.mxro.async.map.sql.internal;

import de.mxro.fn.Function;

public class EncodeCaseInsensitiveKey implements Function<String, String> {

    @Override
    public String apply(final String input) {

        String res = "";

        for (int i = 0; i < input.length(); i++) {
            final char testChar = input.charAt(i);

            if (Character.isLowerCase(testChar) || testChar == '_' || testChar == '/' || testChar == '-') {
                res += testChar;
            } else {
                res += "^" + testChar;
            }

        }

        return res;
    }

}
