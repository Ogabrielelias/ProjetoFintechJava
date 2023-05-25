package br.com.fintech.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.controller.DeslogarUsuario;
import br.com.fintech.controller.FormRegistrarUsuario;
import br.com.fintech.controller.LogarUsuario;
import br.com.fintech.controller.RegistrarUsuario;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = null;
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "registrarUsuario":
			RegistrarUsuario acaoRegistrar = new RegistrarUsuario();
			nome = acaoRegistrar.executar(request, response);
			break;
			
		case "logarUsuario":
			LogarUsuario acaoLogar = new LogarUsuario();
			nome = acaoLogar.executar(request, response);
			break;
			
		case "deslogarUsuario":
			DeslogarUsuario acaoDeslogar = new DeslogarUsuario();
			nome = acaoDeslogar.executar(request, response);
			break;
		
		case "formRegistrarUsuario":
			FormRegistrarUsuario acaoRegistrarUsuario = new FormRegistrarUsuario();
			nome = acaoRegistrarUsuario.executar(request, response);
			break;
	}
		String[] tipoELink = nome.split(":");
		
		if(tipoELink[0].equals("forward") ) {
			request.getRequestDispatcher(tipoELink[1]).forward(request, response);
		}else {
			response.sendRedirect(tipoELink[1]);
		}
	}
}


