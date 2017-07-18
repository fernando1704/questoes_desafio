package br.com.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

public class AppConfig extends ResourceConfig {

	public AppConfig() {
		register(RequestContextFilter.class);
		register(CampanhaRecurso.class);
                register(ClienteRecurso.class);
	}
	
}
