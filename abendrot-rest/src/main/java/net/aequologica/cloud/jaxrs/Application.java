package net.aequologica.cloud.jaxrs;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import net.aequologica.cloud.jaxrs.util.GsonMessageBodyHandler;

import org.glassfish.jersey.filter.LoggingFilter;

public class Application extends javax.ws.rs.core.Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();

		classes.add(Resource.class);
		classes.add(ResourceComplex.class);

		return classes;
	}

	@Override
	public Set<Object> getSingletons() {
		final HashSet<Object> instances = new HashSet<>();

		instances.add(new LoggingFilter(Logger.getLogger(Application.class.getName()), false));
		instances.add(new GsonMessageBodyHandler<Object>());

		return instances;
	}

}
