package com.vcalazas.pointstore.models;

import java.sql.ResultSet;

import javax.ws.rs.PathParam;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Pessoa {

	private int id = 0;
	private String cpf = "";
	private String senha = "";
	private String nome = "";
	private int genero = 0;

	public Pessoa() {}

	public Pessoa(int id, String cpf) throws Exception {
		super();
		try {
			ResultSet r = null;
			if(cpf == null || cpf.trim().equals("")) {
				r =  new SqlConnector().search("select * from pessoa where id="+id+";");
			} else {
				r =  new SqlConnector().search("select * from pessoa where cpf='"+cpf+"';");
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

	public static Pessoa login(Pessoa mPessoa) throws Exception {
		try {
			ResultSet r =   new SqlConnector().search("select * from pessoa where cpf='"+mPessoa.getCpf()+"' and senha='"+mPessoa.getSenha()+"';");
			if(r != null) {
				Pessoa pessoa = new Pessoa();
				while (r.next()) {
					pessoa.setId(r.getInt("id"));
					pessoa.setCpf(r.getString("cpf"));
					pessoa.setNome(r.getString("nome"));
					pessoa.setGenero(r.getInt("genero"));
				}
				return pessoa;
			} else {
				throw new Exception("Erro ao consultar o banco");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	
//	public void listar(
//			int id,
//			String cpf,
//			String nome,
//			int genero
//			) throws Exception {
//		try {
//			ResultSet r = SqlConnector.search("select * from pessoa where cpf like '%"+cpf+"%' or nome like '%"+nome+"%' or  ;");
//			if(r != null) {
//				while (r.next()) {
//					this.id = r.getInt("id");
//					this.cpf = r.getString("cpf");
//					this.nome = r.getString("nome");
//					this.genero = r.getInt("genero");
//				}
//			} else {
//				throw new Exception("Erro ao consultar o banco");
//			}
//		} catch (Exception e) {
//			throw e;
//		}
//	}


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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	


}
