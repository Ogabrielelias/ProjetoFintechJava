package br.com.fintech.dao;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.entity.TipoDespesa;
import br.com.fintech.singleton.ConnectionManager;

public class TipoDespesaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	private Connection conexao;
	
	public TipoDespesaDAO() {
		
	}
	
	public void insert(TipoDespesa tipoDespesa) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_tipo_despesa (id_tipo_despesa, ds_tipo_despesa) values (?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, tipoDespesa.getId());
		stmt.setString(2, tipoDespesa.getTipoDespesa());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public List<TipoDespesa> getAll() throws SQLException{
		List<TipoDespesa> tipoDespesas = new ArrayList<TipoDespesa>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_tipo_despesa";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			TipoDespesa tipoDespesa = new TipoDespesa();
			tipoDespesa.setId(rs.getInt("id_tipo_despesa"));
			tipoDespesa.setTipoDespesa(rs.getString("ds_tipo_despesa"));
			
			tipoDespesas.add(tipoDespesa);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return tipoDespesas;
	}
	
	public TipoDespesa selectById(int id) throws SQLException{
		TipoDespesa tipoDespesa = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_tipo_despesa where id_tipo_despesa = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			tipoDespesa = new TipoDespesa();
			tipoDespesa.setId(rs.getInt("id_tipo_despesa"));
			tipoDespesa.setTipoDespesa(rs.getString("ds_tipo_despesa"));
			
		}
		rs.close();
		stmt.close();
		conexao.close();
		return tipoDespesa;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_tipo_despesa where id_tipo_despesa = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(TipoDespesa tipoDespesa) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_tipo_despesa set ds_tipo_despesa = ? where id_tipo_despesa =?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, tipoDespesa.getTipoDespesa());
		stmt.setInt(2, tipoDespesa.getId());
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getNextId() throws SQLException {
		int id = 0;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql = "select sq_tipo_receita.nextval from dual";
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
