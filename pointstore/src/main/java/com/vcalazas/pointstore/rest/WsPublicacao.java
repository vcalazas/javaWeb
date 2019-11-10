package com.vcalazas.pointstore.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vcalazas.pointstore.models.Pessoa;
import com.vcalazas.pointstore.models.Publicacao;

@Path("/wsPublicacao")
public class WsPublicacao {

	@GET
	@Path("/listar")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public  ArrayList<Publicacao> getSelecionar(
//            @PathParam("id") int id
    ) {
        try {
			return Publicacao.listar();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Publicacao>();
		}
    }
	
}
