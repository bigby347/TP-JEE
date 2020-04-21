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
    <div class="card">
        <div class="card-body">
            <div class="row">
                <div class="col-md-12">
                    <h4>Add a user</h4>
                    <hr>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <form:form method="POST" modelAttribute="person">

                        <form:errors path="*" cssClass="alert alert-danger" element="div"/>
                        <div class="form-group row">
                            <label for="name" class="col-4 col-form-label">Name</label>
                            <div class="col-8">
                                <form:input  path="name" class="form-control here"/>
                                <form:errors path="name" cssClass="alert alert-warning"
                                             element="div"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="firstName" class="col-4 col-form-label">First Name*</label>
                            <div class="col-8">
                                <form:input  path="firstName" class="form-control here"/>
                                <form:errors path="firstName" cssClass="alert alert-warning"
                                             element="div"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="birthDay" class="col-4 col-form-label">Date Of Birth</label>
                            <div class="col-8">
                                <fmt:formatDate value="${person.birthDay}" var="dateString" pattern="yyyy-MM-dd" />
                                <form:input type="date" path="birthDay" value="${dateString}" class="form-control" />
                                <form:errors path="birthDay" cssClass="alert alert-warning"
                                             element="div" />
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="email" class="col-4 col-form-label">Email</label>
                            <div class="col-8">
                                <form:input  path="email" class="form-control here"/>
                                <form:errors path="email" cssClass="alert alert-warning"
                                             element="div"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="website" class="col-4 col-form-label">Website</label>
                            <div class="col-8">
                                <form:input  path="website" class="form-control here"/>
                                <form:errors path="website" cssClass="alert alert-warning"
                                             element="div"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="password" class="col-4 col-form-label">Password*</label>
                            <div class="col-8">
                                <form:input  path="password" class="form-control here"/>
                                <form:errors path="password" cssClass="alert alert-warning"
                                             element="div"/>
                            </div>
                        </div>
                        <div class="form-group row ">
                            <label for="groupe" class="col-4 col-form-label">Groupe*</label>
                            <div class="col-8">
                                <form:input  disabled="true" path="groupe" value="${person.groupe.name}" class="form-control here"/>
                                <form:errors path="groupe" cssClass="alert alert-warning"
                                             element="div"/>
                            </div>
                        </div>

                        <div class="form-group row">
                            <div class="offset-4 col-8">
                                <button name="submit" type="submit" class="btn btn-primary">Add a user</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
