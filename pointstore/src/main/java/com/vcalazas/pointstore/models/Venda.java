package com.vcalazas.pointstore.models;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Venda {

	private int id = 0;
	private int pessoaId = 0;
	private int statusvenda = 0;
	private String datahora  = "";
	private int valor = 0;
	private ArrayList<Vendapublicacao> publicacoes = new ArrayList<Vendapublicacao>();
	private ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
	
	public Venda() {}
	
	public Venda(int id, int pessoaId, int statusvenda, String datahora, int valor) {
		super();
		this.id = id;
		this.pessoaId = pessoaId;
		this.statusvenda = statusvenda;
		this.datahora = datahora;
		this.valor = valor;
	}
	
	public Venda(int vendaId) throws Exception {
		super();
		try {
			ResultSet r =   new SqlConnector().search("select * from venda where id ="+vendaId+" ;");
			if(r != null) {
				while (r.next()) {
					this.id = r.getInt("id");
					this.pessoaId = r.getInt("pessoaId");
					this.statusvenda = r.getInt("statusvenda");
					this.datahora = r.getString("datahora");
					this.valor = r.getInt("valor");
					this.publicacoes = Vendapublicacao.listar(vendaId);
					this.pagamentos = Pagamento.listar(vendaId);
				}
			} else {
				throw new Exception("Erro ao consultar o banco");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void finalizarCompra() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPessoaId() {
		return pessoaId;
	}
	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}
	public int getStatusvenda() {
		return statusvenda;
	}
	public void setStatusvenda(int statusvenda) {
		this.statusvenda = statusvenda;
	}
	public String getDatahora() {
		return datahora;
	}
	public void setDatahora(String datahora) {
		this.datahora = datahora;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	
	
	
}
