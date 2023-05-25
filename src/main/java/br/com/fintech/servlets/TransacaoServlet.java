package br.com.fintech.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fintech.controller.ExcluirTransacao;
import br.com.fintech.controller.ListarTransacoes;

@WebServlet("/transacao")
public class TransacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String nome = null;
		switch (acao) {
		case "excluirTransacao":
			ExcluirTransacao acaoExcluir = new ExcluirTransacao();
			nome=acaoExcluir.executar(request, response);
			break;
		case "listarTransacao": 
			ListarTransacoes acaoListar = new ListarTransacoes();
			nome=acaoListar.executar(request,response);
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
