package br.com.altamira.data.manufacturing.bom.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.altamira.data.manufacturing.bom.service.GraphDbFactory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.Vertex;

public class Item {

	Object id;
	int item;
	String description;
	List<Product> product = new ArrayList<Product>();
	
	static final Graph g = GraphDbFactory.graph;
	
	@JsonIgnore
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Product> getProduct() {
		return product;
	}
	
	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public Item save() {
		
    	Vertex vertex = g.addVertex(null);
    	
    	vertex.setProperty("item", item);
    	vertex.setProperty("description", description);
    	
    	this.id = vertex.getId();
    	
    	System.out.println("Add new item: id=" + vertex.getId() + ", " + GraphDbFactory.graph.toString());
    	
    	for (Product p : product) {
    		p.save();
    		vertex.addEdge("product", g.getVertex(p.getId()));
    	}
    	
    	return this;
	}
	
	/*public static Item findByItem(Order order) {
    	
    	GraphQuery q = g.query();
    	
    	Vertex vertex = q.has("number", order.getNumber()).vertices().iterator().next();
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + Store.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());
		
		return load(vertex);
	}*/

	public static Item load(Vertex vertex) {
    	
		Item item = new Item();
	
		item.setItem((int)vertex.getProperty("item"));
		item.setDescription((String)vertex.getProperty("description"));
		
		item.setId(vertex.getId());
		
		Iterator<Vertex> products = vertex.getVertices(Direction.OUT, "product").iterator();

		while (products.hasNext()) {
			item.product.add(Product.load(products.next()));
		}
		
		return item;
	}
	public Order delete(Order order) {
		return new Order();
	}
	
	public Order delete(String id) {
		return new Order();
	}
}
