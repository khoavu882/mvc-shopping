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
  <div class="pt-4 pl-5">
    <form:form class="d-flex align-items-center justify-content-center"
               action="${pageContext.request.contextPath}/register" modelAttribute="userForm"
               method="POST">
      <div class="col-lg-7">
        <h1 class="form-group d-flex align-items-center justify-content-center pb-3"><s:message code="messages.register_form"></s:message></h1>
        <div class="form-row">
          <label for="userName"><s:message code="messages.username"></s:message></label>
          <form:input type="text" id="userName" name="userName" onkeyup="checkExistUser()" path="userName"
                      class="form-control" placeholder="Your User Name"/>
          <span id="ajax-response-user" style="display: none"></span>
          <form:errors path="userName" class="error-message"/>
        </div>
        <div class="form-row">
          <label for="password"><s:message code="messages.password"></s:message></label>
          <form:input type="password" name="password" path="password" class="form-control" placeholder="Your Password"/>
          <form:errors path="password" class="error-message"/>
        </div>
        <div class="form-row">
          <label for="password"><s:message code="messages.email"></s:message></label>
          <form:input type="email" name="email" path="email" class="form-control" placeholder="Your Email"/>
          <form:errors path="email" class="error-message"/>
        </div>
        <div class="form-row">
          <div class="col">
            <label for="firstName"><s:message code="messages.first_name"></s:message></label>
            <form:input type="text" class="form-control" path="firstName" id="firstName" placeholder="First name"/>
            <form:errors path="firstName" class="error-message"/>
          </div>
          <div class="col">
            <label for="lastName"><s:message code="messages.last_name"></s:message></label>
            <form:input type="text" class="form-control" path="lastName" id="lastName" placeholder="Last name"/>
            <form:errors path="lastName" class="error-message"/>
          </div>
        </div>
        <div class="form-row">
          <label for="password"><s:message code="messages.address"></s:message></label>
          <form:input type="text" name="address" path="address" class="form-control" placeholder="Your Address"/>
          <form:errors path="address" class="error-message"/>
        </div>
        <div class="form-group">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="gridCheck" required="required">
            <label class="form-check-label" for="gridCheck">
              Check me out
            </label>
          </div>
        </div>
        <p style="color: #ff2c66"> ${registerFail}</p>

        <button class="btn btn-primary" type="submit" id="submit"><s:message
            code="messages.sign_up"></s:message></button>
      </div>
    </form:form>
  </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>