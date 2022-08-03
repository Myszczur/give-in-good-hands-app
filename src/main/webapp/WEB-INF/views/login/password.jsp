<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <h2>Resetuj hasło</h2>
    <form:form modelAttribute="user" action="/password/${token}">
        <div class="form-group">
            <form:input type="password" path="password" placeholder="Nowe hasło" required="required"/>
            <form:errors path="password" cssStyle="color: red; margin: 5px; font-size: 15px"/>
        </div>
        <div class="form-group">
            <input type="password" name="checkPassword" placeholder="Powtórz hasło" required/>
        </div>
        <div class="form-group form-group--buttons">
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
            <button class="btn" type="submit">Resetuj hasło</button>
        </div>
    </form:form>
    <c:if test="${not empty error}">
        <p style="color: red; margin: 5px; font-size: 15px">Podane hasła muszą być takie same!</p>
    </c:if>
</section>
<%@ include file="/WEB-INF/footer.jsp" %>