package com.vcalazas.pointstore.models;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.vcalazas.pointstore.utils.General;
import com.vcalazas.pointstore.utils.SqlConnector;

public class Venda {

	private int id = 0;
	private int pessoaId = 0;
	private int statusvenda = 0;
	// 0 - iniciado 
	// 1 - cancelado 
	// 2 - erro
	// 3 - finalizada com sucesso
	
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
	
	public void manter() throws Exception {
		if(this.pessoaId == 0 || this.pessoaId <= 0) {
			throw new Exception("Pessoa não informada.");
		} else {
			Pessoa p = new Pessoa(this.pessoaId, null);
			if(p.getCpf() == null || p.getCpf().trim().equals("")) {
				throw new Exception("Pessoa não existente.");
			}
		}
		
		if (this.statusvenda == 0) {
			this.valor = 0;
			this.datahora = General.getdataIsoString(false);
		}
//		ResultSet r =   new SqlConnector().executeStoreProcedure("call manterVenda("+
//													this.id				+","+
//													this.pessoaId		+","+
//													this.statusvenda	+","+
//													this.datahora		+","+
//													this.valor			+")");
//		System.out.println(r);
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
