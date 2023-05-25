<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<c:url value="usuario" var="usuarioRegistrar">
	<c:param name="acao" value="registrarUsuario"/>
</c:url>

<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/sheets/favicon_transparent_32x32.png">
<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css">
<script type="text/javascript" src="resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fintech</title>
</head>
<body>
	<%@ include file="header.jsp" %>
	<div class="container d-flex flex-column">
	<h2 class="mt-5 h2">Registre-se</h2>
		<form class="container- d-flex flex-row gap-3" action="${usuarioRegistrar}" method="post">
			<div class="col mt-5 d-flex flex-column gap-3">
				<div>
				 	<label for="basic-url"  >Nome:</label>
					<input class="form-control border-primary-subtle text-dark bg-light" name="nome" type="text" placeholder="Nome" aria-label="Nome">
				</div>
				<div>
				 	<label for="basic-url"  >E-mail:</label>
				<input class="form-control border-primary-subtle text-dark bg-light me-2" name="email" type="text" placeholder="E-mail" aria-label="E-mail">
				</div>
				<div>
				 	<label for="exampleInputPassword1"  >Senha:</label>
					<input class="form-control border-primary-subtle text-dark bg-light me-2" name="senha" type="password" placeholder="Senha" aria-label="Senha" id="exampleInputPassword1">
				</div>
				<div>
				 	<label for="basic-url"  >Data de Nascimento:</label>
				<input class="form-control border-primary-subtle text-dark bg-light" name="dataNasc" type="date" placeholder="Data de Nascimento" />
				</div>
				<div>
				 	<label for="basic-url"  >CPF:</label>
				<input class="form-control border-primary-subtle text-dark bg-light me-2" name="cpf" type="text" placeholder="CPF" aria-label="CPF">
				</div>
				<div>
				 	<label for="basic-url"  >Sexo:</label>
				<select class="form-select border-primary-subtle text-dark bg-light" name="sexo" aria-label="Selecione Sexo">
					<option value="masculino">Masculino</option>
					<option value="feminino">Feminino</option>
				</select>
				</div>
				<button class="btn btn-primary" type="submit">Registrar-se</button>
			</div>
		</form>
	</div>
</body>
</html>