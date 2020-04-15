<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp"%>
</head>
<body class="d-flex flex-column h-100">
<%@include file="navbar.jsp"%>
<div class="container" style="margin-top:100px">
    <%@include file="groupShow.jsp"%>
    <%@include file="personList.jsp"%>
</div>


</body>
</html>
