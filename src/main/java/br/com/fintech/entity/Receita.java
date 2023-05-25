package br.com.fintech.entity;

public class Receita extends Transacao {
	
	private int idTransacao;
	private double valor;
	private int tipoReceita;
	
	public Receita() {
		
	}
	
	

	public Receita(int idTransacao, double valor,int tipoReceita) {
		super();
		this.idTransacao = idTransacao;
		this.valor = valor;
		this.tipoReceita = tipoReceita;
	}



	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getTipoReceita() {
		return tipoReceita;
	}
	
	public int getTipoReceitaId() {
		return tipoReceita;
	}

	public void setTipoReceita(int tipoReceita) {
		this.tipoReceita = tipoReceita;
	}

	public int getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}

	@Override
	public String toString() {
		return "Receita [idTransacao=" + idTransacao + ", valor=" + valor + ", tipoReceita=" + tipoReceita + "]";
	}


}
