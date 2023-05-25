package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.factory.DAOFactory;

public class RegistrarConta {
	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//Pega os parametros passados pelo formulario
			double saldo = Double.valueOf(request.getParameter("saldo"));
			String instFinan = request.getParameter("instFinan");
			String tipoConta = request.getParameter("tipoConta");
			String descricaoConta = request.getParameter("descricaoConta");
			//conecta com a sessao
			HttpSession session = request.getSession();
			//pega email do usuario
			String email = (String) session.getAttribute("user");
			//pega o id do usuario pelo email
			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			int idUsuario = usuarioDAO.selectIdByEmail(email);
			//pega o proximo id da conta no sql oracle
			ContaDAO contaDAO = DAOFactory.getContaDAO();
			int idConta = contaDAO.getNextId();
			//cria uma conta com os dados
			Conta conta = new Conta(idConta, saldo, instFinan,tipoConta,descricaoConta,idUsuario);
			//inseri a conta no banco de dados
			contaDAO.insert(conta);
			
			System.out.println("Nova Conta Cadastrada");
			
			} catch (SQLException e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
				String erro="Erro ao cadastrar conta!";
				return "redirect:conta?acao=listarConta&erro="+erro;
			}catch (Exception e) {
				//caso caia na excecao irá printa-lá no console
				e.printStackTrace();
				//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
				String erro="Erro ao cadastrar conta!";
				return "redirect:conta?acao=listarConta&erro="+erro;
			}
			return "redirect:conta?acao=listarConta";
	}
}
