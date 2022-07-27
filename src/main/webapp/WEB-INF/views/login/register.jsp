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
          <li><a href="<c:url value="/login"/>">Zaloguj</a></li>
        </ul>

        <ul>
          <li><a href="/" class="btn btn--without-border active">Start</a></li>
          <li><a href="index.html#steps" class="btn btn--without-border">O co chodzi?</a></li>
          <li><a href="index.html#about-us" class="btn btn--without-border">O nas</a></li>
          <li><a href="index.html#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
          <li><a href="index.html#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
      </nav>
    </header>

    <section class="login-page">
      <h2>Załóż konto</h2>
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
          <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
          <button class="btn" type="submit">Załóż konto</button>
        </div>
      </form:form>
    </section>

<%@ include file="/WEB-INF/footer.jsp" %>