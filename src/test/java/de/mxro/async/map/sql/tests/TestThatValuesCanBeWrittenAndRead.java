package de.mxro.async.map.sql.tests;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import de.mxro.async.map.sql.SqlConnectionConfiguration;

public class TestThatValuesCanBeWrittenAndRead {

	@Test
	public void test() throws Exception {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.
		    getConnection("jdbc:h2:mem:test");
		
		new SqlConnectionConfiguration() {
			
			@Override
			public String getDriverClassName() {
				return "org.h2.Driver";
			}

			@Override
			public boolean supportsInsertOrUpdate() {
				return false;
			}

			@Override
			public boolean supportsMerge() {
				return true;
			}

			@Override
			public String getMergeTemplate() {
				return "MERGE INTO " + getTableName()
						+ " (Id, Value) KEY (Id) VALUES (?, ?)";
			}
		};
		
		conn.close();
	}

}
