<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="/">Annuaire</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${grouplist}">Groups List</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="${personList}">Persons List</a>
            </li>
        </ul>
        <div class="btn-group" role="group" aria-label="Basic example">
        <c:choose>
            <c:when test="${user.id==0 || user.id==null}">
                <a href="/login">
                    <button type="button" class="btn btn-secondary">Login</button>
                </a>
            </c:when>
            <c:otherwise>
                <a href="/person/show/${user.id}">
                    <button type="button" class="btn btn-secondary"><c:out value="${user.name}"/> <c:out
                        value="${user.firstName}"/></button>
                </a>
                <a href="/logout">
                    <button type="button" class="btn btn-secondary">Logout</button>
                </a>
            </c:otherwise>
        </c:choose>
        </div>
    </div>
</nav>
