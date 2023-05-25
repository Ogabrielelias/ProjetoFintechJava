package br.com.fintech.entity;

import java.sql.SQLException;

import br.com.fintech.dao.TipoReceitaDAO;

public class TipoReceita {
	
	private int id;
	private String tipoReceita;
	
	public TipoReceita() {
		
	}

	public String getTipoReceita() {
		return tipoReceita;
	}

	public void setTipoReceita(String tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setAutoId() {
		try {
			this.id = new TipoReceitaDAO().getNextId();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "TipoReceita [id=" + id + ", tipoReceita=" + tipoReceita + "]";
	}
	
	
}
