<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>
</head>
<body class="d-flex flex-column h-100">
<%@include file="navbar.jsp" %>
<div class="container" style="margin-top:100px">
    <h2 class="sub-header">Groups List</h2>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Group Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${groups}" var="g">
                <tr>
                    <td class="row"><a href="/group/show/${g.id}" class="stretched-link"><c:out value="${g.name}"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
