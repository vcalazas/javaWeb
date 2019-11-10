package com.vcalazas.pointstore.rest;

import com.vcalazas.pointstore.models.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/wsTest")
public class WsTest {

	@POST
	@Path("/teste")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Pessoa postTest(
            Test test
    ) {
        try {
			return new Pessoa(0, test.message);
		} catch (Exception e) {
			e.printStackTrace();
			return new Pessoa();
		}
    }
	
	@GET
	@Path("/teste/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Pessoa getTest(
            @PathParam("id") int id
    ) {
        try {
			return new Pessoa(id, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Pessoa();
		}
    }
	
}
