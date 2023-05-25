package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entity.Usuario;
import br.com.fintech.factory.DAOFactory;

public class RegistrarUsuario {
	public String executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// Pega os parametros passados pelo formulario
			String nome = request.getParameter("nome");
			String email = request.getParameter("email").replaceAll("\\s+", "").toLowerCase();
			String senha = request.getParameter("senha");
			String dtNasc = request.getParameter("dataNasc");
			String cpf = request.getParameter("cpf");
			String sexo = request.getParameter("sexo");
			//Pega o proximo id do usuario pelo sql oracle
			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			int id = usuarioDAO.getNextId();
			//cria um usuario com os dados
			Usuario usuario = new Usuario(id, nome, email, senha, cpf, dtNasc, sexo);
			//inseri o usuario ao banco de dados
			usuarioDAO.insert(usuario);

			System.out.println("Novo usuario registrado!");

		} catch (SQLException e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro = "Email em uso!";
			return "redirect:register.jsp?erro=" + erro;
		} catch (Exception e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro = "Verifique os dados inseridos!";
			return "redirect:register.jsp?erro=" + erro;
		}
		return "redirect:home.jsp";

	}
}
