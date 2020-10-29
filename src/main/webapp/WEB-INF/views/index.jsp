<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="fragments/header.jsp" %>

<div class="container">
    <h1 class="text-center">OverFlow</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/questions" method="get">
        <input type="text" name="s" class="form-control form-control-lg" placeholder="Recherchez une question par titre ou auteur ...">
        <div class="d-flex justify-content-center mt-3">
            <button type="submit" class="btn btn-lg btn-primary">Rechercher</button>
        </div>
    </form>
</div>

<%@ include file="fragments/footer.jsp" %>
