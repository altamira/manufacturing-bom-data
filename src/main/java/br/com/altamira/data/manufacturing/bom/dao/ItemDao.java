package br.com.altamira.data.manufacturing.bom.dao;

import br.com.altamira.data.manufacturing.bom.model.Item;
import br.com.altamira.data.manufacturing.bom.model.Order;
import br.com.altamira.data.manufacturing.bom.service.Store;

import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.Vertex;

public class ItemDao {
	
	public static Item create(Item item) {
		System.out.println("Create Item: " + item);
		
    	Vertex vertex = Store.graph.addVertex(null);
    	
    	vertex.setProperty("item", item.getItem());
    	vertex.setProperty("description", item.getDescription());
    	
    	System.out.println("Add new vertex: " + Store.graph.toString());
    	
    	return item;
	}
	
	public static Item findByItem(Long number) {
    	
    	GraphQuery q = Store.graph.query();
    	
    	Vertex vertex = q.has("number", number).vertices().iterator().next();
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + Store.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());
    	
		Item item = new Item();
	
		item.setItem((int)vertex.getProperty("item"));
		item.setDescription((String)vertex.getProperty("description"));
		
		return item;
	}
	
	public Order delete(Order order) {
		return new Order();
	}
	
	public Order delete(String id) {
		return new Order();
	}
}
