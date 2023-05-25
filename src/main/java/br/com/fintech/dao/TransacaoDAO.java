package br.com.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fintech.entity.Transacao;
import br.com.fintech.singleton.ConnectionManager;

public class TransacaoDAO {
private Connection conexao;
	
	public TransacaoDAO() {
		
	}
	
	public void insert(Transacao transacao) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_transacao (id_transacao, dt_transacao, ds_descricao_transacao, t_conta_id_conta) values (?,?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, transacao.getId());
		stmt.setDate(2, transacao.getDtTransacao());
		stmt.setString(3, transacao.getDescricao());
		stmt.setInt(4, transacao.getContaId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public List<Transacao> getUltimasTransacoes(int userId) throws Exception{
		List<Transacao> transacoes = new ArrayList<Transacao>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from (select t.* from t_transacao t "
				+ "inner join t_conta c "
				+ "	on t.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ? order by dt_transacao desc) where rownum <= 3 ";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			Transacao transacao = new Transacao();
			transacao.setId(rs.getInt("id_transacao"));
			transacao.setDtTransacao(rs.getDate("dt_transacao"));
			transacao.setDescricao(rs.getString("ds_descricao_transacao"));
			transacao.setConta(rs.getInt("t_conta_id_conta"));
			
			transacoes.add(transacao);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
			
		return transacoes;
	}
	
	public List<Transacao> getAllByContaId(int id) throws SQLException{
		List<Transacao> transacoes = new ArrayList<Transacao>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_transacao where t_conta_id_conta = ? order by id_transacao";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		try {
		while(rs.next()) {
			Transacao transacao = new Transacao();
			transacao.setId(rs.getInt("id_transacao"));
			transacao.setDtTransacao(rs.getDate("dt_transacao"));
			transacao.setDescricao(rs.getString("ds_descricao_transacao"));
			transacao.setConta(rs.getInt("t_conta_id_conta"));
			
			transacoes.add(transacao);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return transacoes;
	}
	
	public List<Transacao> getTransacaoByUsuarioId(int id) throws SQLException{
		List<Transacao> transacoes = new ArrayList<Transacao>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select t.* "
				+ "from t_transacao t "
				+ "inner join t_conta c "
				+ "on t.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ? order by t.id_transacao";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		try {
		while(rs.next()) {
			Transacao transacao = new Transacao();
			transacao.setId(rs.getInt("id_transacao"));
			transacao.setDtTransacao(rs.getDate("dt_transacao"));
			transacao.setDescricao(rs.getString("ds_descricao_transacao"));
			transacao.setConta(rs.getInt("t_conta_id_conta"));
			
			transacoes.add(transacao);
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return transacoes;
	}
	
	public List<Transacao> getAll() throws SQLException{
		List<Transacao> transacoes = new ArrayList<Transacao>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_transacao";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		try {
		while(rs.next()) {
			Transacao transacao = new Transacao();
			transacao.setId(rs.getInt("id_transacao"));
			transacao.setDtTransacao(rs.getDate("dt_transacao"));
			transacao.setDescricao(rs.getString("ds_descricao_transacao"));
			transacao.setConta(rs.getInt("t_conta_id_conta"));
			
			transacoes.add(transacao);
		}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return transacoes;
	}
	
	public Transacao selectById(int id) throws SQLException{
		Transacao transacao = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_transacao where id_transacao = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		try {
		
		while(rs.next()) {
			transacao = new Transacao();
			transacao.setId(rs.getInt("id_transacao"));
			transacao.setDtTransacao(rs.getDate("dt_transacao"));
			transacao.setDescricao(rs.getString("ds_descricao_transacao"));
			transacao.setConta(rs.getInt("t_conta_id_conta"));
			
		}
		} catch (SQLException e) {
		
			e.printStackTrace();
		} 
		rs.close();
		stmt.close();
		conexao.close();
		return transacao;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_transacao where id_transacao = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(Transacao transacao) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_transacao set dt_transacao = ?, ds_descricao_transacao = ?, t_conta_id_conta = ? where id_transacao = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setDate(1, transacao.getDtTransacao());
		stmt.setString(2, transacao.getDescricao());
		stmt.setInt(3, transacao.getContaId());
		stmt.setInt(4, transacao.getId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getNextId() throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		int id = 0;
		String sql = "select sq_transacao.nextval from dual";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			id = rs.getInt("nextval");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return id;
	}
	
}
