<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="conta" var="contaConectar">
	<c:param name="acao" value="listarConta" />
</c:url>
<c:url value="investimento" var="investListar">
	<c:param name="acao" value="listarInvest" />
</c:url>
<c:url value="dashboard" var="dashboard"/>
<c:url value="transacao" var="transacaoListar">
	<c:param name="acao" value="listarTransacao" />
</c:url>
<c:url value="usuario" var="usuarioForm">
	<c:param name="acao" value="formRegistrarUsuario" />
</c:url>
<c:url value="usuario" var="usuarioLogin">
	<c:param name="acao" value="logarUsuario" />
</c:url>
<c:url value="usuario" var="usuarioSair">
	<c:param name="acao" value="deslogarUsuario" />
</c:url>
<script>
  window.addEventListener("beforeunload", function() {
    document.getElementById("loading-spinner").style.display = "flex";
  });
  
  window.addEventListener("load", function() {
    document.getElementById("loading-spinner").style.display = "none";
    
  });
</script>
<div style="display:none; z-index:2000!important;" id="loading-spinner" class="position-fixed justify-content-center align-items-center sticky-top w-100 h-100 bg-dark opacity-75">
	<div class="spinner-border text-primary" style="width:200px;height:200px;" role="status">
		<span class="visually-hidden">Loading...</span>
	</div>
</div>
<header  class="sticky-top shadow">
	<nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
		<div class="container-fluid">
			<c:if test="${ not empty erro || not empty param.erro}">
			
				<div
					class="position-absolute top-100 start-50 translate-middle alert alert-danger"
					role="alert" style="background-color:rgb(44 11 14 / 90%);">
					${erro} ${param.erro}
					<button type="button" class="ms-3 btn-close"
						data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</c:if>

			<a href="./" class="navbar-brand"> <span class="h2">Fintech</span>
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="justify-content-between collapse navbar-collapse"
				id="navbarNav">
				<ul class=" navbar-nav">
					<c:if test="${ not empty user }">
						<li class="nav-item"><c:if
								test="${ currPage == 'listarDashboard' }">
								<a class="nav-link active" aria-current="page"
									href="${dashboard }">Dashboard</a>
							</c:if> <c:if test="${ currPage != 'listarDashboard' }">
								<a class="nav-link" aria-current="page" href="${dashboard }">Dashboard</a>
							</c:if></li>
						<li class="nav-item"><c:if
								test="${ currPage == 'listarContas' }">
								<a class="nav-link active" aria-current="page"
									href="${ contaConectar }">Contas</a>
							</c:if> <c:if test="${ currPage != 'listarContas' }">
								<a class="nav-link" aria-current="page"
									href="${ contaConectar }">Contas</a>
							</c:if></li>
						<li class="nav-item"><c:if
								test="${ currPage == 'listarInvestimentos' }">
								<a class="nav-link active" aria-current="page"
									href="${investListar }">Investimentos</a>
							</c:if> <c:if test="${ currPage != 'listarInvestimentos' }">
								<a class="nav-link" aria-current="page" href="${investListar }">Investimentos</a>
							</c:if></li>
						<li class="nav-item"><c:if
								test="${ currPage == 'listarTransacoes' }">
								<a class="nav-link active" aria-current="page"
									href="${transacaoListar }">Transações</a>
							</c:if> <c:if test="${ currPage != 'listarTransacoes' }">
								<a class="nav-link" aria-current="page"
									href="${transacaoListar }">Transações</a>
							</c:if></li>
						<li>
							<button class="btn btn-light ms-lg-4" type="button"
								data-bs-toggle="offcanvas"
								data-bs-target="#offcanvasSelectTransaction"
								aria-controls="offcanvasExample">Adicionar Transação</button>
						</li>
					</c:if>
				</ul>
				<div
					class="d-flex flex-column align-items-sm-start align-items-center gap-3">
					<c:if test="${ not empty user }">
						<div class="d-flex flex-row gap-3 align-items-center">
							<span class="text-light ">${ user }</span>
							<button onClick="window.location='${usuarioSair}'" class="btn btn-light"
								type="button">Sair</button>
						</div>
					</c:if>
					<c:if test="${ empty user }">
						<div class="d-flex flex-sm-row flex-column gap-3">
							<button onClick="window.location='${usuarioForm}'"
								class="btn btn-light" type="button">Registre-se</button>
							<form class="d-flex flex-sm-row flex-column gap-sm-0 gap-3"
								role="login" action="${usuarioLogin}" method="post">
								<input
									class="form-control border-primary-subtle text-dark bg-light me-2"
									name="emailLogin" type="text" placeholder="E-mail"
									aria-label="E-mail"> <input
									class="form-control border-primary-subtle text-dark bg-light me-2"
									name="senhaLogin" type="password" placeholder="Senha"
									aria-labelledby="passwordHelpBlock">
								<button class="btn btn-dark" type="submit">Entrar</button>
							</form>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</nav>
	<c:if test="${ not empty user }">
		<div class="offcanvas offcanvas-start" id="offcanvasSelectTransaction"
			tabindex="-1" aria-labelledby="offcanvasExampleLabel">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Adicionar
					Transação</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
					aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<div class="d-flex flex-column gap-3" role="transactions">
					<span>Selecione um tipo de tranção:</span>
					<button class="btn btn-primary" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasReceita"
						aria-controls="offcanvasReceita">Receita</button>
					<button class="btn btn-primary" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasDespesa"
						aria-controls="offcanvasDespesa">Despesa</button>
					<button class="btn btn-primary" type="button"
						data-bs-toggle="offcanvas" data-bs-target="#offcanvasInvestimento"
						aria-controls="offcanvasInvestimento">Investimento</button>
				</div>
			</div>
		</div>
		<div class="offcanvas offcanvas-start" id="offcanvasDespesa"
			tabindex="-1" aria-labelledby="offcanvasFormDespesa">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Adicionar
					Despesa</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
					aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<%@ include file="formDespesa.jsp"%>
			</div>
		</div>
		<div class="offcanvas offcanvas-start" id="offcanvasInvestimento"
			tabindex="-1" aria-labelledby="offcanvasFormInvestimento">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Adicionar
					Investimento</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
					aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<%@ include file="formInvestimento.jsp"%>
			</div>
		</div>
		<div class="offcanvas offcanvas-start" id="offcanvasReceita"
			tabindex="-1" aria-labelledby="offcanvasFormReceita">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Adicionar
					Receita</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
					aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<%@ include file="formReceita.jsp"%>
			</div>
		</div>
	</c:if>
</header>
