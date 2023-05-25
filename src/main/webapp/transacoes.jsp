<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="receita" var="receitaEditar">
	<c:param name="acao" value="editarReceita" />
</c:url>
<c:url value="despesa" var="despesaEditar">
	<c:param name="acao" value="editarDespesa" />
</c:url>
<c:url value="transacao" var="transacaoExcluir">
	<c:param name="acao" value="excluirTransacao" />
</c:url>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css">
	<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/sheets/favicon_transparent_32x32.png">
<script type="text/javascript" src="resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fintech</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="container d-flex justify-content-between mt-5">
		<h3>Transacões:</h3>
	</div>
	<div class="container mt-5">
		<c:if test="${ empty transacoes }">
			<span>Não há nenhuma transação registrada!</span>
		</c:if>
		<c:if test="${ not empty transacoes }">
			<table class="table table-secondary table-striped">
				<thead>
					<tr>
						<th class=" d-sm-table-cell d-none" scope="col">#</th>
						<th class=" d-sm-table-cell d-none" scope="col">Data</th>
						<th class=" d-sm-table-cell d-none" scope="col">Conta</th>
						<th scope="col">Valor</th>
						<th class=" d-sm-table-cell d-none" scope="col">Tipo</th>
						<th class=" d-sm-table-cell d-none" scope="col"
							class="overflow-y-hidden">Descrição</th>
						<th scope="col"></th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ transacoes }" var="t">
						<c:forEach items="${ receitas }" var="r">
							<c:if test="${ r.idTransacao == t.id}">
								<tr>
									<th class=" d-sm-table-cell d-none" scope="row">${ t.id }</th>
									<td class=" d-sm-table-cell d-none"><fmt:formatDate
											value="${ t.dtTransacao }" pattern="dd/MM/yy" /></td>
									<td class=" d-sm-table-cell d-none"><c:forEach
											items="${ contas }" var="c">
											<c:if test="${ c.id == t.conta }">
										${ c.descricao }
									</c:if>
										</c:forEach></td>
									<td class="fw-medium text-success"><fmt:setLocale
											value="pt_BR" /> <fmt:formatNumber value="${r.valor}"
											type="currency" /></td>
									<td class=" d-sm-table-cell d-none"><c:forEach
											items="${ tipoReceitas }" var="tr">
											<c:if test="${r.tipoReceita == tr.id }">
									${ tr.tipoReceita }
								</c:if>
										</c:forEach></td>
									<td class=" d-sm-table-cell d-none">${ t.descricao }</td>
									<td>
										<button class="btn btn-primary" type="button"
											data-bs-toggle="offcanvas"
											data-bs-target="#offcanvasTransacao${t.id}"
											aria-controls="offcanvasEditarTransacao${t.id}Label">Editar</button>
									</td>
									<td>
										<button type="button" class="btn btn-danger"
											onClick="window.location='${transacaoExcluir }&id=${ t.id }'">Excluir</button>
									</td>
								</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${ despesas }" var="d">
							<c:if test="${ d.idTransacao == t.id}">
								<tr>
									<th class=" d-sm-table-cell d-none" scope="row">${ t.id }</th>
									<td class=" d-sm-table-cell d-none"><fmt:formatDate
											value="${ t.dtTransacao }" pattern="dd/MM/yy" /></td>
									<td class=" d-sm-table-cell d-none"><c:forEach
											items="${ contas }" var="c">
											<c:if test="${ c.id == t.conta }">
										${ c.descricao }
									</c:if>
										</c:forEach></td>
									<td class="fw-medium text-danger"><fmt:setLocale
											value="pt_BR" /> <fmt:formatNumber value="${d.valor}"
											type="currency" /></td>
									<td class=" d-sm-table-cell d-none"><c:forEach
											items="${ tipoDespesas }" var="td">
											<c:if test="${d.tipoDespesa == td.id }">
									${ td.tipoDespesa }
								</c:if>
										</c:forEach></td>
									<td class=" d-sm-table-cell d-none">${ t.descricao }</td>
									<td>
										<button class="btn btn-primary" type="button"
											data-bs-toggle="offcanvas"
											data-bs-target="#offcanvasTransacao${t.id}"
											aria-controls="offcanvasEditarTransacao${t.id}Label">Editar</button>
									</td>
									<td>
										<button type="button" class="btn btn-danger"
											onClick="window.location='${transacaoExcluir }&id=${ t.id }'">Excluir</button>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<c:forEach items="${ transacoes }" var="t">
		<div class="offcanvas offcanvas-start" tabindex="-1"
			id="offcanvasTransacao${t.id }"
			aria-labelledby="offcanvasEditarTransacao${t.id}Label">
			<div class="offcanvas-header">
				<h5 class="offcanvas-title" id="offcanvasExampleLabel">Nova
					Conta:</h5>
				<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
					aria-label="Close"></button>
			</div>
			<c:forEach items="${ receitas }" var="r">
				<c:if test="${ r.idTransacao == t.id}">
					<form class="offcanvas-body d-flex flex-column gap-3"
						action="${receitaEditar}" method="post">
						<input
							class="form-control border-primary-subtle d-none text-dark bg-light"
							name="id" value="${ t.id }" type="text" /> <input
							class="form-control border-primary-subtle d-none text-dark bg-light"
							name="valorAntigo" value="${ r.valor }" type="text" /> <input
							class="form-control border-primary-subtle d-none text-dark bg-light"
							name="contaAntiga" value="${ t.conta }" type="number" />
						<div>
							<label>Tipo da Receita:</label> <select
								class="form-select border-primary-subtle text-dark bg-light"
								name="tipoReceita" aria-label="Tipo de receita">
								<c:forEach items="${ tipoReceitas }" var="tr">
									<option value="${tr.id}">${ tr.tipoReceita }</option>
								</c:forEach>
							</select>
						</div>
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
										<c:if test="${ c.id == t.conta }">
											<option value="${c.id}" selected>${ c.descricao }</option>
										</c:if>
										<c:if test="${ c.id != t.conta }">
											<option value="${c.id}">${ c.descricao }</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
						</div>
						<div>
							<label>Valor:</label> <input
								class="form-control border-primary-subtle text-dark bg-light"
								name="valor" step=".01" value="${ r.valor }" type="number"
								placeholder="Valor" aria-label="Valor">
						</div>
						<div>
							<label>Data:</label> <input
								class="form-control border-primary-subtle text-dark bg-light"
								name="data" value="${ t.dtTransacao }" type="date" />
						</div>
						<div>
							<label>Descrição:</label>
							<textarea
								class="form-control border-primary-subtle text-dark bg-light"
								name="descricao" placeholder="Descrição" aria-label="Descrição">${t.descricao }</textarea>
						</div>
						<button class="btn btn-success" type="submit">Alterar</button>
					</form>
				</c:if>
			</c:forEach>
			<c:forEach items="${ despesas }" var="d">
				<c:if test="${ d.idTransacao == t.id}">
					<form class="offcanvas-body d-flex flex-column gap-3"
						action="${despesaEditar}" method="post">
						<input
							class="form-control border-primary-subtle d-none text-dark bg-light"
							name="id" value="${ t.id }" type="text" /> <input
							class="form-control border-primary-subtle d-none text-dark bg-light"
							name="valorAntigo" value="${ d.valor }" type="text" /> <input
							class="form-control border-primary-subtle d-none text-dark bg-light"
							name="contaAntiga" value="${ t.conta }" type="number" />
						<div>
							<label>Tipo da Despesa:</label> <select
								class="form-select border-primary-subtle text-dark bg-light"
								name="tipoDespesa" aria-label="Tipo de despesa">
								<c:forEach items="${ tipoDespesas }" var="td">
									<option value="${td.id}">${ td.tipoDespesa }</option>
								</c:forEach>
							</select>
						</div>
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
										<c:if test="${ c.id == t.conta }">
											<option value="${c.id}" selected>${ c.descricao }</option>
										</c:if>
										<c:if test="${ c.id != t.conta }">
											<option value="${c.id}">${ c.descricao }</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
						</div>
						<div>
							<label>Valor:</label> <input
								class="form-control border-primary-subtle text-dark bg-light"
								name="valor" step=".01" value="${ d.valor }" type="number"
								placeholder="Valor" aria-label="Valor">
						</div>
						<div>
							<label>Data:</label> <input
								class="form-control border-primary-subtle text-dark bg-light"
								name="data" value="${ t.dtTransacao }" type="date" />
						</div>
						<div>
							<label>Descrição:</label>
							<textarea
								class="form-control border-primary-subtle text-dark bg-light"
								name="descricao" placeholder="Descrição" aria-label="Descrição">${t.descricao }</textarea>
						</div>
						<button class="btn btn-success" type="submit">Alterar</button>
					</form>
				</c:if>
			</c:forEach>
		</div>
	</c:forEach>
</body>
</html>