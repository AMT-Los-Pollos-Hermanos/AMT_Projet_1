<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 19.09.20
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login / Register</title>
</head>
<body>
<h1>Login / Register</h1>

<form action="action_page.php" method="post">
    <div class="container">
        <label for="uname"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="uname" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

        <button type="submit">Login</button>
    </div>

</form>

<a href=".">List of question</a>
<a href="question">Question</a>
<a href="new-question">New question</a>
<a href="login">Login</a>
<a href="profile">Profile</a>
</body>
</html>
