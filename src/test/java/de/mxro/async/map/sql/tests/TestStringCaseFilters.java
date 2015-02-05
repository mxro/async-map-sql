package de.mxro.async.map.sql.tests;

import org.junit.Test;

import de.mxro.async.map.sql.internal.CaseInsensitiveToCaseSensitiveFilter;
import de.mxro.async.map.sql.internal.CaseSensitiveToCaseInsensitiveFilter;

public class TestStringCaseFilters {

    @Test
    public void test() {

        final CaseInsensitiveToCaseSensitiveFilter filterIn = new CaseInsensitiveToCaseSensitiveFilter();

        final CaseSensitiveToCaseInsensitiveFilter filterOut = new CaseSensitiveToCaseInsensitiveFilter();

        System.out.println(filterIn.apply("Something_with_Uppercase"));

    }

}
