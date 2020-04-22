<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container">
    <h2 class="sub-header">Groups List</h2>

    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="g">
        <tr>
            <td>
                <a href="/group/show/${g.id}"><c:out value="${g.name}"/></a>
            </td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<%@include file="footer.jsp" %>
