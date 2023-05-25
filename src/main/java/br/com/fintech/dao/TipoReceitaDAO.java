package br.com.fintech.dao;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.entity.TipoReceita;
import br.com.fintech.singleton.ConnectionManager;

public class TipoReceitaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
private Connection conexao;
	
	public TipoReceitaDAO() {
		
	}
	
	public void insert(TipoReceita tipoReceita) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_tipo_receita (id_tipo_receita, ds_tipo_receita) values (?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, tipoReceita.getId());
		stmt.setString(2, tipoReceita.getTipoReceita());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public List<TipoReceita> getAll() throws SQLException{
		List<TipoReceita> tipoReceitas = new ArrayList<TipoReceita>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_tipo_receita";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			TipoReceita tipoReceita = new TipoReceita();
			tipoReceita.setId(rs.getInt("id_tipo_receita"));
			tipoReceita.setTipoReceita(rs.getString("ds_tipo_receita"));
			
			tipoReceitas.add(tipoReceita);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return tipoReceitas;
	}
	
	public TipoReceita selectById(int id) throws SQLException{
		TipoReceita tipoReceita = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_tipo_receita where id_tipo_receita = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			tipoReceita = new TipoReceita();
			tipoReceita.setId(rs.getInt("id_tipo_receita"));
			tipoReceita.setTipoReceita(rs.getString("ds_tipo_receita"));
			
		}
		rs.close();
		stmt.close();
		conexao.close();
		return tipoReceita;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_tipo_receita where id_tipo_receita = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(TipoReceita tipoReceita) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_tipo_receita set ds_tipo_receita = ? where id_tipo_receita =?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, tipoReceita.getTipoReceita());
		stmt.setInt(2, tipoReceita.getId());
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getNextId() throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		int id=0;
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
