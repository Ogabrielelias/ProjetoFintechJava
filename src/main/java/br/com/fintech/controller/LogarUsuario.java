package br.com.fintech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.TipoDespesaDAO;
import br.com.fintech.dao.TipoReceitaDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.entity.TipoDespesa;
import br.com.fintech.entity.TipoReceita;
import br.com.fintech.entity.Usuario;
import br.com.fintech.exception.DBException;
import br.com.fintech.factory.DAOFactory;

public class LogarUsuario {
	
	private UsuarioDAO dao ;
	
	public LogarUsuario() {
        dao =  new UsuarioDAO();
    }
	
	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//pega o email e senha do formulario. E retira os espaços do email e transforma todas letras para minusculas
			String email = request.getParameter("emailLogin").replaceAll("\\s+", "").toLowerCase();
			String senha = request.getParameter("senhaLogin");
			//cadastra só o email e senha ao usuario
			Usuario usuario = new Usuario(email, senha);
				//valida o email e senha do usuario pelo banco de dados 
				if (dao.validarUsuario(usuario)) {
					//conecta a seção
					HttpSession session = request.getSession();
					//adiciona o email do usuario a sessão  
					session.setAttribute("user", email);
					
				}else {
					//se usuario nao for validado retorna um erro ao front
					request.setAttribute("erro", "Usuário e/ou senha inválidos");
				}
			} catch (DBException e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um atributo uma mensagem de erro
				request.setAttribute("erro", "Usuário e/ou senha inválidos");
			}catch (Exception e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um atributo uma mensagem de erro
				request.setAttribute("erro", "Usuário e/ou senha inválidos");
			}
		
			return("forward:home.jsp");
	}
}
