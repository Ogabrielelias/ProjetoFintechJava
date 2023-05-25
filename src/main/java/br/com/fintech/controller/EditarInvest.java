package br.com.fintech.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fintech.dao.InvestimentoDAO;
import br.com.fintech.entity.Investimento;
import br.com.fintech.factory.DAOFactory;

public class EditarInvest {
public String executar(HttpServletRequest request, HttpServletResponse response) throws IOException {
	try {
		//Pega os parametros passados pelo formulario
		int id = Integer.valueOf(request.getParameter("id"));
		String nome = request.getParameter("nomeInvest");
		Double valor = Double.valueOf(request.getParameter("valor"));
		String tipo = request.getParameter("tipoInvest");
		String descricao = request.getParameter("descricao");
		String data = request.getParameter("dataAquisicao");
		String codAtivo = request.getParameter("codAtivo");
		int conta = Integer.valueOf(request.getParameter("conta"));
		//cria um investimento com os parametros
		Investimento investimento = new Investimento(id, nome, valor,tipo,descricao,data,codAtivo,conta);
		InvestimentoDAO investimentoDAO = DAOFactory.getInvestimentoDAO();
		//usa investimento criado para editar um ja existente
		investimentoDAO.update(investimento);
		
		System.out.println("Investimento de id= "+id+" alterado!");
		
		}catch(SQLException e) {
			//caso caia na excecao irá printa-lá no console
			e.printStackTrace();
			//caso caia na excecao ira enviar ao front por meio de um parametro uma mensagem de erro
			String erro = "Erro ao edita seu investimento!";
			return("redirect:transacao?acao=listarTransacao&erro="+erro);
		}
		return("redirect:transacao?acao=listarTransacao");
	}
}
