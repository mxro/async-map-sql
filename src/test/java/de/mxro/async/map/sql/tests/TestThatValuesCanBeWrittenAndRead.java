package de.mxro.async.map.sql.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.mxro.async.Async;
import de.mxro.async.Deferred;
import de.mxro.async.callbacks.ValueCallback;
import de.mxro.async.jre.AsyncJre;
import de.mxro.async.map.AsyncMap;
import de.mxro.async.map.sql.AsyncMapSql;
import de.mxro.async.map.sql.SqlAsyncMapDependencies;
import de.mxro.async.map.sql.SqlConnectionConfiguration;
import de.mxro.fn.Success;
import de.mxro.serialization.Serializer;
import de.mxro.serialization.jre.SerializationJre;
import de.mxro.serialization.jre.StreamDestination;
import de.mxro.serialization.jre.StreamSource;

public class TestThatValuesCanBeWrittenAndRead {

	AsyncMap<String, Object> map;

	
	SqlConnectionConfiguration sqlConf;
	SqlAsyncMapDependencies deps;
	
	@Test
	public void test_synchronous_operations() throws Exception {

		map.putSync("1", "Just a test Value");

		Assert.assertEquals("Just a test Value", map.getSync("1"));
		
		map.putSync("2", 42);

		AsyncJre.waitFor(new Deferred<Success>() {

			@Override
			public void apply(ValueCallback<Success> callback) {
				map.commit(Async.wrap(callback));
			}
		});
		
		Assert.assertEquals(42, map.getSync("2"));

		
	}
	
	@Test
	public void test_asynchronous_operations() throws Exception {
		

		AsyncJre.waitFor(new Deferred<Success>() {

			@Override
			public void apply(ValueCallback<Success> callback) {
				map.put("1", "Just a test Value", Async.wrap(callback));
			}
		});

		AsyncJre.waitFor(new Deferred<Success>() {

			@Override
			public void apply(ValueCallback<Success> callback) {
				map.commit(Async.wrap(callback));
			}
		});
		
		AsyncJre.waitFor(new Deferred<Success>() {

			@Override
			public void apply(final ValueCallback<Success> callback) {
				map.get("1", new ValueCallback<Object>() {

					@Override
					public void onFailure(Throwable t) {
						callback.onFailure(t);
					}

					@Override
					public void onSuccess(Object value) {
						Assert.assertEquals("Just a test Value", value);
						callback.onSuccess(Success.INSTANCE);
					}
				});
			}
		});

		

		
	}
	
	@Test
	public void test_persistence_in_medium() throws Exception {
		map.putSync("2", 42);

		AsyncJre.waitFor(new Deferred<Success>() {

			@Override
			public void apply(ValueCallback<Success> callback) {
				map.commit(Async.wrap(callback));
			}
		});
		
		Assert.assertEquals(42, map.getSync("2"));
		
		AsyncMap<String, Object> map2 = AsyncMapSql.createMap(AsyncMapSql.fromSqlConfiguration(sqlConf), deps);
		
		Assert.assertEquals(42, map2.getSync("2"));
		
	}
	
	@Before
	public void setUp() throws Exception {

		sqlConf = new SqlConnectionConfiguration() {

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

			@Override
			public String getConnectionString() {
				return "jdbc:h2:mem:test";
			}

			@Override
			public String getTableName() {
				return "test";
			}
		};

		AsyncMapSql.assertTable(sqlConf);
		
		final Serializer<StreamSource, StreamDestination> serializer = SerializationJre
				.newJavaSerializer();
		deps = new SqlAsyncMapDependencies() {

			@Override
			public Serializer<StreamSource, StreamDestination> getSerializer() {

				return serializer;
			}
		};

		map = AsyncMapSql.createMap(
				AsyncMapSql.fromSqlConfiguration(sqlConf), deps);
		
		AsyncJre.waitFor(new Deferred<Success>() {

			@Override
			public void apply(ValueCallback<Success> callback) {
				map.start(Async.wrap(callback));
			}
		});
	}

	@After
	public void tearDown() throws Exception {
		AsyncJre.waitFor(new Deferred<Success>() {

			@Override
			public void apply(ValueCallback<Success> callback) {
				map.stop(Async.wrap(callback));
			}
		});

	}

}
