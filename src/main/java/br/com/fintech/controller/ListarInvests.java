package br.com.fintech.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.entity.Investimento;
import br.com.fintech.factory.DAOFactory;

public class ListarInvests {
public String executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try {
		//conecta a sessão e pega o atributo "user" que contem o email do usuario
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("user");
		//pega o id do usuario usando o email do usuario
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		int id = usuarioDAO.selectIdByEmail(email);
		//pega todas contas do usuario usando o id do usuario
		ContaDAO contaDAO = DAOFactory.getContaDAO();
		List<Conta> contas = contaDAO.getAllByUserId(id);
		//pega todos investimento do usuario usando o id do usuario
		InvestimentoDAO investimentoDAO = DAOFactory.getInvestimentoDAO();
		List<Investimento> investimentos = investimentoDAO.getAllByUsuarioId(id);
		//envia as contas ao Front
		request.setAttribute("contas", contas);
		//envia os investimentos ao Front
		request.setAttribute("investimentos", investimentos);
		//indica ao front qual a pagina atual que ele está
		request.setAttribute("currPage", "listarInvestimentos");
		
	}catch(Exception e ) {
		//caso caia na excecao irá printa-lá no console
		e.printStackTrace();
		//caso caia na excecao ira enviar ao front por meio de um atributp uma mensagem de erro
		request.setAttribute("erro", "Erro ao listar suas contas!");
	}
	return("forward:investimentos.jsp");
	}
}
