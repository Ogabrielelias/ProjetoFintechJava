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
import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.dao.ReceitaDAO;
import br.com.fintech.dao.TipoDespesaDAO;
import br.com.fintech.dao.TipoReceitaDAO;
import br.com.fintech.dao.TransacaoDAO;
import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.entity.Conta;
import br.com.fintech.entity.Despesa;
import br.com.fintech.entity.Receita;
import br.com.fintech.entity.Transacao;
import br.com.fintech.factory.DAOFactory;

public class Dashboard {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Pegar o id do usuario que está logado, diretamento do banco de dados, usando o email que esta salvo como atributo na "session"
			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("user");
			UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
			int id = usuarioDAO.selectIdByEmail(email);
			
			//Pegar o Saldo somado de todas as contas do usuario que está logado
			ContaDAO contaDAO = new ContaDAO();
			Double saldoTotal = contaDAO.getSaldoTotal(id);
			//Pegar as 3 contas com maiores saldos do usuario
			List<Conta> contas = contaDAO.getMaioresContas(id);
			
			//Pegar o valor somado das receitas do ultimo mes do usuario logado
			ReceitaDAO receitaDAO = DAOFactory.getReceitaDAO();
			Double receitaUltimoMes = receitaDAO.getReceitaUltimoMes(id);
			//Pegar o valor somado de todas as receitas do usuario logado
			Double receitaTotal = receitaDAO.getReceitaTotal(id);
			
			//Pegar o valor somado das despesas do ultimo mes do usuario logado
			DespesaDAO despesaDAO = DAOFactory.getDespesaDAO();
			Double despesaUltimoMes = despesaDAO.getDespesaUltimoMes(id);
			//Pegar o valor somado de todas as despesas do usuario logado
			Double despesaTotal = despesaDAO.getDespesaTotal(id);
			
			//Pegar o valor somado dos investimentos do usuario logado
			InvestimentoDAO investimentoDAO = DAOFactory.getInvestimentoDAO();
			Double investimentos = investimentoDAO.getInvestimentosTotal(id);
			
			//Pegar as ultimas 3 transações(filtrando pela data mais recente) que o usuario logado efetuou 
			TransacaoDAO transacaoDAO = DAOFactory.getTransacaoDAO();
			List<Transacao> ultimasTransacoes = transacaoDAO.getUltimasTransacoes(id);
			
			//valida se as ultimas transacoes pegas sao despesa ou receita e separa elas em listas
			List<Receita> receitas = new ArrayList<Receita>();
			List<Despesa> despesas = new ArrayList<Despesa>();
			
			for (Transacao t : ultimasTransacoes) {
				Receita receita = receitaDAO.selectById(t.getId());
				Despesa despesa = despesaDAO.selectById(t.getId());
				
				if(receita!=null) {
					receitas.add(receita);
				}else if(despesa!= null) {
					despesas.add(despesa);
				}
			}
			
			//pega todos tipos de receitas e despesas para serem apresentadas no front com as suas respectivas transacoes
			TipoReceitaDAO tipoReceitaDAO = new TipoReceitaDAO();
			TipoDespesaDAO tipoDespesaDAO = new TipoDespesaDAO();
			request.setAttribute("tipoDespesa", tipoDespesaDAO.getAll());
			request.setAttribute("tipoReceita", tipoReceitaDAO.getAll());
			// envia ao front as transacoes/despesas/receitas para serem inseridas no dashboard do usuario
			request.setAttribute("receitas", receitas);
			request.setAttribute("despesas", despesas);
			request.setAttribute("transacoes", ultimasTransacoes);
			//envia ao front os valores totais de receitas,despesas e investimentos do usuario
			request.setAttribute("receitaTotal", receitaTotal);
			request.setAttribute("despesaTotal", despesaTotal);
			request.setAttribute("investTotal", investimentos);
			//envia ao front as 3 contas de maior saldo do usuario 
			request.setAttribute("maioresContas", contas);
			//envia ao front o saldo total do usuario
			request.setAttribute("saldoTotal", saldoTotal);
			//envia ao fron a soma das receitas e despesas realizados no ultimo mês pelo usuario 
			request.setAttribute("despesaMes", despesaUltimoMes);
			request.setAttribute("receitaMes", receitaUltimoMes);
			//indica ao front qual a pagina atual que ele está
			request.setAttribute("currPage", "listarDashboard");
		} catch (Exception e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front uma mensagem de erro
			request.setAttribute("erro", "Erro no carregamento do dashboard!");
		}
		
		return "dashboard.jsp";
	}
}
