<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="fragments/header.jsp" %>

<div class="container">
    <h1 class="text-center">OverFlow</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/questions" method="get">
        <input type="text" name="s" class="form-control form-control-lg" placeholder="If you have to ask, you’ll never know. If you know, you need only ask.">
        <div class="d-flex justify-content-center mt-3">
            <button type="submit" class="btn btn-lg btn-primary">Search</button>
        </div>
    </form>
</div>

<%@ include file="fragments/footer.jsp" %>
