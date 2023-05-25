package br.com.fintech.factory;

import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.DespesaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.ReceitaDAO;
import br.com.fintech.dao.TipoDespesaDAO;
import br.com.fintech.dao.TipoReceitaDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.dao.UsuarioDAO;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAO();
	}
	
	public static ContaDAO getContaDAO() {
		return new ContaDAO();
	}
	
	public static DespesaDAO getDespesaDAO() {
		return new DespesaDAO();
	}
	
	public static InvestimentoDAO getInvestimentoDAO() {
		return new InvestimentoDAO();
	}
	
	public static ReceitaDAO getReceitaDAO() {
		return new ReceitaDAO();
	}
	
	public static TipoDespesaDAO getTipoDespesaDAO() {
		return new TipoDespesaDAO();
	}
	
	public static TipoReceitaDAO getTipoReceitaDAO() {
		return new TipoReceitaDAO();
	}
	
	public static TransacaoDAO getTransacaoDAO() {
		return new TransacaoDAO();
	}
	
}
