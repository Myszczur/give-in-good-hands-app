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
                <p><sec:authentication property="principal.username"/>, co chcesz zrobić?</p>
                <a href="<c:url value="/donations/add"/>" class="btn">Oddaj w dobre ręce</a>
                <a href="<c:url value="/user/edit"/>" class="btn">Edytuj dane</a>
            </h2>
        </div>
    </nav>

    <section class="steps">
        <div class="contact">
            <span class="description" style="font-size: 180%"><h3>Twoje dary</h3></span>

            <c:forEach items="${currentUserDonations}" var="currentUserDonations">
                <div class="steps--container">
                <span class="description" style="font-size: 180%">
                    <div class="title">Miasto: ${currentUserDonations.city}</div>
                    <div class="title">Data: ${currentUserDonations.pickUpDate}</div>
                    <div class="title">Fundacja: ${currentUserDonations.institution.name}</div>
                    <div class="title">Status: ${currentUserDonations.status}</div>
                   <c:if test="${not empty currentUserDonations.received}">
                        <div class="subtitle">
                            Odebrano: <span>${currentUserDonations.received}</span>
                        </div>
                    </c:if>
                </span>
                    <span>
                    <a href="<c:url value="/user/show/${currentUserDonations.id}"/>" class="btn">Szczegóły</a>
                </span>
                </div>
            </c:forEach>
        </div>
    </section>
</header>


<script src="<c:url value="../resources/css/style.css"/>"></script>
</body>
</html>

