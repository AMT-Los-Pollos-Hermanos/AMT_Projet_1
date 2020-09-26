<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean scope="request" id="questions" type="ch.heig.amt.overflow.application.question.QuestionsDTO" />

<div class="container">
    <h1>Questions</h1>
    <hr>
    <div class="row">
        <div class="col-md-4">
            <h2>Nouvelle question</h2>
            <form action="submitQuestion.do" method="post">
                <div class="form-group">
                    <label for="title">Titre</label>
                    <input type="text" id="title" name="title" class="form-control">
                </div>
                <div class="form-group">
                    <label for="content">Contenu</label>
                    <textarea id="content" name="content" class="form-control"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Ajouter</button>
            </form>
        </div>
        <div class="col-md-8">
            <h2>Liste des questions</h2>
            <c:forEach items="${requestScope.questions.questions}" var="question">
                <h3 class="h4"><c:out value="${question.title}" /></h3>
                <p><c:out value="${question.content}" /></p>
            </c:forEach>
        </div>
    </div>
</div>

<%@include file="fragments/footer.jsp" %>
