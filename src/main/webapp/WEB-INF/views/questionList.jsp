<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean scope="request" id="questions" type="ch.heig.amt.overflow.application.question.QuestionsDTO"/>

<div class="container">
    <h1>Questions</h1>
    <hr>
    <div class="row">
        <c:if test="${sessionScope.currentUser != null}">
            <div class="col-md-4">
                <h2>Nouvelle question</h2>
                <form action="submitQuestion.do" method="post">
                    <div class="form-group">
                        <label for="title">Titre</label>
                        <input type="text" id="title" name="title" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="editor">Contenu</label>
                        <textarea id="editor" name="content" class="form-control"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </form>
            </div>
        </c:if>
        <c:choose>
        <c:when test="${sessionScope.currentUser != null}">
        <div class="col-md-8">
            </c:when>
            <c:otherwise>
            <div class="col-md-12">
                </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${requestScope.search != null && requestScope.search != ''}">
                        <h2 class="mb-3">Recherche pour '${requestScope.search}'</h2>
                    </c:when>
                    <c:otherwise>
                        <h2 class="mb-3">Dernières questions</h2>
                    </c:otherwise>
                </c:choose>
                <form action="${pageContext.request.contextPath}/questions" method="get">
                    <input type="text" name="s" class="form-control mb-3"
                           placeholder="Recherchez une question par titre ou auteur ..."
                           value="${requestScope.search}">
                </form>
                <c:forEach items="${requestScope.questions.questions}" var="question">
                    <div class="card mb-3">
                        <div class="card-header">
                            <h3 class="h5">
                                <a href="${pageContext.request.contextPath}/question/${question.questionId}"> <c:out value="${question.title}"/> </a>
                            </h3>
                            <div class="d-flex justify-content-between">
                                <div class="text-muted">
                                    <c:out value="${question.author.firstName} ${question.author.lastName}"/>
                                </div>
                                <small class="text-muted">
                                    <c:out value="${question.formattedCreatedAt()}"/>
                                </small>
                            </div>
                        </div>
                        <div class="card-body">
                            <c:out value="${question.content}" escapeXml="false"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <%@include file="fragments/footer.jsp" %>
