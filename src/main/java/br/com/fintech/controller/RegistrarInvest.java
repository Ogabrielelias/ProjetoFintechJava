package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.entity.Investimento;
import br.com.fintech.factory.DAOFactory;

public class RegistrarInvest {

	public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			//Pega os parametros passados pelo formulario
			String nome = request.getParameter("nomeInvest");
			Double valor = Double.valueOf(request.getParameter("valor"));
			String tipo = request.getParameter("tipoInvest");
			String descricao = request.getParameter("descricao");
			String data = request.getParameter("dataAquisicao");
			String codAtivo = request.getParameter("codAtivo");
			int conta = Integer.valueOf(request.getParameter("conta"));
			//pega o proximo id do investimento pelo sql oracle
			InvestimentoDAO investimentoDAO =DAOFactory.getInvestimentoDAO();
			int id = investimentoDAO.getNextId();
			//cria um investimento com os dados
			Investimento investimento = new Investimento(id,nome,valor,tipo,descricao,data,codAtivo,conta);
			//inseri o investimento ao banco de dados
			investimentoDAO.insert(investimento);
			
			System.out.println("Investimento novo registrado!");
			
		} catch (SQLException e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro = "Erro ao registrar Investimento";
			return("redirect:dashboard?erro="+erro);
		}catch (Exception e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro = "Erro ao registrar Investimento";
			return("redirect:dashboard?erro="+erro);
		}
		return("redirect:dashboard");
	}

}
