<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>


<jsp:useBean scope="request" id="question" type="ch.heig.amt.overflow.application.question.QuestionsDTO.QuestionDTO"/>

<div class="container">
    <div class="container">
        <h1><c:out value="${question.title}"/></h1>
        <div class="d-flex justify-content-between">
            <div class="text-muted"><c:out value="${question.author.firstName} ${question.author.lastName}"/></div>
            <div class="text-muted"><c:out value="${question.formattedCreatedAt()}"/></div>
        </div>
        <hr>

        <div class="row">
            <div class="col-sm-1">
                <div class="d-flex flex-column align-items-center">
                    <a type="button"
                       href="${pageContext.request.contextPath}/submitVote.do?state=up&content_id=${question.questionId}&question_id=${question.questionId}"
                       class="btn btn-primary btn-sm q-vote-plus"> + </a>
                    <span class="q-vote"><c:out value="${question.nbVotes}"/></span>
                    <a type="button"
                       href="${pageContext.request.contextPath}/submitVote.do?state=down&content_id=${question.questionId}&question_id=${question.questionId}"
                       class="btn btn-primary btn-sm q-vote-minus"> - </a>
                </div>
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

            <div class="d-flex justify-content-between">
                <div>
                    <span class="c-vote"><c:out
                            value="${comment.nbVotes}  ${comment.content} - ${comment.author.firstName} ${comment.author.lastName}"/></span>
                    <span class="text-muted"> <c:out value="${comment.formattedCreatedAt()}"/> </span>
                </div>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <a type="button"
                       href="${pageContext.request.contextPath}/submitVote.do?state=up&content_id=${comment.commentId}&question_id=${question.questionId}"
                       class="btn btn-secondary btn-sm c-vote-plus">+</a>
                    <a type="button"
                       href="${pageContext.request.contextPath}/submitVote.do?state=down&content_id=${comment.commentId}&question_id=${question.questionId}"
                       class="btn btn-secondary btn-sm c-vote-minus">-</a>
                </div>

            </div>
            <hr>
        </c:forEach>
        <a class="text-muted" href="${pageContext.request.contextPath}/comment/${question.questionId}">Ajouter un
            nouveau commentaire</a>
    </div>


    <!-- Answers -->
    <c:forEach items="${question.answersDTO.answers}" var="answer">
        <br> <br>
        <div class="container">
            <div class="d-flex justify-content-between">
                <div class="text-muted"><c:out value="${answer.author.firstName} ${answer.author.lastName}"/></div>
                <div class="text-muted"><c:out value="${answer.formattedCreatedAt()}"/></div>
            </div>
            <br>
            <div class="row">
                <div class="col-sm-1">
                    <div class="d-flex flex-column align-items-center">
                        <a type="button"
                           href="${pageContext.request.contextPath}/submitVote.do?state=up&content_id=${answer.answerId}&question_id=${question.questionId}"
                           class="btn btn-primary btn-sm a-vote-plus"> + </a>
                        <span class="a-vote"><c:out value="${answer.nbVotes}"/></span>
                        <a type="button"
                           href="${pageContext.request.contextPath}/submitVote.do?state=down&content_id=${answer.answerId}&question_id=${question.questionId}"
                           class="btn btn-primary btn-sm a-vote-minus"> - </a>
                    </div>
                </div>

                <div class="col-sm-4">
                    <p>
                        <c:out value="${answer.content}" escapeXml="false"/>
                    </p>
                </div>
            </div>
            <hr>
            <c:forEach items="${answer.commentsDTO.comments}" var="comment">
                <div class="d-flex justify-content-between">
                    <div>
                        <span class="vote"><c:out
                                value="${comment.nbVotes}  ${comment.content} - ${comment.author.firstName} ${comment.author.lastName}"/></span>
                        <span class="text-muted"> <c:out value="${comment.formattedCreatedAt()}"/> </span>
                    </div>
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <a type="button"
                           href="${pageContext.request.contextPath}/submitVote.do?state=up&content_id=${comment.commentId}&question_id=${question.questionId}"
                           class="btn btn-secondary btn-sm">+</a>
                        <a type="button"
                           href="${pageContext.request.contextPath}/submitVote.do?state=down&content_id=${comment.commentId}&question_id=${question.questionId}"
                           class="btn btn-secondary btn-sm">-</a>
                    </div>

                </div>
                <hr>
            </c:forEach>
            <a class="text-muted" href="${pageContext.request.contextPath}/comment/${answer.answerId}">Ajouter un
                nouveau commentaire</a>
        </div>

    </c:forEach>

    <hr>
    <div class="container mb-3">
        <form action="${pageContext.request.contextPath}/submitAnswer.do" method="post">
            <div class="form-group">
                <label for="editor">Votre réponse</label>
                <textarea id="editor" name="content" class="form-control"></textarea>
            </div>
            <input type="hidden" name="question_id" value="${question.questionId}">
            <button type="submit" class="btn btn-primary">Ajouter</button>
        </form>
    </div>

</div>


<%@include file="fragments/footer.jsp" %>
