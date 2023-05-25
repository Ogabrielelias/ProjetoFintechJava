package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.factory.DAOFactory;

public class ExcluirInvest {
public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try {
		//pega o parametro id que deseja excluir
		int id = Integer.valueOf(request.getParameter("id"));
		//deleta o investimento usando o id
		InvestimentoDAO investimentoDAO = DAOFactory.getInvestimentoDAO();
		investimentoDAO.delete(id);
		
		System.out.println("Investimento de id = "+id+" deletado");
		
		} catch (SQLException e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro="Erro ao excluir seu investimento!";
			return"redirect:listarInvestimentos?acao=listarInvest&erro="+erro;
		}
		return"redirect:listarInvestimentos?acao=listarInvest";
	}
}
