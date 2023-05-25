package br.com.fintech.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeslogarUsuario {
	
	public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//pega os dados da sessão e invalida eles, assim deslogando o usuario
			HttpSession session = request.getSession();
			session.invalidate();
			
			return "forward:home.jsp";
		}
	
	}
