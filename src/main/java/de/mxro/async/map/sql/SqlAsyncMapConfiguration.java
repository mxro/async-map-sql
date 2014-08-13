package de.mxro.async.map.sql;

import de.mxro.async.map.AsyncMap;
import de.mxro.factories.Configuration;

/**
 * Configuration for a SQL linked {@link AsyncMap}.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 */
public interface SqlAsyncMapConfiguration extends Configuration {

	/**
	 * SQL configuration for this map.
	 * 
	 * @return
	 */
	public SqlConnectionConfiguration sql();

}
