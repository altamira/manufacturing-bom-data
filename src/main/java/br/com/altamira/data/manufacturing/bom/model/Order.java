package br.com.altamira.data.manufacturing.bom.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.altamira.data.manufacturing.bom.service.Store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.GraphQuery;
import com.tinkerpop.blueprints.Vertex;

public class Order {

	Object id;
	Long number = 0l;
	String customer = "";
	String representative = "";
	Date created = new Date();
	Date delivery = new Date();
	String quotation = "";
	String comment = "";
	
	List<Item> item = new ArrayList<Item>();
	
	static Graph g = Store.graph;
	
	public Order() {
		
	}
	
	public Order(Long number, String customer, String quotation,
			Date created) {
		super();
		this.number = number;
		this.customer = customer;
		this.quotation = quotation;

	}

	@JsonIgnore
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getRepresentative() {
		return representative;
	}

	public void setRepresentative(String representative) {
		this.representative = representative;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getDelivery() {
		return delivery;
	}

	public void setDelivery(Date delivery) {
		this.delivery = delivery;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Long getNumber() {
		return number;
	}
	
	public void setNumber(Long number) {
		this.number = number;
	}
	
	public String getCustomer() {
		return customer;
	}
	
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	public String getQuotation() {
		return quotation;
	}
	
	public void setQuotation(String quotation) {
		this.quotation = quotation;
	}

	public Order save() {
		
    	Vertex vertex = g.addVertex(null);
    	
    	vertex.setProperty("number", number);
    	vertex.setProperty("customer", customer);
    	vertex.setProperty("representative", representative);
    	vertex.setProperty("created", created);
    	vertex.setProperty("delivery", delivery);
    	vertex.setProperty("quotation", quotation);
    	vertex.setProperty("comment", comment);
    	
    	this.id = vertex.getId();
    	
    	System.out.println("Add new order: id=" + vertex.getId() + ", " + Store.graph.toString());
    	
    	for (Item i : item) {
    		i.save();
    		vertex.addEdge("item", g.getVertex(i.getId()));
    	}
    	
    	System.out.println(Store.graph.toString());
    	
    	return this;
	}
	
	/*public static Order find(String id) {
    	
    	Vertex vertex = g.getVertex(id);
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + Store.graph.toString());
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
		
		GraphQuery q = g.query();
		
		Iterable<Edge> items = q.has("number", order.getNumber()).edges();
		
		for (Edge e = items.iterator().next(); e != null; items.iterator().next()) {
			
		}
		return order;
	}*/
	
	public static Order findByNumber(Long number) {
    	
    	GraphQuery q = g.query();
    	
    	Vertex vertex = q.has("number", number).vertices().iterator().next();
    	
    	if (vertex == null) {
    		System.out.println("Vertex not found! " + Store.graph.toString());
    		return null;
    	}
    	
    	System.out.println("Vertex found: " + vertex.toString());

    	Order order = load(vertex);
		
		Iterator<Vertex> items = vertex.getVertices(Direction.OUT, "item").iterator();

		while (items.hasNext()) {
			order.item.add(Item.load(items.next()));
		}
		
		return order;
	}
	
	private static Order load(Vertex vertex) {
    	
		Order order = new Order();
	
		order.setNumber((Long)vertex.getProperty("number"));
		order.setQuotation((String)vertex.getProperty("quotation"));
		order.setCustomer((String)vertex.getProperty("customer"));
		order.setRepresentative(vertex.getProperty("representative").toString());
		order.setCreated((Date)vertex.getProperty("created"));
		order.setDelivery((Date)vertex.getProperty("delivery"));
		order.setComment((String)vertex.getProperty("comment"));
		
		order.setId(vertex.getId());
		
		return order;
	}
	
	public Order delete(Order order) {
		return new Order();
	}
	
	public Order delete(String id) {
		return new Order();
	}

}
