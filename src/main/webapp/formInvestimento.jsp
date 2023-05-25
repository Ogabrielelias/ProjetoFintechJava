<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="investimento" var="investRegistrar">
	<c:param name="acao" value="registrarInvest"/>
</c:url>
<c:url value="conta" var="contaListar">
	<c:param name="acao" value="listarConta" />
</c:url>
<form class="d-flex flex-column gap-3" role="transactions" action="${investRegistrar}" method="post">
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
		<label>Nome do Investimento:</label>
		<input class="form-control border-primary-subtle text-dark bg-light"
		name="nomeInvest" type="text" placeholder="Nome do investimento" aria-label="Nome do investimento">
	</div>
	<div>
		<label>Tipo de Investimento:</label>
		<input class="form-control border-primary-subtle text-dark bg-light"
		name="tipoInvest" type="text" placeholder="Tipo de Investimento" aria-label="Tipo de Investimento">
	</div>
	<div>
		<label>Valor:</label>
		<input class="form-control border-primary-subtle text-dark bg-light"
			name="valor" step=".01" type="number" placeholder="Valor" aria-label="Valor">
	</div>
	<div>
		<label>Descrição:</label>
		<textarea class="form-control border-primary-subtle text-dark bg-light" name="descricao" placeholder="Descrição"
		aria-label="Descrição"></textarea>
	</div>
	<div>
		<label>Data de Aquisição:</label> <input
			class="form-control border-primary-subtle text-dark bg-light"
			name="dataAquisicao" type="date" />
	</div>
	<div>
		<label>Código do Ativo: (Opcional)</label>
		<input class="form-control border-primary-subtle text-dark bg-light"
		name="codAtivo" type="text" placeholder="Código do Ativo (Opcional)" aria-label="Código do Ativo (Opcional)">
	</div>
	<button class="btn btn-primary" type="submit">Adicionar</button>
</form>