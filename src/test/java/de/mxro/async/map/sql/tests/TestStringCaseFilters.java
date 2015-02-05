package de.mxro.async.map.sql.tests;

import junit.framework.Assert;

import org.junit.Test;

import de.mxro.async.map.sql.internal.CaseInsensitiveToCaseSensitiveFilter;
import de.mxro.async.map.sql.internal.MakeKeyCaseInsensitive;

public class TestStringCaseFilters {

    @Test
    public void test() {

        final MakeKeyCaseInsensitive filterIn = new MakeKeyCaseInsensitive();

        final CaseInsensitiveToCaseSensitiveFilter filterOut = new CaseInsensitiveToCaseSensitiveFilter();

        final String lowerCase = filterIn.apply("Something_with_Uppercase");

        Assert.assertEquals("^Something_with_^Uppercase", lowerCase);

        Assert.assertEquals("Something_with_Uppercase", filterOut.apply(lowerCase));
        // System.out.println(lowerCase);

        // System.out.println();

    }

}
