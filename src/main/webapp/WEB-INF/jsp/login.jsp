<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>
    <link href="signin.css" rel="stylesheet">
</head>
<body class="d-flex flex-column h-100">
<div class="container">
    <form method="get" action="/home">
        <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-chevron-left"></span>Retour</a>
        </button>
    </form>

    <form action="/connection" method="POST" class="form-signin">
        <h2 class="form-signin-heading">Se connecter</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" id="inputEmail" name="inputEmail" class="form-control" placeholder="Email address"
               required="" autofocus="">
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password"
               required="">
        <button class="btn btn-lg btn-primary btn-block" value="sign" type="submit">Sign in</button>
    </form>

</div>
</body>
