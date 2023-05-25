package br.com.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.entity.Receita;
import br.com.fintech.singleton.ConnectionManager;

public class ReceitaDAO {
private Connection conexao;
	
	public ReceitaDAO() {
		
	}
	
	public void insert(Receita receita) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_receita (t_transacao_id_transacao,vl_receita,t_tipo_receita_id_tipo_receita) values (?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, receita.getIdTransacao());
		stmt.setDouble(2, receita.getValor());
		stmt.setInt(3, receita.getTipoReceitaId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public Double getReceitaTotal(int id) throws SQLException{
		Double valor = 0.00;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select sum(r.vl_receita) "
				+ "from t_receita r "
				+ "inner join t_transacao t "
				+ "on r.t_transacao_id_transacao = t.id_transacao "
				+ "inner join t_conta c "
				+ "on t.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			valor = rs.getDouble("sum(r.vl_receita)");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return valor;
	}
	
	public Double getReceitaUltimoMes(int id) throws SQLException{
		Double valor = 0.00;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select sum(r.vl_receita) "
				+ "from t_receita r "
				+ "inner join t_transacao t "
				+ "on r.t_transacao_id_transacao = t.id_transacao AND t.dt_transacao between  add_months(SYSDATE, - 1) and SYSDATE "
				+ "inner join t_conta c "
				+ "on t.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			valor = rs.getDouble("sum(r.vl_receita)");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return valor;
	}
	
	public List<Receita> getAll() throws SQLException{
		List<Receita> receitas = new ArrayList<Receita>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_receita";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Receita receita = new Receita();
			receita.setIdTransacao(rs.getInt("t_transacao_id_transacao"));
			receita.setValor(rs.getDouble("vl_receita"));
			receita.setTipoReceita(rs.getInt("t_tipo_receita_id_tipo_receita"));
			
			receitas.add(receita);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return receitas;
	}
	
	
	public List<Receita> getReceitasByUsuarioId(int id) throws SQLException{
		List<Receita> receitas = new ArrayList<Receita>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select r.* "
				+ "from t_receita r "
				+ "inner join t_transacao t "
				+ "on r.t_transacao_id_transacao = t.id_transacao "
				+ "inner join t_conta c "
				+ "on t.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Receita receita = new Receita();
			receita.setIdTransacao(rs.getInt("t_transacao_id_transacao"));
			receita.setValor(rs.getDouble("vl_receita"));
			receita.setTipoReceita(rs.getInt("t_tipo_receita_id_tipo_receita"));
			
			receitas.add(receita);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return receitas;
	}
	
	public Receita selectById(int id) throws SQLException{
		
		Receita receita = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_receita where t_transacao_id_transacao = ? ";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			receita = new Receita();
			receita.setIdTransacao(rs.getInt("t_transacao_id_transacao"));
			receita.setValor(rs.getDouble("vl_receita"));
			receita.setTipoReceita(rs.getInt("t_tipo_receita_id_tipo_receita"));
			
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return receita;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_receita where t_transacao_id_receita = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(Receita receita) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_receita set vl_receita = ?, t_tipo_receita_id_tipo_receita = ? where t_transacao_id_transacao =?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setDouble(1, receita.getValor());
		stmt.setInt(2, receita.getTipoReceitaId());
		stmt.setInt(3, receita.getIdTransacao());
		
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getCurrentId() throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		String sql = "select sq_transacao.currval from dual";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int id = rs.getInt("currval");
		
		rs.close();
		stmt.close();
		conexao.close();
		return id;
	}
	
}
