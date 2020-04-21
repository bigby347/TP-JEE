<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/include.jsp" %>
<html>
<head>
    <title>Home Page</title>
    <%@ include file="/WEB-INF/jsp/head-bootstrap.jsp" %>
</head>
<body class="d-flex flex-column h-100">
<%@include file="navbar.jsp" %>
<div class="container" style="margin-top:100px">
    <form method="get" action="/group/list">
        <button type="submit" class="btn btn-default">
            <span class="glyphicon glyphicon-chevron-left"></span>Retour</a>
        </button>
    </form>
    <h2 class="sub-header">Persons in <c:out value="${group.name}"/></h2>
    <div class="table-responsive">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>First Name</th>
                <c:choose>
                <c:when test="${user.id==0 || user.id==null}">
                </c:when>
                <c:otherwise>
                    <th>Email</th>
                    <th>Birthday</th>
                </c:otherwise>

                </c:choose>
                <th>Website</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${persons}" var="p">
                <tr>
                    <td class="row" data-href="/person/show/${p.id}">
                        <a href="/person/show/${p.id}" class="stretched-link"><c:out value="${p.name}"/></a>
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
                    <td><a href="<c:out value="${p.website}"/>" class="stretched-link"><c:out value="${p.website}"/></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function ($) {
        $(".row").click(function () {
            window.document.location = $(this).data("href");
        });
    });
</script>
</html>