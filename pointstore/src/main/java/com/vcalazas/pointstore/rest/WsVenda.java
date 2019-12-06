package com.vcalazas.pointstore.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vcalazas.pointstore.models.Publicacao;
import com.vcalazas.pointstore.models.Venda;

@Path("/wsVenda")
public class WsVenda {

	@POST
	@Path("/manter")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public  ArrayList<Publicacao> getSelecionar(
//            @PathParam("") Venda venda
    		Venda venda
    ) {
        try {
			Publicacao.listar();
			return new ArrayList<Publicacao>();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<Publicacao>();
		}
    }
	
}
