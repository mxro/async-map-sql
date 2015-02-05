package de.mxro.async.map.sql.tests;

import junit.framework.Assert;

import org.junit.Test;

import de.mxro.async.map.sql.internal.DecodeCaseInsensitiveKey;
import de.mxro.async.map.sql.internal.EncodeCaseInsensitiveKey;

public class TestStringCaseFilters {

    @Test
    public void test() {

        final EncodeCaseInsensitiveKey filterIn = new EncodeCaseInsensitiveKey();

        final DecodeCaseInsensitiveKey filterOut = new DecodeCaseInsensitiveKey();

        final String lowerCase = filterIn.apply("Something_with_Uppercase");

        Assert.assertEquals("^Something_with_^Uppercase", lowerCase);

        Assert.assertEquals("Something_with_Uppercase", filterOut.apply(lowerCase));

        final String key2 = filterIn.apply("this/is/Nothing/but_a_very-common_KEY");

        Assert.assertEquals("this/is/^Nothing/but_a_very-common_^K^E^Y", key2);

        System.out.println(filterOut.apply(key2));

    }

}
