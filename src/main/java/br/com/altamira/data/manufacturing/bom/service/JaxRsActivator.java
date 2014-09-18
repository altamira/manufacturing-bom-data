package br.com.altamira.data.manufacturing.bom.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class JaxRsActivator extends Application {
	
	public JaxRsActivator() {
		super();
		
		System.out.println("Start graph database: " + GraphDbFactory.graph.toString());
	}

}
