<c:url var="grouplist" value="/group/list"/>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Annuaire</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${grouplist}">Liste Groupe</a></li>
                <li><a href="">Liste Persons</a></li>
                <li><a href="">Recherche</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${user.id==0 || user.id==null}">
                        <li><a href="/login"><span
                                class="glyphicon glyphicon-log-in"></span> Se connecter</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/person/show/${user.id}"><span
                                class="glyphicon glyphicon-user"> </span> <c:out value="${user.name}"/> <c:out
                                value="${user.firstName}"/></a></li>

                        <li><a href="/logout"><span
                                class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>

                    </c:otherwise>
                </c:choose>

            </ul>
        </div>
    </div>
</nav>