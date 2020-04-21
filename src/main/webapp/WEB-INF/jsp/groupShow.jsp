<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container" style="margin-top:100px">
    <form method="get" action="/group/list">
        <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-chevron-left"></span>Retour</a>
        </button>
    </form>
    <h2 class="sub-header">Persons in <c:out value="${group.name}"/></h2>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Name</th>
                <th scope="col">First Name</th>
                <c:choose>
                <c:when test="${user.id==0 || user.id==null}">
                </c:when>
                <c:otherwise>
                    <th scope="col">Email</th>
                    <th scope="col">Birthday</th>
                </c:otherwise>

                </c:choose>
                <th scope="col">Website</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${persons}" var="p">
                <tr class="tabletr" data-href="/person/show/${p.id}">
                    <td>
                        <a href="/person/show/${p.id}"><c:out value="${p.name}"/></a>
                    </td>
                    <td><c:out value="${p.firstName}"/></td>
                    <c:choose>
                        <c:when test="${user.id==0 || user.id==null}">
                        </c:when>
                        <c:otherwise>
                            <td><c:out value="${p.email}"/></td>
                            <td><c:out value="${p.birthDay}"/></td>
                        </c:otherwise>
                    </c:choose>
                    <td><a href="<c:out value="${p.website}"/>"><c:out value="${p.website}"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
</div>
</body>

</html>