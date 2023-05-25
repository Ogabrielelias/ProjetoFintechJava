<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:url value="/conta" var="contaListar">
	<c:param name="acao" value="listarConta" />
</c:url>

<c:url value="transacao" var="transacaoListar">
	<c:param name="acao" value="listarTransacao" />
</c:url>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="resources/css/bootstrap.min.css">
<link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/sheets/favicon_transparent_32x32.png">
<script type="text/javascript" src="resources/js/jquery-3.7.0.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Fintech</title>
</head>
<body>
	<%@ include file="header.jsp"%>
	<div
		class="container-lg d-flex flex-column align-items-center gap-4 mt-5">
		<div
			class="container-fluid shadow border rounded d-flex flex-md-row flex-column justify-content-between py-3 px-5">
			<div class="d-flex flex-column align-items-center">
				<p class="h4">Saldo Total</p>
				<c:if test="${ saldoTotal >= 0 }">
					<span class="h3 text-success">
				</c:if>
				<c:if test="${ saldoTotal < 0 }">
					<span class="h3 text-danger">
				</c:if>
				<fmt:setLocale value="pt_BR" />
				<fmt:formatNumber value="${saldoTotal}" type="currency" />
				</span>
			</div>
			<div class="d-flex flex-column align-items-center">
				<p class="h4">Receitas</p>
				<span class="h3  text-success"><fmt:setLocale value="pt_BR" />
					<fmt:formatNumber value="${receitaMes}" type="currency" /> </span>
			</div>
			<div class="d-flex flex-column align-items-center">
				<p class="h4">Despesas</p>
				<span class="h3 text-danger"><fmt:setLocale value="pt_BR" />
					<fmt:formatNumber value="${despesaMes}" type="currency" /></span>
			</div>
		</div>
		<div class="container-fluid p-0 gap-2 d-flex flex-md-row flex-column">
			<div
				class="shadow border col d-flex flex-column justify-content-between rounded py-3 px-5">
				<div
					class="d-flex flex-column align-items-lg-start align-items-center">
					<p class="h5 text-lg-start text-center">Total Receitas:
					<p>
						<span class="h4 text-success"><fmt:setLocale value="pt_BR" />
							<fmt:formatNumber value="${receitaTotal}" type="currency" /></span>
				</div>
				<div
					class="d-flex flex-column align-items-lg-start align-items-center">
					<p class="h5 text-lg-start text-center">Total Despesas:
					<p>
						<span class="h4 text-danger"><fmt:setLocale value="pt_BR" />
							<fmt:formatNumber value="${despesaTotal}" type="currency" /></span>
				</div>
				<div
					class="d-flex flex-column align-items-lg-start align-items-center">
					<p class="h5 text-lg-start text-center">Total Investido:
					<p>
						<span class="h4 text-primary"><fmt:setLocale value="pt_BR" />
							<fmt:formatNumber value="${investTotal}" type="currency" /></span>
				</div>
			</div>
			<div class=" shadow col border rounded py-3 px-5">
				<div class=" d-flex flex-sm-row flex-column justify-content-between">
					<p class="h5">Contas:
					<p>
						<c:if test="${ not empty maioresContas }">
							<a href="${contaListar }">Vizualizar contas</a>
						</c:if>
				</div>
				<c:if test="${ empty maioresContas }">
					<span>Não há contas cadastradas! <a href="${contaListar }">Cadastre
							agora.</a></span>
				</c:if>
				<c:if test="${ not empty maioresContas }">
					<table class="table table-light table-striped">
						<thead>
							<tr>
								<th scope="col">Saldo</th>
								<th scope="col" class=" d-sm-table-cell d-none">Inst.
									Finan.</th>
								<th scope="col" class=" d-sm-table-cell d-none">Tipo Conta</th>
								<th scope="col" class="overflow-y-hidden">Descrição</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${maioresContas }" var="c">
								<tr>
									<c:if test="${ c.saldo >=0 }">
										<td class="fw-medium text-success">
									</c:if>
									<c:if test="${ c.saldo < 0 }">
										<td class="fw-medium text-danger">
									</c:if>
									<fmt:setLocale value="pt_BR" />
									<fmt:formatNumber value="${c.saldo}" type="currency" />
									</td>
									<td class=" d-sm-table-cell d-none">${ c.instFinanceira }
									</td>
									<td class=" d-sm-table-cell d-none">${ c.tipoConta }</td>
									<td>${ c.descricao }</td>
								<tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
		<div
			class="container-fluid shadow border rounded d-flex mb-3 flex-column py-3 px-5">
			<div class=" d-flex flex-sm-row flex-column justify-content-between">
				<p class="h5">Ultimas Transações:</p>
				<c:if test="${ not empty transacoes }">
					<a href="${listarTransacao }">Vizualizar transações</a>
				</c:if>
			</div>
			<c:if test="${ empty transacoes }">
				<span>Não há nenhuma transação registrada!</span>
			</c:if>
			<c:if test="${ not empty transacoes }">
				<table class="table table-light table-striped">
					<thead>
						<tr class="table-light">

							<th scope="col">Data</th>
							<th class=" d-sm-table-cell d-none" scope="col">Conta</th>
							<th scope="col">Valor</th>
							<th class=" d-sm-table-cell d-none" scope="col">Tipo</th>
							<th class=" d-sm-table-cell d-none" scope="col"
								class="overflow-y-hidden">Descrição</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ transacoes }" var="t">
							<c:forEach items="${ receitas }" var="r">
								<c:if test="${ r.idTransacao == t.id}">
									<tr>
										<td><fmt:formatDate value="${ t.dtTransacao }"
												pattern="dd/MM/yy" /></td>
										<td class=" d-sm-table-cell d-none"><c:forEach
												items="${ contas }" var="c">
												<c:if test="${ c.id == t.conta }">
										${ c.descricao }
									</c:if>
											</c:forEach></td>
										<td class="fw-medium text-success">
										
										<fmt:setLocale
												value="pt_BR" /> <fmt:formatNumber value="${r.valor}"
												type="currency" /></td>
										<td class=" d-sm-table-cell d-none"><c:forEach
												items="${ tipoReceita }" var="tr">
												<c:if test="${r.tipoReceita == tr.id }">
									${ tr.tipoReceita }
								</c:if>
											</c:forEach></td>
										<td class=" d-sm-table-cell d-none">${ t.descricao }</td>
									</tr>
								</c:if>
							</c:forEach>
							<c:forEach items="${ despesas }" var="d">
								<c:if test="${ d.idTransacao == t.id}">
									<tr>

										<td><fmt:formatDate value="${ t.dtTransacao }"
												pattern="dd/MM/yy" /></td>
										<td class=" d-sm-table-cell d-none"><c:forEach
												items="${ contas }" var="c">
												<c:if test="${ c.id == t.conta }">
										${ c.descricao }
									</c:if>
											</c:forEach></td>
										<td class="fw-medium text-danger"><fmt:setLocale
												value="pt_BR" /> <fmt:formatNumber value="${-d.valor}"
												type="currency" /></td>
										<td class=" d-sm-table-cell d-none"><c:forEach
												items="${ tipoDespesa }" var="td">
												<c:if test="${d.tipoDespesa == td.id }">
									${ td.tipoDespesa }
								</c:if>
											</c:forEach></td>
										<td class=" d-sm-table-cell d-none">${ t.descricao }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
		</div>
	</div>
</body>
</html>