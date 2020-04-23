<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>
    <link href="signin.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form action="/resetPassword" method="POST" class="form-signin">
        <h1 class="h3 mb-3 font-weight-normal">Reset Password</h1>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="email" name="inputEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>

    <form method="get" action="/home">
    <button type="submit" class="btn btn-outline-secondary">
        <span class="glyphicon glyphicon-chevron-left"></span>Retour</a>
    </button>
</form>
</div>

<%@include file="footer.jsp" %>