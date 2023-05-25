package br.com.fintech.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.controller.EditarConta;
import br.com.fintech.controller.ExcluirConta;
import br.com.fintech.controller.ListarContas;
import br.com.fintech.controller.RegistrarConta;

@WebServlet("/conta")
public class ContaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = null;
		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "registrarConta":
			RegistrarConta acaoRegistrar = new RegistrarConta();
			nome = acaoRegistrar.executar(request, response);
			break;
	
		case "editarConta": 
			EditarConta acaoEditar = new EditarConta();
			nome = acaoEditar.executar(request, response);
			break;
			
		case "excluirConta":
			ExcluirConta acaoExcluir = new ExcluirConta();
			nome = acaoExcluir.executar(request, response);
			break;
			
		case "listarConta": 
			ListarContas acaoListar = new ListarContas();
			nome = acaoListar.executar(request,response);
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
