<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/conta" var="contaRegistrar">
	<c:param name="acao" value ="registrarConta"></c:param>
</c:url>
<form class="offcanvas-body d-flex flex-column gap-3"  action="${contaRegistrar}" method="post">
			<div>
				 <label for="basic-url">Saldo:</label>
				<input class="form-control border-primary-subtle text-dark bg-light" value="0" step=".01" name="saldo" type="number" placeholder="Valor" aria-label="Valor">
			</div>
		 	<div>
				 <label for="basic-url">Instituição Financeira:</label>
				<input class="form-control border-primary-subtle text-dark bg-light" name="instFinan" type="text" placeholder="Instituição Financeira" aria-label="Instituição Financeira">
			</div>
			<div>
				 <label for="basic-url">Tipo:</label>
				<input class="form-control border-primary-subtle text-dark bg-light" name="tipoConta" type="text" placeholder="Tipo Conta" aria-label="Tipo Conta">
			</div>
			<div>
				 <label for="basic-url">Descrição:</label>
			<textarea class="form-control border-primary-subtle text-dark bg-light" name="descricaoConta" placeholder="Descrição" aria-label="Descrição"></textarea>
			</div>
			<button type="submit" class="btn btn-primary">Adicionar Conta</button>
		</form>