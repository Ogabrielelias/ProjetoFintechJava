package br.com.fintech.entity;

import java.sql.SQLException;

import br.com.fintech.dao.ContaDAO;

public class Conta {
	private int id;
	private double saldo;
	private String InstFinanceira;
	private String tipoConta;
	private String descricao = null;
	private int usuario;

	public Conta() {
		
	}
	
	

	public Conta(int id, double saldo, String instFinanceira, String tipoConta, String descricao, int usuario) {
		super();
		this.id = id;
		this.saldo = saldo;
		this.InstFinanceira = instFinanceira;
		this.tipoConta = tipoConta;
		this.descricao = descricao;
		this.usuario = usuario;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getInstFinanceira() {
		return InstFinanceira;
	}

	public void setInstFinanceira(String instFinanceira) {
		InstFinanceira = instFinanceira;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getUsuario() {
		return usuario;
	}
	

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	
	public void setAutoId() {
		try {
			this.id = new ContaDAO().getNextId();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", saldo=" + saldo + ", InstFinanceira=" + InstFinanceira + ", tipoConta="
				+ tipoConta + ", descricao=" + descricao + ", idUsuario=" + usuario + "]";
	}
	
}
