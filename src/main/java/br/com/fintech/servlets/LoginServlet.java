package br.com.fintech.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.entity.Conta;
import br.com.fintech.entity.TipoDespesa;
import br.com.fintech.entity.TipoReceita;
import br.com.fintech.entity.Usuario;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.TipoDespesaDAO;
import br.com.fintech.dao.TipoReceitaDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.exception.DBException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao;
  
	public LoginServlet() {
        dao = new UsuarioDAO();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("emailLogin").replaceAll("\\s+", "").toLowerCase();
		String senha = request.getParameter("senhaLogin");
		
		
		try {
			Usuario usuario = new Usuario(email, senha);
			if (dao.validarUsuario(usuario)) {
				HttpSession session = request.getSession();
				ContaDAO contaDAO =new ContaDAO();
				List<Conta> contas = new ArrayList<Conta>();
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				int id =usuarioDAO.selectIdByEmail(email);
				contas = contaDAO.getAllByUserId(id);
				
				TipoReceitaDAO trDAO = new TipoReceitaDAO();
				List<TipoReceita> trs = trDAO.getAll();
				TipoDespesaDAO tdDAO = new TipoDespesaDAO();
				List<TipoDespesa> tds = tdDAO.getAll();
				
				session.setAttribute("tipoDespesas", tds);
				session.setAttribute("tipoReceitas", trs);
				session.setAttribute("contas", contas);
				session.setAttribute("user", email);
			}else {
				request.setAttribute("erro", "Usuário e/ou senha inválidos");
			}
		} catch (DBException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}

}