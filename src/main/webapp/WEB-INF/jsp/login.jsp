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
    <form action="/connection" method="POST" class="form-signin">
        <img class="mb-4" src="/docs/4.4/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="inputPassword" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox mb-3">
            <a href="/resetPassword">Reset Password</a>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
    </form>

    <form method="get" action="/home">
        <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-chevron-left"></span>Retour</a>
        </button>
    </form>

</div>
<%@include file="footer.jsp" %>
