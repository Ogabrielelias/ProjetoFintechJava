<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="despesa" var="despesaRegistrar">
	<c:param name="acao" value="registrarDespesa"/>
</c:url>
<c:url value="conta" var="contaListar">
	<c:param name="acao" value="listarConta" />
</c:url>
<form class="d-flex flex-column gap-3" role="income" action="${despesaRegistrar}" method="post">
	<div>
		<label>Tipo da Despesa:</label> <select class="form-select border-primary-subtle text-dark bg-light"
			name="tipoDespesa" aria-label="Tipo de despesa">
			<c:forEach items="${ tipoDespesas }" var="td">
				<option value="${td.id}">${ td.tipoDespesa }</option>
			</c:forEach>
		</select>
	</div>
	<div>
		<label>Contas:</label>
		<c:if test="${ empty contas }">
			<span>N�o h� contas cadastradas! <a href="./${contaListar }">Cadastre
					agora.</a></span>
		</c:if>
		<c:if test="${ not empty contas }">
			<select class="form-select border-primary-subtle text-dark bg-light" name="conta" aria-label="Conta">
				<c:forEach items="${ contas }" var="c">
					<option value="${c.id}">${ c.descricao }</option>
				</c:forEach>
			</select>
		</c:if>
	</div>
	<div>
		<label>Valor:</label> 
		<input
			class="form-control border-primary-subtle text-dark bg-light"
			name="valor" step=".01" type="number" placeholder="Valor"
			aria-label="Valor">
	</div>
	<div>
		<label>Data:</label> <input
			class="form-control border-primary-subtle text-dark bg-light"
			name="data" type="date" />
	</div>
	<div>
		<label>Descri��o:</label>
		<textarea class="form-control border-primary-subtle text-dark bg-light" name="descricao"
			placeholder="Descri��o" aria-label="Descri��o"></textarea>
	</div>
	<button class="btn btn-primary" type="submit">Adicionar</button>
</form>
