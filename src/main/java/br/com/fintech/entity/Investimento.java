package br.com.fintech.entity;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.fintech.dao.InvestimentoDAO;

public class Investimento {

	private int id;
	private String nomeInvestimento;
	private double valorInvestido;
	private String tipoInvestimento;
	private String descricao;
	private Date dtAquisicao;
	private String codAtivo;
	private int conta;
	
	public Investimento() {
		
	}
	
	
	
	

	public Investimento(int id, String nomeInvestimento, double valorInvestido, String tipoInvestimento,
			String descricao, String dtAquisicao, String codAtivo, int conta) {
		super();
		this.id = id;
		this.nomeInvestimento = nomeInvestimento;
		this.valorInvestido = valorInvestido;
		this.tipoInvestimento = tipoInvestimento;
		this.descricao = descricao;
		setDtAquisicaoByStrg(dtAquisicao);
		this.codAtivo = codAtivo;
		this.conta = conta;
	}





	public String getNomeInvestimento() {
		return nomeInvestimento;
	}

	public void setNomeInvestimento(String nomeInvestimento) {
		this.nomeInvestimento = nomeInvestimento;
	}

	public double getValorInvestido() {
		return valorInvestido;
	}

	public void setValorInvestido(double valorInvestido) {
		this.valorInvestido = valorInvestido;
	}

	public String getTipoInvestimento() {
		return tipoInvestimento;
	}

	public void setTipoInvestimento(String tipoInvestimento) {
		this.tipoInvestimento = tipoInvestimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtAquisicao() {
		return dtAquisicao;
	}

	public void setDtAquisicaoByStrg(String dtAquisicao) {
		String dataString = dtAquisicao; 
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date dataAquisicao = null;
		try {
		dataAquisicao = new Date(fmt.parse(dataString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dtAquisicao = dataAquisicao;
	}
	

	public void setDtAquisicao(Date dtAquisicao) {
		this.dtAquisicao = dtAquisicao;
	}

	public String getCodAtivo() {
		return codAtivo;
	}

	public void setCodAtivo(String codAtivo) {
		this.codAtivo = codAtivo;
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
			this.id = new InvestimentoDAO().getNextId();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Investimento [id=" + id + ", nomeInvestimento=" + nomeInvestimento + ", valorInvestido="
				+ valorInvestido + ", tipoInvestimento=" + tipoInvestimento + ", descricao=" + descricao
				+ ", dtAquisicao=" + dtAquisicao + ", codAtivo=" + codAtivo + ", conta=" + conta + "]";
	}
	
	
	
}
