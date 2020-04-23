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
    <div class="jumbotron p-4 p-md-5 text-white rounded bg-dark">
        <div class="col-md-6 px-0">
            <h1 class="display-4 font-italic">Hello</h1>
            <c:choose>
            <c:when test="${user.id==0 || user.id==null}">
                <p class="lead my-3">
                    Welcome on the home page
                </p>
                <p class="lead mb-0"><a href="/login" class="text-white font-weight-bold">Login now</a></p>
            </c:when>
            <c:otherwise>
            <p class="lead my-3">
                    ${user.name}, Welcome on the home page
            </p>
            </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="row mb-2">
        <div class="col-md-6">
            <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                    <h3 class="mb-0 text-primary">Groups List</h3>
                    <a class="nav-link" href="${grouplist}"><strong class="d-inline-block mb-2 text-primary">See Groups List >></strong></a>
                    <form class="form-inline mt-2 mt-md-0" action="/searchGroups" method="post">
                        <input class="form-control lg-2" name="inputGroupResearch" type="text" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-primary my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
                <div class="col p-4 d-flex flex-column position-static">
                    <h3 class="mb-0 text-success">Persons List</h3>
                    <a class="nav-link" href="${personList}"><strong class="d-inline-block mb-2 text-success">See Persons list >></strong></a>
                    <form class="form-inline mt-2 mt-md-0" action="/searchPersons" method="post">
                        <input class="form-control md-2" name="inputPersonResearch" type="text" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<%@include file="footer.jsp" %>
