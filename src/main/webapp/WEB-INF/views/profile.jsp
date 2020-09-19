<%--
  Created by IntelliJ IDEA.
  User: julien
  Date: 19.09.20
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>Hello guys, this is my profile page. Bisous ${requestScope.user.username}</h1>

<a href=".">List of question</a>
<a href="question">Question</a>
<a href="new-question">New question</a>
<a href="login">Login</a>
<a href="profile">Profile</a>
</body>
</html>
