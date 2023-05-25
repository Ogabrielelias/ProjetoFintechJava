package br.com.fintech.entity;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.fintech.dao.TransacaoDAO;

public class Transacao {
	
	private int id;
	private Date dtTransacao;
	private String descricao;
	private int conta;
	
	public Transacao() {
		
	}
	
	

	public Transacao(int id, String dtTransacao, String descricao, int conta) {
		super();
		this.id = id;
		setDtTransacaoByStrg(dtTransacao);
		this.descricao = descricao;
		this.conta = conta;
	}



	public Date getDtTransacao() {
		return dtTransacao;
	}

	public void setDtTransacaoByStrg(String dtTransacao) {
		String dataString = dtTransacao; 
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date dataTransacao = null;
		try {
		dataTransacao = new Date(fmt.parse(dataString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dtTransacao = dataTransacao;
	}
	
	

	public void setDtTransacao(Date dtTransacao) {
		this.dtTransacao = dtTransacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getConta() {
		return conta;
	}
	
	public int getContaId() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

	public int getId() {
		return id;
	}
	
	public void setAutoId() {
		try {
			this.id = new TransacaoDAO().getNextId();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", dtTransacao=" + dtTransacao + ", descricao=" + descricao  + ", conta=" + conta + "]";
	}
	
	
	
}
