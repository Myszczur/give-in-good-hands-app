<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/header.jsp" %>
<body>
<header class="header--form-page">
    <nav class="container container--70">
        <ul class="nav--actions">
            <li class="logged-user">
                <sec:authorize access="isAuthenticated()">
                    <p>Witaj: <sec:authentication property="principal.username"/></p>
                </sec:authorize>

                <ul class="dropdown">
                    <li><a href="<c:url value="/admin/admin"/>">Panel główny</a></li>
                    <li><a href="<c:url value="/admin/users"/>">Użytkownicy</a></li>
                    <li><a href="<c:url value="/admin/donations"/>">Dary</a></li>
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
        <div class="steps--container">
            <h2>
                <p>Lista dostępnych użytkowników</p>
                <a href="<c:url value="/admin/users/adduser"/>" class="btn">Dodaj Użytkownika</a>
            </h2>
        </div>
    </nav>

    <section class="steps">
        <div class="contact">
            <span class="description" style="font-size: 180%"><h3>Lista Użytkowników</h3></span>

            <c:forEach items="${usersList}" var="usersList">
                <div class="steps--container">
                <span class="description" style="font-size: 180%">
                    <div class="title">Email: ${usersList.email}</div>
                    <div class="title">Imię: ${usersList.firstName}</div>
                    <div class="title">Nazwisko: ${usersList.lastName}</div>
                </span>
                    <span>
                    <a href="<c:url value="/admin/users/edit?id=${userList.id}"/>" class="btn">Edytuj</a>
                    <a href="<c:url value="/admin/users/delete/${usersList.id}"/>" class="btn">Usuń</a>
                        <c:if test="${usersList.accountNonBlocked == false}">
                            <a href="<c:url value="/admin/users/block?id=${usersList.id}"/>" class="btn">Odblokuj</a>
                        </c:if>
                    <c:if test="${usersList.accountNonBlocked == true}">
                        <a href="<c:url value="/admin/users/block?id=${usersList.id}"/>" class="btn">Zablokuj</a>
                    </c:if>
                </span>
                </div>
            </c:forEach>
        </div>
    </section>
</header>
<script src="<c:url value="../resources/css/style.css"/>"></script>
</body>
</html>

