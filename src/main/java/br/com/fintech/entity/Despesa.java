package br.com.fintech.entity;

public class Despesa extends Transacao {

	private int idTransacao;
	private double valor;
	private int tipoDespesa;
	
	public Despesa() {
		
	}

	public Despesa(int idTransacao, double valor,  int tipoDespesa) {
		super();
		this.idTransacao = idTransacao;
		this.valor = valor;
		this.tipoDespesa = tipoDespesa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public  int getTipoDespesa() {
		return tipoDespesa;
	}
	
	public int getTipoDespesaId() {
		return tipoDespesa;
	}

	public void setTipoDespesa( int tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public int getIdTransacao() {
		return idTransacao;
	}

	public void setIdTransacao(int idTransacao) {
		this.idTransacao = idTransacao;
	}
	
	
	
}
