<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="receita" var="receitaRegistrar">
		<c:param name="acao" value="registrarReceita"/>
</c:url>
<c:url value="conta" var="contaListar">
	<c:param name="acao" value="listarConta" />
</c:url>
<form class="d-flex flex-column gap-3" role="income" action="${receitaRegistrar}" method="post">
	<div>
		<label>Tipo da Receita:</label> <select class="form-select border-primary-subtle text-dark bg-light"
			name="tipoReceita" aria-label="Tipo de receita">
			<c:forEach items="${ tipoReceitas }" var="tr">
				<option value="${tr.id}">${ tr.tipoReceita }</option>
			</c:forEach>
		</select>
	</div>
	<div>
		<label>Contas:</label>
		<c:if test="${ empty contas }">
			<span>Não há contas cadastradas! <a href="./${contaListar }">Cadastre
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
		<label>Descrição:</label>
		<textarea class="form-control border-primary-subtle text-dark bg-light" name="descricao"
			placeholder="Descrição" aria-label="Descrição"></textarea>
	</div>
	<button class="btn btn-primary" type="submit">Adicionar</button>
</form>
