package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.DespesaDAO;
import br.com.fintech.dao.ReceitaDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.factory.DAOFactory;

public class ExcluirTransacao {
public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try {
		//pega o parametro id que deseja excluir
		int id = Integer.valueOf(request.getParameter("id"));
		//pega o id da conta usada na transacao
		TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();
		int idContaDaTransacao = transacaoDAO.selectById(id).getConta();
		//pega uma conta usando o id a cima
		ContaDAO contaDAO = DAOFactory.getContaDAO();
		Conta conta = contaDAO.selectById(idContaDaTransacao);
		
		ReceitaDAO receitaDAO =DAOFactory.getReceitaDAO();
		DespesaDAO despesaDAO = DAOFactory.getDespesaDAO();
		
		//valida se a transacao é uma receita ou despesa
		if(receitaDAO.selectById(id)!=null) {
			//se for receita retira saldo da conta pega anteriormente
			Double valorReceita=receitaDAO.selectById(id).getValor();
			contaDAO.retirarSaldo(conta, valorReceita);
			
		}else if(despesaDAO.selectById(id)!=null) {
			//se for despesa adiciona saldo a conta pega anteriormente
			Double valorDespesa = despesaDAO.selectById(id).getValor();
			contaDAO.adicionarSaldo(conta, valorDespesa);
		}
		//deleta a transação
		transacaoDAO.delete(id);
		
		System.out.println("Conta de id = "+id+" deletada");
		
		} catch (SQLException e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro="Erro ao excluir sua transação!";
			return("redirect:transacao?acao=listarTransacao"+erro);
		}
		return("redirect:transacao?acao=listarTransacao");
	}
}
