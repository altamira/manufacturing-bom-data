package br.com.altamira.data.manufacturing.bom.service;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.altamira.data.manufacturing.bom.model.Order;

/**
 * Root resource (exposed at "order" path)
 */
@Path("/order")
public class OrderEndpoint {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@PathParam("id") Long id) {
    	Order order = new Order(id, Long.valueOf(72271l), "DARUMA", "00085088", new Date());
        return Response.ok(order).build();
    }
}
