package de.mxro.async.map.sql;

import de.mxro.async.map.AsyncMap;
import de.mxro.async.map.sql.internal.SqlAsyncMapImplementation;

public class AsyncMapSql {

	public static final <V> AsyncMap<String, V> createMap(SqlAsyncMapConfiguration conf, SqlAsyncMapDependencies deps) {
		return new SqlAsyncMapImplementation<V>(conf, deps);
	}
	
}
