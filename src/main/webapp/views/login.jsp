<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

  <jsp:include page="head.jsp"></jsp:include>

</head>
<body class="h-100">
<jsp:include page="navbar.jsp"></jsp:include>

<main class="container">
  <div class="pt-4 pl-4">
    <form:form class="d-flex align-items-center justify-content-center"
               action="${pageContext.request.contextPath}/login-check" method="post">
      <div class=" col-lg-5">
        <h1 class="form-group d-flex align-items-center justify-content-center pb-3"><s:message code="messages.login_form"></s:message></h1>
        <div class="form-group">
          <label for="login-userName"><s:message code="messages.username"></s:message></label>
          <input type="text" class="form-control" id="login-userName" name="username" placeholder="User Name">
        </div>
        <div class="form-group">
          <label for="login-password"><s:message code="messages.password"></s:message></label>
          <input type="password" class="form-control" id="login-password" name="password" placeholder="Password">
        </div>
        <div class="form-group form-check">
          <input type="checkbox" class="form-check-input" id="login-check">
          <label class="form-check-label" for="login-check">Check me out</label>
        </div>
        <div class="form-group d-flex align-items-center justify-content-center">
          <button type="submit" class="btn btn-primary"><s:message code="messages.sign_in"></s:message></button>
        </div>
      </div>
    </form:form>
  </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
