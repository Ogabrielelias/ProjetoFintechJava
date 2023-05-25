package br.com.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.entity.Investimento;
import br.com.fintech.singleton.ConnectionManager;

public class InvestimentoDAO {
private Connection conexao;
	
	public InvestimentoDAO() {
		
	}
	
	public void insert(Investimento investimento) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_investimento (id_investimento, nm_investimento, vl_investimento, ds_tipo_investimento, ds_investimento, dt_aquisicao, ds_codigo_ativo, t_conta_id_conta) values (?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, investimento.getId());
		stmt.setString(2, investimento.getNomeInvestimento());
		stmt.setDouble(3, investimento.getValorInvestido());
		stmt.setString(4, investimento.getTipoInvestimento());
		stmt.setString(5, investimento.getDescricao());
		stmt.setDate(6, investimento.getDtAquisicao());
		stmt.setString(7, investimento.getCodAtivo());
		stmt.setInt(8, investimento.getContaId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public Double getInvestimentosTotal(int id) throws SQLException{
		Double valor = 0.00;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select sum(r.vl_investimento) "
				+ "from t_investimento r "
				+ "inner join t_conta c "
				+ "on r.t_conta_id_conta = c.id_conta "
				+ "inner join t_usuario u "
				+ "on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			valor = rs.getDouble("sum(r.vl_investimento)");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return valor;
	}
	
	public List<Investimento> getAllByUsuarioId(int id) throws SQLException{
		List<Investimento> investimentos = new ArrayList<Investimento>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select i.* from t_investimento i inner join t_conta c on i.t_conta_id_conta = c.id_conta inner join t_usuario u on c.t_usuario_id_usuario = u.id_usuario and u.id_usuario = ? order by id_investimento";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Investimento investimento = new Investimento();
			investimento.setId(rs.getInt("id_investimento"));
			investimento.setNomeInvestimento(rs.getString("nm_investimento"));
			investimento.setDescricao(rs.getString("ds_investimento"));
			investimento.setValorInvestido(rs.getDouble("vl_investimento"));
			investimento.setTipoInvestimento(rs.getString("ds_tipo_investimento"));
			investimento.setDtAquisicao(rs.getDate("dt_aquisicao"));
			investimento.setCodAtivo(rs.getString("ds_codigo_ativo"));
			investimento.setConta(rs.getInt("t_conta_id_conta"));
			
			investimentos.add(investimento);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return investimentos;
	}
	
	public List<Investimento> getAll() throws SQLException{
		List<Investimento> investimentos = new ArrayList<Investimento>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_investimento";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Investimento investimento = new Investimento();
			investimento.setId(rs.getInt("id_investimento"));
			investimento.setNomeInvestimento(rs.getString("nm_investimento"));
			investimento.setDescricao(rs.getString("ds_investimento"));
			investimento.setValorInvestido(rs.getDouble("vl_investimento"));
			investimento.setTipoInvestimento(rs.getString("ds_tipo_investimento"));
			investimento.setDtAquisicao(rs.getDate("dt_aquisicao"));
			investimento.setCodAtivo(rs.getString("ds_codigo_ativo"));
			investimento.setConta(rs.getInt("t_conta_id_conta"));
			
			investimentos.add(investimento);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return investimentos;
	}
	
	public Investimento selectById(int id) throws SQLException{
		Investimento investimento = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_investimento where id_investimento = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			investimento = new Investimento();
			investimento.setId(rs.getInt("id_investim"));
			investimento.setNomeInvestimento(rs.getString("nm_investimento"));
			investimento.setDescricao(rs.getString("ds_investimento"));
			investimento.setValorInvestido(rs.getDouble("vl_investimento"));
			investimento.setTipoInvestimento(rs.getString("ds_tipo_investimento"));
			investimento.setDtAquisicao(rs.getDate("dt_aquisicao"));
			investimento.setCodAtivo(rs.getString("ds_codigo_ativo"));
			investimento.setConta(rs.getInt("t_conta_id_conta"));
			
		}
		rs.close();
		stmt.close();
		conexao.close();
		return investimento;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_investimento where id_investimento = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(Investimento investimento) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_investimento set nm_investimento = ?, vl_investimento = ?, ds_tipo_investimento = ?, ds_investimento = ?, dt_aquisicao = ?, ds_codigo_ativo = ?, t_conta_id_conta = ? where id_investimento = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, investimento.getNomeInvestimento());
		stmt.setDouble(2, investimento.getValorInvestido());
		stmt.setString(3, investimento.getTipoInvestimento());
		stmt.setString(4, investimento.getDescricao());
		stmt.setDate(5, investimento.getDtAquisicao());
		stmt.setString(6, investimento.getCodAtivo());
		stmt.setInt(7, investimento.getContaId());
		stmt.setInt(8, investimento.getId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getNextId() throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		int id = 0;
		String sql = "select sq_investimento.nextval from dual";
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
