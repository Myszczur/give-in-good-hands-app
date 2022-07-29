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
                  <p>Jesteś zalogowany jako Administrator, co chcesz zrobić?</p>
              </h2>
          </div>
      </nav>

      <div class="about-us--text">
                  <ul>
                    <li><a href="<c:url value="/admin/users"/>" class="btn btn--without-border">Użytkownicy</a></li>
                    <li><a href="<c:url value="/admin/foundations"/>" class="btn btn--without-border">Fundacje</a></li>
                    <li><a href="<c:url value="/admin/admins"/>" class="btn btn--without-border">Administratorzy</a></li>
                    <li><a href="<c:url value="/admin/donations"/>" class="btn btn--without-border">Dary</a></li>
                  </ul>
      </div>
    </header>


<%@ include file="/WEB-INF/footer.jsp" %>