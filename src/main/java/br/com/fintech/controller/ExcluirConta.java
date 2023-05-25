package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.ContaDAO;
import br.com.fintech.factory.DAOFactory;

public class ExcluirConta {
	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//pega o parametro id que deseja excluir
			int id = Integer.valueOf(request.getParameter("id"));
			//deleta a conta usando o id
			ContaDAO contaDAO = DAOFactory.getContaDAO();
			contaDAO.delete(id);
			
			System.out.println("Conta de id = "+id+" deletada");
			} catch (SQLException e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
				String erro="Erro ao excluir sua conta!";
				return "redirect:conta?acao=listarConta&erro="+erro;
			}
			return "redirect:conta?acao=listarConta";
	}
}
