package br.com.fintech.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.TipoDespesaDAO;
import br.com.fintech.dao.TipoReceitaDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.entity.TipoDespesa;
import br.com.fintech.entity.TipoReceita;
import br.com.fintech.factory.DAOFactory;

@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//descomente o comentario a baixo e o do final da classe para pegar o tempo de loading das paginas
//		long antes = System.currentTimeMillis();
		//passa a requisição de ServletRequest para HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		//pega a sessão e a url
		HttpSession session = req.getSession();
		String url = req.getRequestURI();
		//valida se o usuario esta logado e se está em uma pagina que um usuario sem login deva estar
		if (session.getAttribute("user") == null  && !url.contains("usuario")  && !url.contains("resources") && !url.contains("home")&& !url.contains("registrarUsuario")&& !url.endsWith("/")) {
			//envia ao front um erro por meio de um atributo
			request.setAttribute("erro", "Entre com o usuário e senha!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}else {
			//caso usuario esteja logado
			try {
				//pega o id do usuario logado pelo email
				String email = (String) session.getAttribute("user");
				UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO(); 
				int id =usuarioDAO.selectIdByEmail(email);
				//pega as contas do usuario usando o id do usuario
				ContaDAO contaDAO =DAOFactory.getContaDAO();
				List<Conta> contas = contaDAO.getAllByUserId(id);
				//pega todas os tipos de receitas
				TipoReceitaDAO trDAO = DAOFactory.getTipoReceitaDAO();
				List<TipoReceita> trs = trDAO.getAll();
				//pega todas os tipos de despesas
				TipoDespesaDAO tdDAO = DAOFactory.getTipoDespesaDAO();
				List<TipoDespesa> tds = tdDAO.getAll();
				//adiciona a sessão uma lista com todos tipos de despesas
				session.setAttribute("tipoDespesas", tds);
				//adiciona a sessão uma lista com todos tipos de receitas
				session.setAttribute("tipoReceitas", trs);
				//adiciona a sessão uma lista com todas contas do usuario
				session.setAttribute("contas", contas);
				//adiciona a sessão o email do usuario
				session.setAttribute("user", email);
				
			} catch (Exception e) {
				//caso caia na exceção printa o erro no console
				e.printStackTrace();
			}
			chain.doFilter(request, response);
		}
		//descomente os comentarios a baixo  para pegar o tempo de loading das paginas
//		long depois = System.currentTimeMillis();
//		Double segundos = (Double) ((depois-antes)/1000.00);
//		System.out.println("Tempo de carregamento: "+segundos+" seg.");
		}

}