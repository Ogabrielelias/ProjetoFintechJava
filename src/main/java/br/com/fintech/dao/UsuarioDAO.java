package br.com.fintech.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fintech.entity.Usuario;
import br.com.fintech.exception.DBException;
import br.com.fintech.singleton.ConnectionManager;

public class UsuarioDAO {

	private Connection conexao;
	
	public UsuarioDAO() {
		
	}
	
	public boolean validarUsuario(Usuario usuario) throws DBException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL_USUARIO = ? AND DS_SENHA_USUARIO = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();

			if (rs.next()){
				
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao validar usuario!");
		}finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	
	public void insert(Usuario usuario) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="insert into t_usuario (id_usuario,nm_usuario,ds_email_usuario,ds_senha_usuario,ds_cpf,dt_nascimento,ds_sexo) values (?,?,?,?,?,?,?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, usuario.getId());
		stmt.setString(2, usuario.getNome());
		stmt.setString(3, usuario.getEmail());
		stmt.setString(4, usuario.getSenha());
		stmt.setString(5, usuario.getCpf());
		stmt.setDate(6, usuario.getDtNasc());
		stmt.setString(7, usuario.getSexo());
		
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public List<Usuario> getAll() throws Exception{
		List<Usuario> usuarios = new ArrayList<Usuario>();
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_usuario order by nm_usuario";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(rs.getInt("id_usuario"));
			usuario.setNome(rs.getString("nm_usuario"));
			usuario.setEmail(rs.getString("ds_email_usuario"));
			usuario.setSenha(rs.getString("ds_senha_usuario"));
			usuario.setCpf(rs.getString("ds_cpf"));
			usuario.setDtNasc(rs.getDate("dt_nascimento"));
			usuario.setSexo(rs.getString("ds_sexo"));
			
			usuarios.add(usuario);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		return usuarios;
	}
	
	public Usuario selectById(int id) throws Exception{
		Usuario usuario = null;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select * from t_usuario where id_usuario = ? order by nm_usuario";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			usuario = new Usuario();
			usuario.setId(rs.getInt("id_usuario"));
			usuario.setNome(rs.getString("nm_usuario"));
			usuario.setEmail(rs.getString("ds_email_usuario"));
			usuario.setSenha(rs.getString("ds_senha_usuario"));
			usuario.setCpf(rs.getString("ds_cpf"));
			usuario.setDtNasc(rs.getDate("dt_nascimento"));
			usuario.setSexo(rs.getString("ds_sexo"));
		}
		rs.close();
		stmt.close();
		conexao.close();
		return usuario;
	}
	
	public int selectIdByEmail(String email) throws Exception{
		int usuarioId = 0;
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="select id_usuario from t_usuario where ds_email_usuario = ? ";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			usuarioId=rs.getInt("id_usuario");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return usuarioId;
	}
	
	public void delete(int id) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql="delete from t_usuario where id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}
	
	public void update(Usuario usuario) throws SQLException{
		conexao = ConnectionManager.getInstance().getConnection();
		String sql ="update t_usuario set nm_usuario = ?,ds_email_usuario = ?,ds_senha_usuario = ? where id_usuario = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getEmail());
		stmt.setString(3, usuario.getSenha());
		stmt.setInt(4, usuario.getId());
		
		stmt.execute();
		stmt.close();
		conexao.close();
		
	}
	
	public int getNextId() throws SQLException {
		conexao = ConnectionManager.getInstance().getConnection();
		int id = 0;
		String sql = "select sq_usuario.nextval from dual";
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
