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
                <p>Lista dostępnych adminów</p>
                <a href="<c:url value="/admin/admins/add"/>" class="btn">Dodaj Admina</a>
            </h2>
        </div>
    </nav>

    <section class="steps">
        <div class="contact">
            <span class="description" style="font-size: 180%"><h3>Lista Adminów</h3></span>

            <c:forEach items="${adminsList}" var="adminsList">
                <div class="steps--container">
                <span class="description" style="font-size: 180%">
                    <div class="title">Email: ${adminsList.email}</div>
                    <div class="title">Imię: ${adminsList.firstName}</div>
                    <div class="title">Nazwisko: ${adminsList.lastName}</div>
                </span>
                    <span>
                    <a href="<c:url value="/admin/admins/edit?id=${adminsList.id}"/>" class="btn">Edytuj</a>
                    <a href="<c:url value="/admin/admins/delete/${adminsList.id}"/>" class="btn">Usuń</a>
                </span>
                </div>
            </c:forEach>
        </div>
    </section>
</header>


<%@ include file="/WEB-INF/footer.jsp" %>