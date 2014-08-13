package de.mxro.async.map.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import de.mxro.async.map.AsyncMap;
import de.mxro.async.map.sql.internal.SqlAsyncMapImplementation;
import de.mxro.async.map.sql.internal.SqlConnectionFactory;

/**
 * <p>
 * Core methods to interact with async-map-sql module.
 * <p>
 * Use {@link #createMap(SqlAsyncMapConfiguration, SqlAsyncMapDependencies)} to
 * create new SQL backed up AsyncMaps.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 */
public class AsyncMapSql {

	/**
	 * Creates a new AsyncMap persisted by a JDBC compatible SQL database.
	 * 
	 * @param conf
	 *            Configuration for this map.
	 * @param deps
	 *            Run-time dependencies for the maps.
	 * @return
	 */
	public static final <V> AsyncMap<String, V> createMap(
			SqlAsyncMapConfiguration conf, SqlAsyncMapDependencies deps) {
		return new SqlAsyncMapImplementation<V>(conf, deps);
	}

	public static final SqlAsyncMapConfiguration fromSqlConfiguration(
			final SqlConnectionConfiguration sqlConf) {
		return new SqlAsyncMapConfiguration() {

			@Override
			public SqlConnectionConfiguration sql() {
				return sqlConf;
			}
		};
	}

	public static final void assertTable(SqlConnectionConfiguration sqlConf) {
		Connection connection = SqlConnectionFactory.createConnection(sqlConf);

		try {
			CallableStatement statement = connection
					.prepareCall("CREATE TABLE IF NOT EXISTS "
							+ sqlConf.getTableName()
							+ "(ID VARCHAR(512) PRIMARY KEY, VALUE BLOB);");

			statement.execute();

			/*
			 * Don't close the connection for H2 in memory databases. Closing
			 * the connection would wipe the created table.
			 */
			if (!sqlConf.getConnectionString().contains(":mem:")) {
				connection.close();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
