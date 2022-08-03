<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/header.jsp" %>

<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                <sec:authorize access="isAuthenticated()">
                    <p>Witaj: <sec:authentication property="principal.username"/></p>
                </sec:authorize>

                <ul class="dropdown">
<%--                    <li><a href="#">Profil</a></li>--%>
<%--                    <li><a href="#">Moje zbiórki</a></li>--%>
                    <li> <sec:authorize access="isAuthenticated()">
                        <form action="<c:url value="/logout"/>" method="post">
                            <input type="submit" value="Wyloguj">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </sec:authorize>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>

    <div class="slogan container container--90">
        <h2>
            Czy na pewno chcesz usunąć Admina ${AdminById.email} z bazy danych ?
        </h2>
        <input type="hidden" name="id" value="${AdminById.id}"/>
        <a href="<c:url  value="/admin/admins/delete?id=${AdminById.id}"/>">
            <i></i><b>Tak</b> </a>
        <a href="<c:url  value="/admin/admins"/>">
            <i></i><b>Anuluj</b> </a>
    </div>
</header>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

