package br.com.altamira.data.manufacturing.bom.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.com.altamira.data.manufacturing.bom.model.Order;

/**
 * Root resource (exposed at "order" path)
 */
@Path("/order")
public class OrderEndpoint {
    
    @GET
    @Path("/{number:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByNumber(@PathParam("number") Long number) {
    	
    	System.out.println("Requesting Order number: " + number);

    	Order order = Order.findByNumber(number);
    	
    	if (order == null) {
    		return Response.status(Status.NOT_FOUND).build();
    	}
    	
        return Response.ok().entity(order).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Order order) {
    	
    	Order entity = order.save();
    	
    	if (entity == null) {
    		return Response.status(Status.BAD_REQUEST).build();
    	}
    	
    	return Response
    			.created(
    					UriBuilder.fromResource(OrderEndpoint.class)
    						.path(String.valueOf(entity.getNumber())).build())
    			.entity(entity).build();
    }
}
