package br.com.altamira.data.manufacturing.bom.model;

import br.com.altamira.data.manufacturing.bom.service.Store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.Vertex;

public class Product {

	Object id;
	String code;
	String color;
	String description;
	float quantity;
	float weight;
	
	static Graph g = Store.graph;
	
	@JsonIgnore
	public Object getId() {
		return id;
	}
	
	public void setId(Object id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public float getQuantity() {
		return quantity;
	}
	
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	public Product save() {
		
    	Vertex vertex = g.addVertex(null);
    	
    	vertex.setProperty("code", code);
    	vertex.setProperty("color", color);
    	vertex.setProperty("description", description);
    	vertex.setProperty("quantity", quantity);
    	vertex.setProperty("weight", weight);
    	
    	this.id = vertex.getId();
    	
    	System.out.println("Add new product: id=" + vertex.getId() + ", " + Store.graph.toString());
    	
    	return this;
	}
	
	public static Product findByProduct(Long number) {
    	
    	GraphQuery q = g.query();
    	
    	Vertex vertex = q.has("number", number).vertices().iterator().next();
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + Store.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());

		return load(vertex);
	}
	
	public static Product load(Vertex vertex) {
    	
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
