package com.vcalazas.pointstore.models;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.vcalazas.pointstore.utils.SqlConnector;

public class Vendapublicacao extends Publicacao {
	
	private int vendaId = 0;
	private int publicacaoId = 0;
	
	public static ArrayList<Vendapublicacao> listar(int vendaId) {
		ArrayList<Vendapublicacao> publicacoes = new ArrayList<Vendapublicacao>();
		try {
			ResultSet r =   new SqlConnector().search("select * from vendapublicacao where vendaId ="+vendaId+" ;");
			if(r != null) {
				while (r.next()) {
					Vendapublicacao vendapublicacao = new Vendapublicacao();
					vendapublicacao.setVendaId(r.getInt("vendaId"));
					vendapublicacao.setPublicacaoId(r.getInt("publicacaoId"));
					vendapublicacao.setMarketId(r.getInt("marketId"));
					vendapublicacao.setDatahorainicio(r.getString("datahorainicio"));
					vendapublicacao.setDatahorafim(r.getString("datahorafim"));
					vendapublicacao.setValor(r.getInt("valor"));
					vendapublicacao.setTitulo(r.getString("titulo"));
					vendapublicacao.setDescricao(r.getString("descricao"));
					vendapublicacao.setSobrepordescricao(r.getInt("sobrepordescricao"));
					publicacoes.add(vendapublicacao);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return publicacoes;
	}
	
	public int getVendaId() {
		return vendaId;
	}
	public void setVendaId(int vendaId) {
		this.vendaId = vendaId;
	}
	public int getPublicacaoId() {
		return publicacaoId;
	}
	public void setPublicacaoId(int publicacaoId) {
		this.publicacaoId = publicacaoId;
	}
	
}
