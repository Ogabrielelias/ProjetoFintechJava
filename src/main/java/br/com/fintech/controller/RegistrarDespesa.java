package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.DespesaDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.entity.Despesa;
import br.com.fintech.entity.Transacao;
import br.com.fintech.factory.DAOFactory;

public class RegistrarDespesa {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//Pega os parametros passados pelo formulario
			int tipoReceita = Integer.valueOf(request.getParameter("tipoDespesa"));
			int contaId = Integer.valueOf(request.getParameter("conta"));
			Double valor = Double.valueOf(request.getParameter("valor"));
			String data = request.getParameter("data");
			String descricao = request.getParameter("descricao");
			
			//pega o proximo id da transação pelo sql oracle
			TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();
			int idTransacao = transacaoDAO.getNextId();
			//cria uma transação com os dados
			Transacao transacao = new Transacao(idTransacao,data,descricao,contaId);
			//inseri a transação ao banco de dados
			transacaoDAO.insert(transacao);
			//cria uma despesa com os dados
			DespesaDAO despesaDAO = DAOFactory.getDespesaDAO();
			Despesa despesa = new Despesa(idTransacao, valor,tipoReceita);
			//inseri a despesa ao banco de dados
			despesaDAO.insert(despesa);
			//pega a conta selecionada pelo usuario
			ContaDAO contaDAO = DAOFactory.getContaDAO();
			Conta conta = contaDAO.selectById(contaId);
			//retira o valor da despesa do saldo
			contaDAO.retirarSaldo(conta, despesa.getValor());
			
			System.out.println("Nova despesa registrada!");
			
			} catch (SQLException e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
				String erro = "Erro ao registrar Despesa";
				return ("redirect:dashboard?erro="+erro);
			} catch (Exception e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
				String erro = "Erro ao registrar Despesa";
				return ("redirect:dashboard?erro="+erro);
			}
		return ("redirect:dashboard");
	}

}
