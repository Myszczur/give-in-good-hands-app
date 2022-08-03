<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/header.jsp" %>
<header>
    <nav class="container container--70">
        <ul class="nav--actions">
            <li><a href="/login">Zaloguj</a></li>
            <li class="/register"><a href="#">Załóż konto</a></li>
        </ul>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="403.jsp#steps" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="403.jsp#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="403.jsp#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="403.jsp#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</header>

<section class="login-page">
    <h2>Przypomnij hasło</h2>
    <form method="post">
        <span style="font-size: 170%">Podaj email na który zosanie wysłany link do zmiany hasła:</span>
        <div class="form-group">

        <input type="email" name="email" placeholder="Email" required/>
        </div>
        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Potwierdź</button>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
</section>
<%@ include file="/WEB-INF/footer.jsp" %>