package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.ContaDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.factory.DAOFactory;

public class EditarConta {
	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//Pega os parametros passados pelo formulario
			int id = Integer.valueOf(request.getParameter("id"));
			Double saldo = Double.valueOf(request.getParameter("saldo"));
			String instFinan = request.getParameter("instFinan");
			String tipoConta = request.getParameter("tipoConta");
			String descricaoConta = request.getParameter("descricaoConta");
			int usuario = Integer.valueOf(request.getParameter("usuario"));
			//cria uma conta com os parametros
			Conta conta = new Conta(id, saldo, instFinan, tipoConta, descricaoConta, usuario);
			//usa a conta criada para editar uma ja existente
			ContaDAO contaDAO = DAOFactory.getContaDAO();
			contaDAO.update(conta);
			
			System.out.println("Conta id = "+id+" alterada!");
			
			} catch (SQLException e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
				String erro = "Erro ao edita sua conta!";
				return "redirect:conta?acao=listarConta&erro="+erro;
			}
			return "redirect:conta?acao=listarConta";
	}
}
