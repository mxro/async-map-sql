package de.mxro.async.map.sql;

import de.mxro.async.map.AsyncMap;
import de.mxro.async.map.sql.internal.SqlAsyncMapImplementation;
import de.mxro.async.map.sql.internal.SqlConnectionFactory;

public class AsyncMapSql {

	public static final <V> AsyncMap<String, V> createMap(SqlAsyncMapConfiguration conf, SqlAsyncMapDependencies deps) {
		return new SqlAsyncMapImplementation<V>(conf, deps);
	}
	
	public static final SqlAsyncMapConfiguration fromSqlConfiguration(final SqlConnectionConfiguration sqlConf) {
		return new SqlAsyncMapConfiguration() {
			
			@Override
			public SqlConnectionConfiguration sql() {
				return sqlConf;
			}
		};
	}
	
	public static final void assertTable(SqlConnectionConfiguration sqlConf) {
		Connection connection = SqlConnectionFactory.createConnection(sqlConf);
	}
	
}
