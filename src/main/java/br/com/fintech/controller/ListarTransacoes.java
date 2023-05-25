package br.com.fintech.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fintech.dao.ContaDAO;
import br.com.fintech.dao.DespesaDAO;
import br.com.fintech.dao.ReceitaDAO;
import br.com.fintech.dao.TipoDespesaDAO;
import br.com.fintech.dao.TipoReceitaDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.entity.Despesa;
import br.com.fintech.entity.Receita;
import br.com.fintech.entity.TipoDespesa;
import br.com.fintech.entity.TipoReceita;
import br.com.fintech.entity.Transacao;
import br.com.fintech.entity.Usuario;
import br.com.fintech.factory.DAOFactory;

public class ListarTransacoes {

	public String executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			//conecta a sessão
			HttpSession session = request.getSession();
			ContaDAO contaDAO = DAOFactory.getContaDAO();
			TipoReceitaDAO tipoReceitaDAO = DAOFactory.getTipoReceitaDAO();
			TipoDespesaDAO tipoDespesaDAO = DAOFactory.getTipoDespesaDAO();
			//pega o atributo "contas" que contem as contas do usuario
			List<Conta> contas = (List<Conta>) session.getAttribute("contas");
			//pega os atributos "tipoReceitas" e "tipoDespesas" que contem todos tipos de receitas e despesas 
			List<TipoReceita> tiposReceitas = (List<TipoReceita>) session.getAttribute("tipoReceitas");
			List<TipoDespesa> tiposDespesas = (List<TipoDespesa>) session.getAttribute("tipoDespesas");
			//pega o atributo "user" que contem o email do usuario
			String email = (String) session.getAttribute("user");
			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			int id = usuarioDAO.selectIdByEmail(email);
			//se o atributo "contas" estiver vazio(pois ocorre dos atributos to tipo List<> sairem da sessão sozinhos), 
			//pegara as contas usando o id do usuario
			if (contas == null) {
				contas = contaDAO.getAllByUserId(id);
				session.setAttribute("contas", contas);
			}
			//se o atributo "tipoReceitas" estiver vazio(pois ocorre dos atributos to tipo List<> sairem da sessão sozinhos), 
			//pegara os tipo de receitas
			if (tiposReceitas == null) {
				tiposReceitas = tipoReceitaDAO.getAll();
				session.setAttribute("tipoReceitas", tiposReceitas);
			}
			//se o atributo "tipoDespesas" estiver vazio(pois ocorre dos atributos to tipo List<> sairem da sessão sozinhos), 
			//pegara os tipo de despesas
			if (tiposDespesas == null) {
				tiposDespesas = tipoDespesaDAO.getAll();
				session.setAttribute("tipoDespesas", tiposDespesas);
			}
			
			//pega as transações do usuario usando o id do usuario
			TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();
			List<Transacao> transacoesUsuario = transacaoDAO.getTransacaoByUsuarioId(id);
			//pega as receitas do usuario usando o id do usuario
			ReceitaDAO receitaDAO = DAOFactory.getReceitaDAO();
			List<Receita> receitasUsuario = receitaDAO.getReceitasByUsuarioId(id);
			//pega as despesas do usuario usando o id do usuario
			DespesaDAO despesaDAO = DAOFactory.getDespesaDAO();
			List<Despesa> despesasUsuario = despesaDAO.getDespesasByUsuarioId(id);
			//indica ao front qual a pagina atual que ele está
			request.setAttribute("currPage", "listarTransacoes");
			//envia as despesas ao Front
			request.setAttribute("despesas", despesasUsuario);
			//envia as transacoes ao Front
			request.setAttribute("transacoes", transacoesUsuario);
			//envia as receitas ao Front
			request.setAttribute("receitas", receitasUsuario);

		} catch (Exception e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um atributo uma mensagem de erro
			request.setAttribute("erro", "Erro ao listar suas transações!");
		}
		return ("forward:transacoes.jsp");

	}

}
