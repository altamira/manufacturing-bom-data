package br.com.altamira.data.manufacturing.bom.dao;

import java.util.Date;

import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.Vertex;

import br.com.altamira.data.manufacturing.bom.model.Order;
import br.com.altamira.data.manufacturing.bom.service.GraphDbFactory;


public class OrderDao {

	public static Order create(Order order) {
		System.out.println("Create Order: " + order);
		
    	Vertex vertex = GraphDbFactory.graph.addVertex(null);
    	
    	vertex.setProperty("number", order.getNumber());
    	vertex.setProperty("customer", order.getCustomer());
    	vertex.setProperty("representative", order.getRepresentative());
    	vertex.setProperty("created", order.getCreated());
    	vertex.setProperty("delivery", order.getDelivery());
    	vertex.setProperty("quotation", order.getQuotation());
    	vertex.setProperty("comment", order.getComment());
    	
    	System.out.println("Add new vertex: " + GraphDbFactory.graph.toString());
    	
    	return order;
	}
	
	public static Order find(String id) {
    	
    	Vertex vertex = GraphDbFactory.graph.getVertex(id);
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + GraphDbFactory.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());
    	
		Order order = new Order();
		
		order.setNumber((Long)vertex.getProperty("number"));
		order.setQuotation((String)vertex.getProperty("quotation"));
		order.setCustomer((String)vertex.getProperty("customer"));
		order.setRepresentative(vertex.getProperty("representative").toString());
		order.setCreated((Date)vertex.getProperty("created"));
		order.setDelivery((Date)vertex.getProperty("delivery"));
		order.setComment((String)vertex.getProperty("comment"));
		
		return order;
	}
	
	public static Order findByNumber(Long number) {
    	
    	GraphQuery q = GraphDbFactory.graph.query();
    	
    	Vertex vertex = q.has("number", number).vertices().iterator().next();
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + GraphDbFactory.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());
    	
		Order order = new Order();
	
		order.setNumber((Long)vertex.getProperty("number"));
		order.setQuotation((String)vertex.getProperty("quotation"));
		order.setCustomer((String)vertex.getProperty("customer"));
		order.setRepresentative(vertex.getProperty("representative").toString());
		order.setCreated((Date)vertex.getProperty("created"));
		order.setDelivery((Date)vertex.getProperty("delivery"));
		order.setComment((String)vertex.getProperty("comment"));
		
		return order;
	}
	
	public Order delete(Order order) {
		return new Order();
	}
	
	public Order delete(String id) {
		return new Order();
	}
}
