package de.mxro.async.map.sql.internal;

import de.mxro.fn.Function;

public class CaseInsensitiveToCaseSensitiveFilter implements Function<String, String> {

    @Override
    public String apply(final String input) {

        final String res = "";

        for (int i=0;i<input.length();i++) {

            final char testChar = input.charAt(i);

            if (testChar != '^') {
                final res 
            }

        }

        return res;
    }
}
