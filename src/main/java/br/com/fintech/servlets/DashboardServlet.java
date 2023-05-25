package br.com.fintech.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fintech.controller.Dashboard;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = null;
		Dashboard dashboard = new Dashboard();
		nome = dashboard.execute(request, response);
		
		request.getRequestDispatcher(nome).forward(request, response);
	
	}

}
