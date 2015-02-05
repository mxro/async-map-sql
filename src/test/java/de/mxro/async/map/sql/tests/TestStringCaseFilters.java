package de.mxro.async.map.sql.tests;

import org.junit.Test;

import de.mxro.async.map.sql.internal.CaseInsensitiveToCaseSensitiveFilter;
import de.mxro.async.map.sql.internal.CaseSensitiveToCaseInsensitiveFilter;

public class TestStringCaseFilters {

    @Test
    public void test() {

        final CaseSensitiveToCaseInsensitiveFilter filterIn = new CaseSensitiveToCaseInsensitiveFilter();

        final CaseInsensitiveToCaseSensitiveFilter filterOut = new CaseInsensitiveToCaseSensitiveFilter();

        final String lowerCase = filterIn.apply("Something_with_Uppercase");
        System.out.println(lowerCase);

        System.out.println(filterOut.apply(lowerCase));

    }

}
