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
                <a href="<c:url value="/admin/foundations"/>" class="btn">Lista Fundacji</a>
            </h2>
        </div>

    </nav>
    <section class="steps">
        <div class="contact">
            <span class="description" style="font-size: 180%"><h3>Dodaj Fundację</h3></span>

            <form:form modelAttribute="foundation">
                <div class="form-group">
                    <form:input type="name" path="name" placeholder="Nazwa Fundacji" required="required"/>
                    <form:errors path="email" cssStyle="color: red; margin: 5px; font-size: 15px"/>
                </div>
                <div class="form-group">
                    <form:input type="text" path="description" placeholder="Opis i cel"/>
                    <form:errors path="description" cssStyle="color: red; margin: 5px; font-size: 15px"/>
                </div>
                <div class="form-group form-group--buttons">
                    <button class="btn" type="submit">Dodaj</button>
                </div>
            </form:form>
        </div>
    </section>
</header>
<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

