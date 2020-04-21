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
    <div class="panel panel-default">
        <div class="panel-heading"><h4>Profile</h4></div>
        <div class="panel-body">

            <div class="box box-info">

                <div class="box-body">
                    <div class="col-sm-6">
                        <h4 style="color:#00b1b1;"><c:out value="${person.name} ${person.firstName}"/></h4></span>
                    </div>
                    <div class="clearfix"></div>
                    <hr style="margin:5px 0 5px 0;">


                    <div class="col-sm-5 col-xs-6">First Name:</div>
                    <div class="col-sm-7 col-xs-6 "><c:out value="${person.firstName}"/></div>
                    <div class="clearfix"></div>
                    <div class="bot-border"></div>

                    <div class="col-sm-5 col-xs-6">Last Name:</div>
                    <div class="col-sm-7"><c:out value="${person.name}"/></div>
                    <div class="clearfix"></div>
                    <div class="bot-border"></div>

                    <div class="col-sm-5 col-xs-6">Group:</div>
                    <div class="col-sm-7"><c:out value="${person.groupe.name}"/></div>

                    <div class="clearfix"></div>
                    <div class="bot-border"></div>
                    <div class="col-sm-5 col-xs-6">Website:</div>
                    <div class="col-sm-7"><c:out value="${person.website}"/></div>

                    <div class="clearfix"></div>
                    <div class="bot-border"></div>
                    <c:if test="${user.id !=0}">
                        <div class="col-sm-5 col-xs-6 tital ">Date Of Birth:</div>
                        <div class="col-sm-7"><c:out value="${person.birthDay}"/></div>

                        <div class="clearfix"></div>
                        <div class="bot-border"></div>

                        <div class="col-sm-5 col-xs-6 tital ">Email:</div>
                        <div class="col-sm-7"><c:out value="${person.email}"/></div>

                        <div class="clearfix"></div>
                        <div class="bot-border"></div>
                    </c:if>

                    <c:if test="${user.id == person.id}">
                        <div class="col-sm-5 col-xs-6 tital ">Password:</div>
                        <div class="col-sm-7"><c:out value="${person.password}"/></div>

                        <div class="clearfix"></div>
                        <div class="bot-border"></div>
                        <a href="/person/edit/${person.id}?groupId=${person.groupe.id}">Modifier</a>
                    </c:if>

                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>