package br.com.altamira.data.manufacturing.bom.dao;

import br.com.altamira.data.manufacturing.bom.model.Product;
import br.com.altamira.data.manufacturing.bom.model.Order;
import br.com.altamira.data.manufacturing.bom.service.GraphDbFactory;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.Vertex;

public class ProductDao {
	
	public static Product create(Product product) {
		System.out.println("Create Product: " + product);
		
    	Vertex vertex = GraphDbFactory.graph.addVertex(null);
    	
    	vertex.setProperty("code", product.getCode());
    	vertex.setProperty("color", product.getColor());
    	vertex.setProperty("description", product.getDescription());
    	vertex.setProperty("quantity", product.getQuantity());
    	vertex.setProperty("weight", product.getWeight());
    	
    	System.out.println("Add new vertex: " + GraphDbFactory.graph.toString());
    	
    	return product;
	}
	
	public static Product findByProduct(Long number) {
    	
    	GraphQuery q = GraphDbFactory.graph.query();
    	
    	Vertex vertex = q.has("number", number).vertices().iterator().next();
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + GraphDbFactory.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());
    	
		Product product = new Product();
	
		product.setCode(vertex.getProperty("code").toString());
		product.setColor(vertex.getProperty("color").toString());
		product.setDescription((String)vertex.getProperty("description"));
		product.setQuantity((float)vertex.getProperty("quantity"));
		product.setWeight((float)vertex.getProperty("weight"));
		
		return product;
	}
	
	public Order delete(Order order) {
		return new Order();
	}
	
	public Order delete(String id) {
		return new Order();
	}
}
