<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="investimento" var="investEditar">
	<c:param name="acao" value="editarInvest"/>
</c:url>
<c:url value="investimento" var="investExcluir">
	<c:param name="acao" value="excluirInvest"/>
</c:url>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/sheets/favicon_transparent_32x32.png">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css">
<script type="text/javascript" src="resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<title>Fintech</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container d-flex justify-content-between mt-5">
		<h3>Investimentos:</h3>
	</div>
	<div class="container mt-5">
		<c:if test="${ empty investimentos }">
			<span>Não há nenhum investimento registrado!</span>
		</c:if>
		<c:if test="${ not empty investimentos }">
			<table class="table table-secondary table-striped">
				<thead>
					<tr>
						<th class=" d-sm-table-cell d-none" scope="col">#</th>
						<th class=" d-sm-table-cell d-none" scope="col">Conta</th>
						<th  class=" d-sm-table-cell d-none" scope="col">Nome Invest.</th>
						<th class=" d-sm-table-cell d-none" scope="col">Tipo Invest.</th>
						<th scope="col">Valor</th>
						<th class=" d-sm-table-cell d-none" scope="col" class="overflow-y-hidden">Descrição</th>
						<th class=" d-sm-table-cell d-none" scope="col">Data de Aquisição</th>
						<th class=" d-sm-table-cell d-none" scope="col">Cód. Ativo</th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ investimentos }" var="i">
						<c:forEach items="${ contas }" var="c">
							<c:if test="${ c.id == i.conta}">
								<tr>
									<th class=" d-sm-table-cell d-none"scope="row">${ i.id }</th>
									<td class=" d-sm-table-cell d-none">${ c.descricao }</td>
									<td  class=" d-sm-table-cell d-none">${ i.nomeInvestimento }</td>
									<td class=" d-sm-table-cell d-none">${ i.tipoInvestimento }</td>
									<td class="fw-medium text-primary"><fmt:setLocale
											value="pt_BR" /> <fmt:formatNumber
											value="${i.valorInvestido}" type="currency" /></td>
									<td  class=" d-sm-table-cell d-none">${ i.descricao }</td>
									<td  class=" d-sm-table-cell d-none"><fmt:formatDate value="${i.dtAquisicao}"
											pattern="dd/MM/yyyy" /></td>
									<td  class=" d-sm-table-cell d-none">${ i.codAtivo }</td>
									<td>
										<button class="btn btn-primary" type="button"
											data-bs-toggle="offcanvas"
											data-bs-target="#offcanvasEditarInvestimento${i.id}"
											aria-controls="offcanvasEditarInvestimento${i.id}Label">Editar</button>
									</td>
									<td>
										<button type="button" class="btn btn-danger"
											onClick="window.location='${investExcluir }&id=${ i.id }'">Excluir</button>
									</td>
							</c:if>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<c:forEach items="${ investimentos }" var="i">
		<div class="offcanvas offcanvas-start"
			id="offcanvasEditarInvestimento${ i.id }" tabindex="-1"
			aria-labelledby="offcanvasFormEditarInvestimento${ i.id }">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Adicionar
					Receita</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
					aria-label="Close"></button>
			</div>
			<div class="offcanvas-body">
				<form class="d-flex flex-column gap-3" role="investments"
					action="${investEditar}" method="post">
					<input class="d-none" type="number" value="${ i.id }" name="id" />
					<div>
						<label>Contas:</label>
						<c:if test="${ empty contas }">
							<span>Não há contas cadastradas! <a href="./listarContas">Cadastre
									agora.</a></span>
						</c:if>
						<c:if test="${ not empty contas }">
							<select
								class="form-select border-primary-subtle text-dark bg-light"
								name="conta" aria-label="Conta">
								<c:forEach items="${ contas }" var="c">
									<c:if test="${ i.conta==c.id }">
										<option selected value="${c.id}">${ c.descricao }</option>
									</c:if>
									<c:if test="${ i.conta!=c.id }">
										<option value="${c.id}">${ c.descricao }</option>
									</c:if>
								</c:forEach>
							</select>
						</c:if>
					</div>
					<div>
						<label>Nome do Investimento:</label> <input
							class="form-control border-primary-subtle text-dark bg-light"
							name="nomeInvest" type="text" value="${i.nomeInvestimento }"
							placeholder="Nome do investimento"
							aria-label="Nome do investimento">
					</div>
					<div>
						<label>Tipo de Investimento:</label> <input
							class="form-control border-primary-subtle text-dark bg-light"
							name="tipoInvest" type="text" value="${ i.tipoInvestimento}"
							placeholder="Tipo de Investimento"
							aria-label="Tipo de Investimento">
					</div>
					<div>
						<label>Valor:</label> <input
							class="form-control border-primary-subtle text-dark bg-light"
							name="valor" step=".01" value="${i.valorInvestido }"
							type="number" placeholder="Valor" aria-label="Valor">
					</div>
					<div>
						<label>Descrição:</label>
						<textarea
							class="form-control border-primary-subtle text-dark bg-light"
							name="descricao" placeholder="Descrição" aria-label="Descrição"> ${ i.descricao }</textarea>
					</div>
					<div>
						<label>Data de Aquisição:</label> <input
							class="form-control border-primary-subtle text-dark bg-light"
							name="dataAquisicao" type="date" value="${i.dtAquisicao }" />
					</div>
					<div>
						<label>Código do Ativo: (Opcional)</label> <input
							class="form-control border-primary-subtle text-dark bg-light"
							name="codAtivo" type="text" value="${i.codAtivo}"
							placeholder="Código do Ativo (Opcional)"
							aria-label="Código do Ativo (Opcional)">
					</div>
					<button class="btn btn-primary" type="submit">Adicionar</button>
				</form>
			</div>
		</div>
	</c:forEach>
</body>
</html>