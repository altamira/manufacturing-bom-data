package br.com.altamira.data.manufacturing.bom.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.altamira.data.manufacturing.bom.service.GraphDbFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.Vertex;

public class Component {

	Object id;
	String code;
	String color;
	String description;
	float quantity;
	float weight;
	
	List<Component> component = new ArrayList<Component>();
	
	static final Graph g = GraphDbFactory.graph;
	
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
	
	public List<Component> getComponent() {
		return component;
	}

	public void setComponent(List<Component> component) {
		this.component = component;
	}

	public Component save() {
		
    	Vertex vertex = g.addVertex(null);
    	
    	vertex.setProperty("code", code);
    	vertex.setProperty("color", color);
    	vertex.setProperty("description", description);
    	vertex.setProperty("quantity", quantity);
    	vertex.setProperty("weight", weight);
    	
    	this.id = vertex.getId();
    	
    	System.out.println("Add new component: id=" + vertex.getId() + ", " + GraphDbFactory.graph.toString());
    	
    	for (Component c : component) {
    		c.save();
    		vertex.addEdge("component", g.getVertex(c.getId()));
    	}
    	
    	return this;
	}
	
	public static Component findByComponent(Long number) {
    	
    	GraphQuery q = g.query();
    	
    	Vertex vertex = q.has("number", number).vertices().iterator().next();
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + GraphDbFactory.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());

		return load(vertex);
	}
	
	public static Component load(Vertex vertex) {
    	
		Component component = new Component();
	
		component.setCode(vertex.getProperty("code").toString());
		component.setColor(vertex.getProperty("color").toString());
		component.setDescription((String)vertex.getProperty("description"));
		component.setQuantity((float)vertex.getProperty("quantity"));
		component.setWeight((float)vertex.getProperty("weight"));
		
		component.setId(vertex.getId());
		
		Iterator<Vertex> components = vertex.getVertices(Direction.OUT, "component").iterator();

		while (components.hasNext()) {
			component.component.add(Component.load(components.next()));
		}
		
		return component;
	}
	
	public Order delete(Order order) {
		return new Order();
	}
	
	public Order delete(String id) {
		return new Order();
	}

}
