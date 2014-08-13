package de.mxro.async.map.sql.tests;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class TestThatValuesCanBeWrittenAndRead {

	@Test
	public void test() throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.
		    getConnection("jdbc:h2:~/test");
		conn.close();
	}

}
