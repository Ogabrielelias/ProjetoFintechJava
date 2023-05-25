package br.com.fintech.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.controller.EditarInvest;
import br.com.fintech.controller.ExcluirInvest;
import br.com.fintech.controller.ListarInvests;
import br.com.fintech.controller.RegistrarInvest;

@WebServlet("/investimento")
public class InvestimentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String acao = request.getParameter("acao");
		String nome = null;
		switch (acao) {
		case "registrarInvest":
			RegistrarInvest acaoRegistrar = new RegistrarInvest();
			nome = acaoRegistrar.executar(request, response);
			break;
		case "editarInvest": 
			EditarInvest acaoEditar = new EditarInvest();
			nome =acaoEditar.executar(request, response);
			break;
		case "excluirInvest":
			ExcluirInvest acaoExcluir = new ExcluirInvest();
			nome =acaoExcluir.executar(request, response);
			break;
		case "listarInvest": 
			ListarInvests acaoListar = new ListarInvests();
			nome =acaoListar.executar(request,response);
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


