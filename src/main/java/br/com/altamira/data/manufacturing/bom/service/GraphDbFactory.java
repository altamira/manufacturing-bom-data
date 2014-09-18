package br.com.altamira.data.manufacturing.bom.service;

import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

public class GraphDbFactory {

	// Thread safe
	public final static Graph graph = new TinkerGraph();
    
    private GraphDbFactory() {}
	
}
