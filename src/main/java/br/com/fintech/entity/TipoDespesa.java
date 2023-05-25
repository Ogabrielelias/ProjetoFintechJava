package br.com.fintech.entity;

import java.sql.SQLException;

import br.com.fintech.dao.TipoDespesaDAO;

public class TipoDespesa {
	
	private int id;
	private String tipoDespesa;
	
	public TipoDespesa() {
		
	}

	public String getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setAutoId() {
		try {
			this.id = new TipoDespesaDAO().getNextId();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "TipoDespesa [id=" + id + ", tipoDespesa=" + tipoDespesa + "]";
	}
	
}
