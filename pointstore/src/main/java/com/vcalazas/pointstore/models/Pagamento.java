package com.vcalazas.pointstore.models;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Pagamento {
	
	private int id = 0;
	private int pessoaId = 0;
	private int vendaId = 0;
	private String datahora = "";
	private int valor = 0;
	private int statuspagamento = 0;
	
	public Pagamento(int id, int pessoaId, int vendaId, String datahora, int valor, int statuspagamento) {
		super();
		this.id = id;
		this.pessoaId = pessoaId;
		this.vendaId = vendaId;
		this.datahora = datahora;
		this.valor = valor;
		this.statuspagamento = statuspagamento;
	}
	
	public Pagamento(int pagamentoId) throws Exception {
		super();
		try {
			ResultSet r =   new SqlConnector().search("select * from venda where id ="+pagamentoId+" ;");
			if(r != null) {
				while (r.next()) {
					this.id = r.getInt("id");
					this.pessoaId = r.getInt("pessoaId");
					this.vendaId = r.getInt("vendaId");
					this.datahora = r.getString("datahora");
					this.valor = r.getInt("valor");
					this.statuspagamento = r.getInt("statuspagamento");
				}
			} else {
				throw new Exception("Erro ao consultar o banco");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static ArrayList<Pagamento> listar(int vendaId) {
		ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
		try {
			ResultSet r =   new SqlConnector().search("select * from pagamento where vendaId="+vendaId+" ;");
			if(r != null) {
				while (r.next()) {
					pagamentos.add(new Pagamento(
							r.getInt("id"),
							r.getInt("pessoaId"),
							r.getInt("vendaId"),
							r.getString("datahora"),
							r.getInt("valor"),
							r.getInt("statuspagamento")
							));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagamentos;
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
	public int getVendaId() {
		return vendaId;
	}
	public void setVendaId(int vendaId) {
		this.vendaId = vendaId;
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
	public int getStatuspagamento() {
		return statuspagamento;
	}
	public void setStatuspagamento(int statuspagamento) {
		this.statuspagamento = statuspagamento;
	}

}
