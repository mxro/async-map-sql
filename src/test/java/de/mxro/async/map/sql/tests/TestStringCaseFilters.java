package de.mxro.async.map.sql.tests;

import junit.framework.Assert;

import org.junit.Test;

import de.mxro.async.map.sql.internal.CaseInsensitiveToCaseSensitiveFilter;
import de.mxro.async.map.sql.internal.CaseSensitiveToCaseInsensitiveFilter;

public class TestStringCaseFilters {

    @Test
    public void test() {

        final CaseSensitiveToCaseInsensitiveFilter filterIn = new CaseSensitiveToCaseInsensitiveFilter();

        final CaseInsensitiveToCaseSensitiveFilter filterOut = new CaseInsensitiveToCaseSensitiveFilter();

        final String lowerCase = filterIn.apply("Something_with_Uppercase");

        Assert.assertEquals("^something_with_^uppercase", lowerCase);

        Assert.assertEquals("Something_with_Uppercase", filterOut.apply(lowerCase));
        // System.out.println(lowerCase);

        // System.out.println();

    }

}
