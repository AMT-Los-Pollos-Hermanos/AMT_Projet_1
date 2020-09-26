<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="fragments/header.jsp" %>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4">
            <h1 class="text-center">Connexion</h1>
            <div class="card">
                <div class="card-body">
                    <form action="" method="post">
                        <div class="form-group">
                            <label for="username">Nom d'utilisateur</label>
                            <input type="text" id="username" name="username" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="password">Mot de passe</label>
                            <input type="password" id="password" name="password" class="form-control" required>
                        </div>

                        <button type="submit" role="button" class="btn btn-primary">Login</button>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="fragments/footer.jsp" %>
