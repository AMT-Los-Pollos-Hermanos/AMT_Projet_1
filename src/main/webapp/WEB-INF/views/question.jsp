<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>


<jsp:useBean scope="request" id="question" type="ch.heig.amt.overflow.application.question.QuestionsDTO.QuestionDTO"/>

<div class="container">
    <div class="container">
        <h1><c:out value="${question.title}"/> </h1>
        <div class="d-flex justify-content-between">
            <div class="text-muted"> <c:out value="${question.author.firstName} ${question.author.lastName}"/> </div>
            <div class="text-muted"> <c:out value="${question.formattedCreatedAt()}"/> </div>
        </div>
        <hr>

        <div class="row">
            <div class="col-sm-1">
                <p><a type="button" href="${pageContext.request.contextPath}/submitVote.do?state=up&content_id=${question.questionId}" class="btn btn-primary"> + </a></p>
                <p> <c:out value="${question.nbVotes}"/> </p>
                <p><a type="button" href="${pageContext.request.contextPath}/submitVote.do?state=down&content_id=${question.questionId}" class="btn btn-primary"> - </a></p>
            </div>

            <div class="col-sm-4">
                <p>
                    <c:out value="${question.content}" escapeXml="false"/>
                </p>
            </div>
        </div>
        <hr>

        <!-- Comments -->
        <c:forEach items="${question.commentsDTO.comments}" var="comment">

            <c:out value="${comment.content} - ${comment.author.firstName} ${comment.author.lastName}" escapeXml="false"/>
            <span class="text-muted"> <c:out value="${comment.formattedCreatedAt()}"/> </span>
            <hr>
        </c:forEach>
        <a class="text-muted" href="${pageContext.request.contextPath}/comment/${question.questionId}"> Add new comment </a>
    </div>


    <!-- Answers -->
    <c:forEach items="${question.answersDTO.answers}" var="answer">
        <br> <br>
    <div class="container">
        <div class="d-flex justify-content-between">
            <div class="text-muted"> <c:out value="${answer.author.firstName} ${answer.author.lastName}"/> </div>
            <div class="text-muted"> <c:out value="${answer.formattedCreatedAt()}"/> </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-1">
                <p><a type="button" href="${pageContext.request.contextPath}/submitVote.do?state=up&content_id=${answer.answerId}" class="btn btn-primary"> + </a></p>
                <p> <c:out value="${answer.nbVotes}"/> </p>
                <p><a type="button" href="${pageContext.request.contextPath}/submitVote.do?state=down&content_id=${answer.answerId}" class="btn btn-primary"> - </a></p>
            </div>

            <div class="col-sm-4">
                <p>
                    <c:out value="${answer.content}" escapeXml="false"/>
                </p>
            </div>
        </div>
        <hr>
        <c:forEach items="${answer.commentsDTO.comments}" var="comment">
            <c:out value="${comment.content} - ${comment.author.firstName} ${comment.author.lastName}" escapeXml="false"/>
            <span class="text-muted"> <c:out value="${comment.formattedCreatedAt()}"/> </span>
            <hr>
        </c:forEach>
        <a class="text-muted" href="${pageContext.request.contextPath}/comment/${answer.answerId}"> Add new comment </a>
    </div>
    </c:forEach>

    <hr>
    <div class="container mb-3">
        <form action="${pageContext.request.contextPath}/submitAnswer.do" method="post">
            <div class="form-group">
                <label for="editor">Your answer</label>
                <textarea id="editor" name="content" class="form-control"></textarea>
            </div>
            <input type="hidden" name="question_id" value="${question.questionId}">
            <button type="submit" class="btn btn-primary">Ajouter</button>
        </form>
    </div>

</div>



<%@include file="fragments/footer.jsp" %>
