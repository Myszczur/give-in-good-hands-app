<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/header.jsp" %>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="#">Zaloguj</a></li>
            <li class="highlighted"><a href="#">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="403.jsp" class="btn btn--without-border active">Start</a></li>
            <li><a href="403.jsp#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="403.jsp#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="403.jsp#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="403.jsp#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Zaloguj się</h2>
    <form method="post" action="/login">
        <div class="form-group">
            <input type="email" name="email" placeholder="Email"/>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło"/>
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <c:if test="${error.equals('error')}">
            <p style="color: red; margin: 5px; font-size: 15px">Niepoprawny Email bądż Hasło! </p>
        </c:if>
        <c:if test="${error.equals('blocked')}">
            <p style="color: red; margin: 5px; font-size: 15px">Użytkownik został zablokowany, skontaktuj się z
                administratorem. </p>
        </c:if>
        <div class="form-group form-group--buttons">
            <a href="<c:url value="/register"/>" class="btn btn--without-border">Załóż konto</a>
            <button class="btn" type="submit">Zaloguj się</button>
        </div>

    </form>
</section>
<%@ include file="/WEB-INF/footer.jsp" %>