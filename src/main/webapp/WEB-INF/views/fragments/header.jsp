<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css">

    <title>Overflow</title>

    <style>
        body {
            padding-top: 5rem;
        }
    </style>
</head>
<body>

<c:if test="${sessionScope.flash != null}">
<div class="container">
    <div class="alert alert-<c:out value="${sessionScope.flash.type}" />">
        <c:out value="${sessionScope.flash.message}"/>
    </div>
</div>
</c:if>

<jsp:include page="fragments/navbar.jsp"/>
