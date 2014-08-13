package de.mxro.async.map.sql;




import de.mxro.factories.Dependencies;

public interface SqlAsyncMapDependencies  extends Dependencies {

	public abstract Serializer<StreamSource, StreamDestination> getSerializer();

	
}
