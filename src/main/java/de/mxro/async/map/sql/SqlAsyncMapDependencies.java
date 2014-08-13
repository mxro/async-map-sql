package de.mxro.async.map.sql;




import de.mxro.factories.Dependencies;
import de.mxro.serialization.Serializer;
import de.mxro.serialization.jre.StreamDestination;
import de.mxro.serialization.jre.StreamSource;

public interface SqlAsyncMapDependencies  extends Dependencies {

	public abstract Serializer<StreamSource, StreamDestination> getSerializer();

	
}
