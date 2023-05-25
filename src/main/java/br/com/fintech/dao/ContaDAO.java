package br.com.fintech.dao;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fintech.entity.Conta;
import br.com.fintech.singleton.ConnectionManager;

public class ContaDAO implements Serializable{
	private static final long serialVersionUID = 1L;
private Connection conexao;
	
	public ContaDAO() {
		
	}
	
	public void insert(Conta conta) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_conta (id_conta,vl_saldo_conta,ds_instituicao_financeira,ds_tipo_conta,ds_descricao,t_usuario_id_usuario) values (?,?,?,?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, conta.getId());
		stmt.setDouble(2, conta.getSaldo());
		stmt.setString(3, conta.getInstFinanceira());
		stmt.setString(4, conta.getTipoConta());
		stmt.setString(5, conta.getDescricao());
		stmt.setInt(6, conta.getUsuario());
		
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public Double getSaldoTotal(int idUsuario) throws SQLException {
		Double saldo = 0.00;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select sum(vl_saldo_conta) from t_conta where t_usuario_id_usuario = ?";
		
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, idUsuario);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			saldo=(rs.getDouble("sum(vl_saldo_conta)"));
		}
		
		conexao.close();
		return saldo;
	}
	
	public List<Conta> getMaioresContas(int userId) throws Exception{
		List<Conta> contas = new ArrayList<Conta>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from (select * from t_conta where t_usuario_id_usuario = ? order by vl_saldo_conta desc)where rownum <= 3 ";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();

		while(rs.next()) {
			Conta conta = new Conta();
			conta.setId(rs.getInt("id_conta"));
			conta.setSaldo(rs.getDouble("vl_saldo_conta"));
			conta.setInstFinanceira(rs.getString("ds_instituicao_financeira"));
			conta.setTipoConta(rs.getString("ds_tipo_conta"));
			conta.setDescricao(rs.getString("ds_descricao"));
			conta.setUsuario(rs.getInt("t_usuario_id_usuario"));
		
			contas.add(conta);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
			
		return contas;
	}
	
	public List<Conta> getAllByUserId(int userId) throws Exception{
		List<Conta> contas = new ArrayList<Conta>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_conta where t_usuario_id_usuario = ? order by id_conta";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, userId);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Conta conta = new Conta();
			conta.setId(rs.getInt("id_conta"));
			conta.setSaldo(rs.getDouble("vl_saldo_conta"));
			conta.setInstFinanceira(rs.getString("ds_instituicao_financeira"));
			conta.setTipoConta(rs.getString("ds_tipo_conta"));
			conta.setDescricao(rs.getString("ds_descricao"));
			conta.setUsuario(rs.getInt("t_usuario_id_usuario"));
			
			contas.add(conta);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
			
		return contas;
	}
	
	public List<Conta> getAll() throws Exception{
		List<Conta> contas = new ArrayList<Conta>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_conta";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Conta conta = new Conta();
			conta.setId(rs.getInt("id_conta"));
			conta.setSaldo(rs.getDouble("vl_saldo_conta"));
			conta.setInstFinanceira(rs.getString("ds_instituicao_financeira"));
			conta.setTipoConta(rs.getString("ds_tipo_conta"));
			conta.setDescricao(rs.getString("ds_descricao"));
			conta.setUsuario(rs.getInt("t_usuario_id_usuario"));
			
			contas.add(conta);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
			
		return contas;
	}
	
	public Conta selectById(int id) throws SQLException{
		Conta conta = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_conta where id_conta = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			conta = new Conta();
			conta.setId(rs.getInt("id_conta"));
			conta.setSaldo(rs.getDouble("vl_saldo_conta"));
			conta.setInstFinanceira(rs.getString("ds_instituicao_financeira"));
			conta.setTipoConta(rs.getString("ds_tipo_conta"));
			conta.setDescricao(rs.getString("ds_descricao"));
			conta.setUsuario(rs.getInt("t_usuario_id_usuario"));
			
		}
		rs.close();
		stmt.close();
		conexao.close();
		
		return conta;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_conta where id_conta = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(Conta conta) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_conta set vl_saldo_conta = ?, ds_instituicao_financeira = ?, ds_tipo_conta = ? , ds_descricao = ?, t_usuario_id_usuario = ? where id_conta =?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setDouble(1, conta.getSaldo());
		stmt.setString(2, conta.getInstFinanceira());
		stmt.setString(3, conta.getTipoConta());
		stmt.setString(4, conta.getDescricao());
		stmt.setInt(5, conta.getUsuario());
		stmt.setInt(6, conta.getId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getNextId() throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		int id = 0;
		String sql = "select sq_conta.nextval from dual";
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
	
	public void adicionarSaldo(Conta conta, Double valor) throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_conta set vl_saldo_conta = vl_saldo_conta + ? where id_conta = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setDouble(1, valor);
		stmt.setInt(2, conta.getId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void retirarSaldo(Conta conta, Double valor) throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_conta set vl_saldo_conta = vl_saldo_conta - ? where id_conta = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setDouble(1, valor);
		stmt.setInt(2, conta.getId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void updateSaldo(Conta conta) throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_conta set vl_saldo_conta = vl_saldo_conta+(select SUM(r.vl_receita) from t_receita r inner join t_transacao t on t.t_conta_id_conta = ? and r.t_transacao_id_transacao = t.id_transacao)-(select SUM(d.vl_despesa) from t_despesa d inner join t_transacao t on t.t_conta_id_conta = ? and d.t_transacao_id_transacao = t.id_transacao) where id_conta = ? ";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		
		stmt.setInt(1, conta.getId());
		stmt.setInt(2, conta.getId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
}
