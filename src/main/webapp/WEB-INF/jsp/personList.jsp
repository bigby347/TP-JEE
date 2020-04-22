<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <h2 class="sub-header">Persons List</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${persons}" var="p">
            <tr>
                <td>
                    <a href="/person/show/${p.id}"><c:out value="${p.name}"/></a>
                </td>
                <td>
                    <a href="/person/show/${p.id}"><c:out value="${p.firstName}"/></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="footer.jsp" %>
