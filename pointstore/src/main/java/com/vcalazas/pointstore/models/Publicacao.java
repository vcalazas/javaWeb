package com.vcalazas.pointstore.models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Publicacao {

	private int id = 0;
	private int marketId = 0;
	private String datahorainicio = "";
	private String datahorafim = "";
	private int valor = 0;
	private String titulo = "";
	private String descricao = "";
	private int sobrepordescricao = 0;
	private Market market = new Market();
	private ArrayList<Produto> produtos = new ArrayList<Produto>();

	public Publicacao() {}
	
	public Publicacao(int id, int marketId, String datahorainicio, String datahorafim, int valor, String titulo,
			String descricao, int sobrepordescricao) {
		super();
		this.id = id;
		this.marketId = marketId;
		this.datahorainicio = datahorainicio;
		this.datahorafim = datahorafim;
		this.valor = valor;
		this.titulo = titulo;
		this.descricao = descricao;
		this.sobrepordescricao = sobrepordescricao;
	}

	public static ArrayList<Publicacao> listar() throws Exception{
		ArrayList<Publicacao> publicacoes = new ArrayList<Publicacao>();
		try {
			ResultSet r =   new SqlConnector().search("select * from publicacao ;");
			if(r != null) {
				HashMap<Integer, Market> markets = new HashMap<Integer, Market>();
				while (r.next()) {
					Publicacao p = new Publicacao(
							r.getInt("id"),
							r.getInt("marketId"),
							r.getString("datahorainicio"),
							r.getString("datahorafim"),
							r.getInt("valor"),
							r.getString("titulo"),
							r.getString("descricao"),
							r.getInt("sobrepordescricao")
							);
					if(markets.get(p.getMarketId()) == null) {
						markets.put(p.getMarketId(), new Market(p.getMarketId()));
					}
					p.setMarket(markets.get(p.getMarketId()));
					p.setProdutos(Produto.getProdutosFromPublicacao(p.getId(),markets.get(p.getMarketId())));
					
					publicacoes.add(p);
				}
			} else {
				throw new Exception("Erro ao consultar o banco");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Erro ao consultar o banco");
		}
		return publicacoes;
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
	public String getDatahorainicio() {
		return datahorainicio;
	}
	public void setDatahorainicio(String datahorainicio) {
		this.datahorainicio = datahorainicio;
	}
	public String getDatahorafim() {
		return datahorafim;
	}
	public void setDatahorafim(String datahorafim) {
		this.datahorafim = datahorafim;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getSobrepordescricao() {
		return sobrepordescricao;
	}
	public void setSobrepordescricao(int sobrepordescricao) {
		this.sobrepordescricao = sobrepordescricao;
	}
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) {
		this.market = market;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
}
