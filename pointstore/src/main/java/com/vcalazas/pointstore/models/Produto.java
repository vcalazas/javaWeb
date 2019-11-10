package com.vcalazas.pointstore.models;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Produto {
	
	private int id = 0;				
	private int marketId = 0;		
	private String nome = "";		
	private String descricao = "";	
	private int valorbruto = 0;		
	private int estoque = 0;
	private Market market = new Market();

	public Produto() {}
	
	public Produto(int id, int marketId, String nome, String descricao, int valorbruto, int estoque) {
		super();
		this.id = id;
		this.marketId = marketId;
		this.nome = nome;
		this.descricao = descricao;
		this.valorbruto = valorbruto;
		this.estoque = estoque;
	}
	
	public static ArrayList<Produto> getProdutosFromPublicacao(int publicacaoId){
		return Produto.getProdutosFromPublicacao(publicacaoId, null);
	}
	
	public static ArrayList<Produto> getProdutosFromPublicacao(int publicacaoId, Market market){
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		try {
			ResultSet r =   new SqlConnector().search("SELECT id, marketId, nome, descricao, valorbruto, estoque  FROM produtopublicacao pp left join produto p on pp.produtoId = p.id where pp.publicacaoId = "+publicacaoId+";");
			if(r != null) {
				if(market == null) {
					while (r.next()) {
						produtos.add( new Produto(
								r.getInt("id"),
								r.getInt("marketId"),
								r.getString("nome"),
								r.getString("descricao"),
								r.getInt("valorbruto"),
								r.getInt("estoque")
								));
					}
				} else {
					while (r.next()) {
						Produto p = new Produto(
								r.getInt("id"),
								r.getInt("marketId"),
								r.getString("nome"),
								r.getString("descricao"),
								r.getInt("valorbruto"),
								r.getInt("estoque")
								);
						p.setMarket(market);
						produtos.add(p);
					}
				}
				
			} else {
				throw new Exception("Erro ao consultar o banco");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return produtos;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMarketId() {
		return marketId;
	}
	public void setMarketId(int marketId) {
		this.marketId = marketId;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getValorbruto() {
		return valorbruto;
	}
	public void setValorbruto(int valorbruto) {
		this.valorbruto = valorbruto;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Market getMarket() {
		return market;
	}

	public void setMarket(Market market) {
		this.market = market;
	}
	
	

}
