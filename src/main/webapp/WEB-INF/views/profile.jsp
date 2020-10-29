<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>


<div class="container">
    <div class="row gutters-sm">
        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img src="${pageContext.request.contextPath}/assets/img/avatar.png" alt="Admin" class="rounded-circle" width="150">
                        <div class="mt-3">
                            <h4> <c:out value="${sessionScope.currentUser.username}"/> </h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card mb-3">
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Nom</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${sessionScope.currentUser.firstName} ${sessionScope.currentUser.lastName}"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Email</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <c:out value="${sessionScope.currentUser.email}"/>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-sm-3">
                            <h6 class="mb-0">Mot de passe</h6>
                        </div>
                        <div class="col-sm-9 text-secondary">
                            <a type="button" class="btn btn-dark" href="${pageContext.request.contextPath}/changePassword">Changer mot de passe</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="fragments/footer.jsp" %>
