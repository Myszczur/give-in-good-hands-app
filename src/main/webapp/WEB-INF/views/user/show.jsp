<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
                    <li><sec:authorize access="isAuthenticated()">
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
                <a href="<c:url value="/user"/>" class="btn">Twój panel główny</a>
            </h2>
        </div>
    </nav>
    <section class="steps">
        <div class="contact">
            <h2>Lista Zbiórek</h2>
            <a href="<c:url value="/form"/>" class="btn">Dodaj</a>
            <div class="form-group form-group--50" style="margin:2em; font-size:2em; border: 0.1em solid black">
                <span class="description">
                    <div class="title"><span>${donations.institution.name}</span></div>
                    <div class="title">Dary:
                        <span>
                            <c:forEach items="${donations.categories}" var="category">
                                <span> ${category.name}</span>
                            </c:forEach>
                    </div>
                    <div class="title">Liczba worków: <span>${donations.quantity}</span></div>
                    <div class="title">Data odbioru: <span>${donations.pickUpDate} ${donations.pickUpTime}</span></div>
                    <div class="subtitle">
                        Status: <span>${donations.status}</span>
                    </div>
                    <c:if test="${not empty donations.received}">
                        <div class="subtitle">
                            Odebrano: <span>${donations.received}</span>
                        </div>
                    </c:if>
                    <div class="subtitle">
                        Dodano: <span>${donations.created}</span>
                    </div>
                </span>
                <span>
                    <a href="<c:url value="/user"/>" class="btn">Wróć</a>
                    <c:if test="${donations.status.equals('Nieodebrane')}">
                        <a href="<c:url value="/user/received/${donations.id}"/>" class="btn">Odebrane</a>
                    </c:if>
                </span>
            </div>
        </div>
    </section>
</header>

<script src="<c:url value="../../../resources/css/style.css"/>"></script>
</body>
</html>
