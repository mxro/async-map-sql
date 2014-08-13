package de.mxro.async.map.sql;

import nx.persistence.sql.SqlConnectionConfiguration;
import de.mxro.factories.Configuration;

public interface SqlAsyncMapConfiguration extends Configuration {

	public SqlConnectionConfiguration sql();
	
}
