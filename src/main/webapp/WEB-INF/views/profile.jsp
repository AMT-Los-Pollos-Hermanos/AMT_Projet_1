<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>


<div class="container">
    <div class="row gutters-sm">
        <div class="col-md-4 mb-3">
            <div class="card">
                <div class="card-body">
                    <div class="d-flex flex-column align-items-center text-center">
                        <img src="https://cdn.business2community.com/wp-content/uploads/2017/08/blank-profile-picture-973460_640.png" alt="Admin" class="rounded-circle" width="150">
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
                            <button class="btn btn-dark">Changer mot de passe</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="fragments/footer.jsp" %>
