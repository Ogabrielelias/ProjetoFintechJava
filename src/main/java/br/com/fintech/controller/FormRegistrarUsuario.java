package br.com.fintech.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormRegistrarUsuario {
	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
			//Form para redirecionar a tela de registro do usuario
			// poderia-se usar esta classe para carregar dados que seriam utilizados no Form
			return "forward:register.jsp";
	}
}
