<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>


<jsp:useBean scope="request" id="question" type="ch.heig.amt.overflow.application.question.QuestionsDTO.QuestionDTO"/>



<div class="container">

    <h1><c:out value="${question.title}"/> </h1>
    <div class="d-flex justify-content-between">
        <div class="text-muted"> <c:out value="${question.author.firstName} ${question.author.lastName}"/> </div>
        <div class="text-muted"> <c:out value="${question.formattedCreatedAt()}"/> </div>
    </div>
    <hr>

    <div class="row">
        <div class="col-sm-1">
            <p><button type="button" class="btn btn-primary"> + </button></p>
            <p> Score </p>
            <p><button type="button" class="btn btn-primary"> - </button></p>
        </div>

        <div class="col-sm-4">
            <p>
                <c:out value="${question.content}" escapeXml="false"/>
            </p>
        </div>
    </div>

    <hr>
</div>

<%@include file="fragments/footer.jsp" %>
