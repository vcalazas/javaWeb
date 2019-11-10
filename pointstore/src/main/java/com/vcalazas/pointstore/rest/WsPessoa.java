package com.vcalazas.pointstore.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.vcalazas.pointstore.models.Pessoa;
import com.vcalazas.pointstore.models.Test;

@Path("/wsPessoa")
public class WsPessoa {
	@POST
	@Path("/login")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Pessoa postlogin(
    		Pessoa pessoa
    ) {
        try {
			return Pessoa.login(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
			return new Pessoa();
		}
    }
	
	@GET
	@Path("/selecionar/{id}")
	@Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Pessoa getSelecionar(
            @PathParam("id") int id
    ) {
        try {
			return new Pessoa(id, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Pessoa();
		}
    }
	
//	@GET
//	@Path("/listar/{id}/{cpf}/{nome}/{genero}")
//	@Consumes({MediaType.APPLICATION_JSON})
//    @Produces({MediaType.APPLICATION_JSON})
//    public Pessoa getListar(
//            @PathParam("id") int id,
//            @PathParam("cpf") String cpf,
//            @PathParam("nome") String nome,
//            @PathParam("genero") int genero
//    ) {
//        try {
//			return new Pessoa(id, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Pessoa();
//		}
//    }
	
}