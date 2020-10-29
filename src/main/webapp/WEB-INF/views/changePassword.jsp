<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="fragments/header.jsp" %>

<div class="container">

    <form action="changePassword.do" method="post">
        <div class="form-group">
            <label for="oldPassword">Ancien mot de passe</label>
            <input type="password" class="form-control" id="oldPassword" name="oldPassword"
                   aria-describedby="oldPasswordHelp" placeholder="Entrer l'ancien mot de passe">
        </div>
        <div class="form-group">
            <label for="newPassword">Nouveau mot de passe</label>
            <input type="password" class="form-control" id="newPassword" name="newPassword"
                   aria-describedby="newPasswordHelp" placeholder="Entrer le nouveau mot de passe">
        </div>

        <div class="form-group">
            <label for="newPasswordAgain">Répéter le mot de passe</label>
            <input type="password" class="form-control" id="newPasswordAgain" name="newPasswordAgain"
                   aria-describedby="newPasswordAgainHelp" placeholder="Entrer encore une fois">
        </div>
        <button type="submit" class="btn btn-primary" id="changePasswordBtn">Changer</button>
    </form>

</div>


<%@include file="fragments/footer.jsp" %>
