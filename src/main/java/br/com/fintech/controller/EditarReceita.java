package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.ReceitaDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.entity.Receita;
import br.com.fintech.entity.Transacao;
import br.com.fintech.factory.DAOFactory;

public class EditarReceita {
	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//Pega os parametros passados pelo formulario
			int id = Integer.valueOf(request.getParameter("id"));
			int tipoReceita = Integer.valueOf(request.getParameter("tipoReceita"));
			int idConta = Integer.valueOf(request.getParameter("conta"));
			int idContaAntiga = Integer.valueOf(request.getParameter("contaAntiga"));
			Double valor = Double.valueOf(request.getParameter("valor"));
			Double valorAntigo = Double.valueOf(request.getParameter("valorAntigo"));
			String dtReceita = request.getParameter("data");
			String descricao = request.getParameter("descricao");
			//cria uma transacao e uma receita com os parametros
			Transacao transacao = new Transacao(id,dtReceita,descricao,idConta);
			Receita receita = new Receita(id,valor,tipoReceita);
			//usa a transacao e a receita criada para editar uma ja existente
			TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();
			transacaoDAO.update(transacao);
			ReceitaDAO receitaDAO = DAOFactory.getReceitaDAO();
			receitaDAO.update(receita);
			//pega a conta nova que foi selecionada na edicao
			ContaDAO contaDAO = DAOFactory.getContaDAO();
			Conta conta = contaDAO.selectById(idConta);
			//pega a conta antiga que foi selecionada na edicao
			Conta contaAntiga = contaDAO.selectById(idContaAntiga);
			//retira saldo / retira o valor da receita que havia sido adicionado ao saldo da conta antiga
			contaDAO.retirarSaldo(contaAntiga, valorAntigo);
			//adiciona saldo / adiciona o valor da receita a conta nova
			contaDAO.adicionarSaldo(conta, valor);
			
			System.out.println("Receita id: "+id+" alterada!");
			
		}catch(SQLException e ) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro = "Erro ao editar sua receita!";
			return "redirect:transacao?acao=listarTransacao&erro="+erro;
		}
		return("redirect:transacao?acao=listarTransacao");
	}
}
