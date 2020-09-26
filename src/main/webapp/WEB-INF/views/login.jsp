<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="fragments/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header" style="font-size: 1.5rem">Créer un nouveau compte</div>
                <div class="card-body">
                    <form action="register.do" method="post">
                        <div class="form-group">
                            <label for="rFirstName">Prénom</label>
                            <input type="text" id="rFirstName" name="firstName" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="rLastName">Nom</label>
                            <input type="text" id="rLastName" name="lastName" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="rEmail">E-mail</label>
                            <input type="email" id="rEmail" name="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="rUsername">Nom d'utilisateur</label>
                            <input type="text" id="rUsername" name="username" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="rPassword">Mot de passe</label>
                            <input type="password" id="rPassword" name="password" class="form-control" required>
                        </div>
                        <button type="submit" role="button" class="btn btn-primary">S'inscrire</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="card">
                <div class="card-header" style="font-size: 1.5rem">Connexion</div>
                <div class="card-body">
                    <form action="login.do" method="post">
                        <div class="form-group">
                            <label for="lUsername">Nom d'utilisateur</label>
                            <input type="text" id="lUsername" name="username" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label for="lPassword">Mot de passe</label>
                            <input type="password" id="lPassword" name="password" class="form-control" required>
                        </div>
                        <button type="submit" role="button" class="btn btn-primary" id="loginBtn">Se connecter</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<%@ include file="fragments/footer.jsp" %>
