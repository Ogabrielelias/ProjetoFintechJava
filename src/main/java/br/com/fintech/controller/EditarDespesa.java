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

public class EditarDespesa {
	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//Pega os parametros passados pelo formulario
			int id = Integer.valueOf(request.getParameter("id"));
			int tipoDespesa = Integer.valueOf(request.getParameter("tipoDespesa"));
			int idConta = Integer.valueOf(request.getParameter("conta"));
			int idContaAntiga = Integer.valueOf(request.getParameter("contaAntiga"));
			Double valor = Double.valueOf(request.getParameter("valor"));
			Double valorAntigo = Double.valueOf(request.getParameter("valorAntigo"));
			String dtDespesa = request.getParameter("data");
			String descricao = request.getParameter("descricao");
			//cria uma transacao e uma despesa com os parametros
			Transacao transacao = new Transacao(id,dtDespesa,descricao,idConta);
			Despesa despesa = new Despesa(id,valor,tipoDespesa);
			//usa a transacao e a despesa criada para editar uma ja existente
			TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();
			transacaoDAO.update(transacao);
			DespesaDAO despesaDAO = DAOFactory.getDespesaDAO();
			despesaDAO.update(despesa);
			//pega a conta nova que foi selecionada na edicao
			ContaDAO contaDAO = DAOFactory.getContaDAO();
			Conta conta = contaDAO.selectById(idConta);
			//pega a conta antiga que foi selecionada na edicao
			Conta contaAntiga = contaDAO.selectById(idContaAntiga);
			//adiciona saldo / retira o valor da despesa que havia sido adicionado ao saldo da conta antiga
			contaDAO.adicionarSaldo(contaAntiga, valorAntigo);
			//retira saldo / adiciona o valor da despesa a conta nova
			contaDAO.retirarSaldo(conta, valor);
			
			System.out.println("Despesa id: "+id+" alterada!");
			
		}catch(SQLException e ) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro = "Erro ao editar sua despesa!";
			return "redirect:transacao?acao=listarTransacao&erro="+erro;
		}
		return "redirect:transacao?acao=listarTransacao";
	}
	
}
