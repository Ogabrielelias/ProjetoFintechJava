<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/conta" var="contaRegistrar">
	<c:param name="acao" value ="registrarConta"></c:param>
</c:url>
<c:url value="/conta" var="contaEditar">
	<c:param name="acao" value ="editarConta"></c:param>
</c:url>
<c:url value="/conta" var="contaExcluir">
	<c:param name="acao" value ="excluirConta"></c:param>
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
	<div class="container d-flex justify-content-between mt-5">
		<h3>Contas:</h3>
		<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasCriarConta" aria-controls="offcanvasCriarContaLabel">
			Criar Nova Conta
		</button>
	</div>
	<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasCriarConta" aria-labelledby="offcanvasCriarContaLabel">
  		<div class="offcanvas-header">
		    <h5 class="offcanvas-title" id="offcanvasExampleLabel">Nova Conta:</h5>
		   	<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		</div>
		<%@ include file="formConta.jsp" %>
  	</div>
  	<div class="container mt-5">
  		<c:if test="${ empty contas }">
  			<span>Não há nenhuma conta registrada!</span>
  		</c:if>
  		<c:if test="${ not empty contas }">
  			<table class="table table-secondary table-striped">
  			<thead>
	  			<tr>
	  				<th scope="col" class=" d-sm-table-cell d-none">#</th>
	  				<th scope="col">Saldo</th>
	  				<th scope="col" class=" d-sm-table-cell d-none">Inst. Finan.</th>
	  				<th scope="col" class=" d-sm-table-cell d-none">Tipo Conta</th>
	  				<th scope="col" class="overflow-y-hidden">Descrição</th>
	  				<th scope="col"></th>
	  				<th scope="col"></th>
	  			</tr>
  			</thead>
  			<tbody>
  				<c:forEach items="${ contas }" var="c">
  			<tr>
					<th scope="row" class=" d-sm-table-cell d-none">
						${ c.id }
					</th>	
					<c:if test="${ c.saldo >=0 }">
					<td class="fw-medium text-success">
					</c:if>
					<c:if test="${ c.saldo < 0 }">
					<td class="fw-medium text-danger">
					</c:if>
						<fmt:setLocale value = "pt_BR"/>
	         			<fmt:formatNumber value = "${c.saldo}" type = "currency"/>			
					</td>
					<td class=" d-sm-table-cell d-none">
						${ c.instFinanceira }
					</td>
					<td class=" d-sm-table-cell d-none">
						${ c.tipoConta }
					</td>
					<td>
						${ c.descricao }
					</td>
					<td>
						<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasConta${c.id}" aria-controls="offcanvasEditarConta${c.id}Label">Editar</button>
					</td>
					<td>
						<button type="button" class="btn btn-danger" onClick="window.location='${ contaExcluir }&id=${c.id }'">Excluir</button>
					</td>
  			<tr>
  				</c:forEach>
  			</tbody>
			</table>
  		</c:if>
  		<c:forEach items="${ contas }" var="conta">
	<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasConta${conta.id }" aria-labelledby="offcanvasEditarConta${conta.id}Label">
  		<div class="offcanvas-header">
		    <h5 class="offcanvas-title" id="offcanvasExampleLabel">Nova Conta:</h5>
		   	<button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
		</div>
		<form class="offcanvas-body d-flex flex-column gap-3"  action="${contaEditar}" method="post">
		<input class="form-control d-none" value="${conta.usuario}" name="usuario" type="text" placeholder="usuario" aria-label="usuario">
		<input class="form-control d-none" value="${conta.id}" name="id" type="text" placeholder="id" aria-label="id">
			<div>
				 <label for="basic-url">Saldo:</label>
				<input class="form-control border-primary-subtle text-dark bg-light" value="${conta.saldo}" step=".01" name="saldo" type="number" placeholder="Valor" aria-label="Valor">
			</div>
		 	<div>
				 <label for="basic-url">Instituição Financeira:</label>
				<input class="form-control border-primary-subtle text-dark bg-light" value="${conta.instFinanceira}"name="instFinan" type="text" placeholder="InstituiçãoFinanceira" aria-label="InstituiçãoFinanceira">
			</div>
			<div>
				 <label for="basic-url">Tipo:</label>
				<input class="form-control border-primary-subtle text-dark bg-light" value="${conta.tipoConta}" name="tipoConta" type="text" placeholder="TipoConta" aria-label="TipoConta">
			</div>
			<div>
				 <label for="basic-url">Descrição:</label>
			<textarea class="form-control border-primary-subtle text-dark bg-light" name="descricaoConta" placeholder="Descrição" aria-label="Descrição">${conta.descricao}</textarea>
			</div>
			<button type="submit" class="btn btn-primary">Editar Conta</button>
		</form>
  	</div>
  		</c:forEach>
  	</div>
</body>
</html>