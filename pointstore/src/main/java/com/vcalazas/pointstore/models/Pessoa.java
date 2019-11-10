package com.vcalazas.pointstore.models;

import java.sql.ResultSet;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Pessoa {
	
	private int id = 0;
	private String cpf = "";
	private String nome = "";
	private int genero = 0;
	
	public Pessoa() {}
	
	public Pessoa(int id, String cpf) throws Exception {
		super();
		try {
			ResultSet r = null;
			if(cpf == null || cpf.trim().equals("")) {
				r = SqlConnector.search("select * from pessoa where id="+id+";");
			} else {
				r = SqlConnector.search("select * from pessoa where cpf='"+cpf+"';");
			}
			if(r != null) {
				while (r.next()) {
					this.id = r.getInt("id");
					this.cpf = r.getString("cpf");
					this.nome = r.getString("nome");
					this.genero = r.getInt("genero");
				}
			} else {
				throw new Exception("Erro ao consultar o banco");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getGenero() {
		return genero;
	}
	public void setGenero(int genero) {
		this.genero = genero;
	}
	
	

}
