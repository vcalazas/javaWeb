package com.vcalazas.pointstore.models;

import java.sql.ResultSet;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Market {

	private int id = 0;
	private String nome = "";

	public Market() {}

	public Market(int id) throws Exception {
		super();
		try {
			ResultSet r =   new SqlConnector().search("select * from market ;");
			if(r != null) {
				while (r.next()) {
					this.id = r.getInt("id");
					this.nome = r.getString("nome");
				}
			} else {
				throw new Exception("Erro ao consultar o banco");
			}
		} catch (Exception e) {
			throw new Exception("Erro ao consultar o banco");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



}
