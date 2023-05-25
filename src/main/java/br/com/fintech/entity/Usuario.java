package br.com.fintech.entity;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.fintech.dao.UsuarioDAO;
import br.com.fintech.exception.EmailException;
import br.com.fintech.util.CriptografiaUtils;

public class Usuario {
	
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private Date dtNasc;
	private String sexo;
	
	public Usuario() {
		
	}
	
	public Usuario(int id, String nome, String email, String senha, String cpf, String dtNasc, String sexo) throws Exception {
		super();
		this.id = id;
		this.nome = nome;
		setEmail(email);
		setSenhaCriptograda(senha);
		setCpf(cpf);
		setDtNascByStrg(dtNasc);
		this.sexo = sexo;
	}

	public Usuario(String email, String senha) throws Exception {
		setEmail(email);
		setSenhaCriptograda(senha);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setAutoId() {
		try {
			this.id = new UsuarioDAO().getNextId();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws Exception {
		 Pattern pattern = Pattern.compile("^(.+)@(.+)$", Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern.matcher(email);
		 boolean matchFound = matcher.find();
		  if(matchFound) {
		this.email = email;
		}else {
			throw new EmailException();
		}
		  
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	public void setSenhaCriptograda(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws Exception {
		Pattern pattern = Pattern.compile("^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}$", Pattern.CASE_INSENSITIVE);
		 Matcher matcher = pattern.matcher(cpf);
		 boolean matchFound = matcher.find();
		  if(matchFound) {
			  this.cpf = cpf;
		}else {
		throw new Exception();
	}
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNascByStrg(String dtNasc) {
		String dataString = dtNasc; 
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date dataNasc = null;
		try {
		dataNasc = new Date(fmt.parse(dataString).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		this.dtNasc = dataNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", dtNasc=" + dtNasc + ", sexo=" + sexo + "]";
	}
	
	
	
}
