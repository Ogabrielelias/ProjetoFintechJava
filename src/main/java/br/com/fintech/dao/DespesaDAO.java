package br.com.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.entity.Despesa;
import br.com.fintech.singleton.ConnectionManager;

public class DespesaDAO {
private Connection conexao;
	
	public DespesaDAO() {
		
	}
	
	public void insert(Despesa despesa) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_despesa (t_transacao_id_transacao,vl_despesa,t_tipo_despesa_id_tipo_despesa) values (?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, despesa.getIdTransacao());
		stmt.setDouble(2, despesa.getValor());
		stmt.setInt(3, despesa.getTipoDespesaId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public Double getDespesaTotal(int id) throws SQLException{
		Double valor = 0.00;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select sum(r.vl_despesa) "
				+ "from t_despesa r "
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
			valor = rs.getDouble("sum(r.vl_despesa)");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return valor;
	}
	
	public Double getDespesaUltimoMes(int id) throws SQLException{
		Double valor = 0.00;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select sum(r.vl_despesa) "
				+ "from t_despesa r "
				+ "inner join t_transacao t "
				+ "on r.t_transacao_id_transacao = t.id_transacao AND t.dt_transacao between  add_months(SYSDATE, - 1)  and SYSDATE "
				+ "inner join t_conta c "
				+ "on t.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			valor = rs.getDouble("sum(r.vl_despesa)");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return valor;
	}
	
	public List<Despesa> getAll() throws SQLException{
		List<Despesa> despesas = new ArrayList<Despesa>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_despesa";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Despesa despesa = new Despesa();
			despesa.setIdTransacao(rs.getInt("t_transacao_id_transacao"));
			despesa.setValor(rs.getDouble("vl_despesa"));
			despesa.setTipoDespesa(rs.getInt("t_tipo_despesa_id_tipo_despesa"));
			
			despesas.add(despesa);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return despesas;
	}
	
	public List<Despesa> getDespesasByUsuarioId(int id) throws SQLException{
		List<Despesa> despesas = new ArrayList<Despesa>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select d.* "
				+ "from t_despesa d "
				+ "inner join t_transacao t "
				+ "on d.t_transacao_id_transacao =  t.id_transacao "
				+ "inner join t_conta c "
				+ "on t.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Despesa despesa = new Despesa();
			despesa.setIdTransacao(rs.getInt("t_transacao_id_transacao"));
			despesa.setValor(rs.getDouble("vl_despesa"));
			despesa.setTipoDespesa(rs.getInt("t_tipo_despesa_id_tipo_despesa"));
		
			despesas.add(despesa);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return despesas;
	}
	
	public Despesa selectById(int id) throws SQLException{
		Despesa despesa = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_despesa where t_transacao_id_transacao = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			despesa = new Despesa();
			despesa.setIdTransacao(rs.getInt("t_transacao_id_transacao"));
			despesa.setValor(rs.getDouble("vl_despesa"));
			despesa.setTipoDespesa(rs.getInt("t_tipo_despesa_id_tipo_despesa"));
			
		}
		rs.close();
		stmt.close();
		conexao.close();
		return despesa;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_despesa where t_transacao_id_despesa = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(Despesa despesa) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_despesa set vl_despesa = ?, t_tipo_despesa_id_tipo_despesa = ? where t_transacao_id_transacao =?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setDouble(1, despesa.getValor());
		stmt.setInt(2, despesa.getTipoDespesaId());
		stmt.setInt(3, despesa.getIdTransacao());
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getNextId() throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		String sql = "select sq_transacao.nextval from dual;";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int id = rs.getInt("nextval");
		
		rs.close();
		stmt.close();
		conexao.close();
		return id;
	}
	
}
