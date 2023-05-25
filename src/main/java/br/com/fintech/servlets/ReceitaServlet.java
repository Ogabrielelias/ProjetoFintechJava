package br.com.fintech.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fintech.controller.EditarReceita;
import br.com.fintech.controller.RegistrarReceita;

@WebServlet("/receita")
public class ReceitaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String nome= null;
		switch (acao) {
		case "registrarReceita":
			RegistrarReceita acaoRegistrar = new RegistrarReceita();
			nome=acaoRegistrar.executar(request, response);
			break;
		case "editarReceita": 
			EditarReceita acaoEditar = new EditarReceita();
			nome=acaoEditar.executar(request, response);
			break;
		}
		String[] tipoELink = nome.split(":");
		
		if(tipoELink[0].equals("forward")) {
			request.getRequestDispatcher(tipoELink[1]).forward(request, response);
		}else {
			response.sendRedirect(tipoELink[1]);
		}
	}
}
