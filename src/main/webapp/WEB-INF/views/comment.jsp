<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="fragments/header.jsp" %>

<div class="container">
    <h1>Nouveau commentaire</h1>
    <c:if test="${requestScope.question != null}">
        <h2 class="h3 text-muted">Sur la question '${requestScope.question.title}'</h2>
    </c:if>
    <c:if test="${requestScope.answer != null}">
        <h2 class="h3 text-muted">Sur la réponse
            de ${requestScope.answer.author.firstName} ${requestScope.answer.author.lastName}</h2>
    </c:if>
    <hr>
    <form action="${pageContext.request.contextPath}/comment.do" method="post">
        <div class="form-group">
            <label for="editor">Commentaire</label>
            <textarea name="comment" id="editor" cols="30" rows="10"></textarea>
        </div>
        <input type="hidden" name="content_id" value="${requestScope.contentId}">
        <button type="submit" class="btn btn-primary">Envoyer</button>
        <a href="${header.get("referer")}" class="btn btn-secondary">Annuler</a>
    </form>
</div>

<%@include file="fragments/footer.jsp" %>
