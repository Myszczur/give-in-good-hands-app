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
                <a href="<c:url value="/admin/users"/>" class="btn">Lista Użytkowników</a>
            </h2>
        </div>
    </nav>

    <section class="steps">
        <div class="contact">
            <span class="description" style="font-size: 180%"><h3>Wpisz dane nowego użytkownika</h3></span>

            <form:form modelAttribute="user">
                <div class="form-group">
                    <form:input type="email" path="email" placeholder="Email" required="required"/>
                    <form:errors path="email" cssStyle="color: red; margin: 5px; font-size: 15px"/>
                </div>
                <div class="form-group">
                    <form:input type="text" path="firstName" placeholder="Imię"/>
                    <form:errors path="firstName" cssStyle="color: red; margin: 5px; font-size: 15px"/>
                </div>
                <div class="form-group">
                    <form:input type="text" path="lastName" placeholder="Nazwisko"/>
                    <form:errors path="lastName" cssStyle="color: red; margin: 5px; font-size: 15px"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="password" placeholder="Hasło" required="required"/>
                    <form:errors path="password" cssStyle="color: red; margin: 5px; font-size: 15px"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="matchingPassword" placeholder="Powtórz hasło" required="required"/>
                    <form:errors path="matchingPassword" cssStyle="color: red; margin: 5px; font-size: 15px"/>
                    <c:if test="${not empty error}">
                        <p style="color: red; margin: 5px; font-size: 15px">Hasła muszą być podobne</p>
                    </c:if>
                </div>
                <div class="form-group form-group--buttons">
                    <button class="btn" type="submit">Dodaj użytkownika</button>
                </div>
            </form:form>
        </div>
    </section>
</header>


<script src="<c:url value="../resources/css/style.css"/>"></script>
</body>
</html>

