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
  <div class="col">
    <div class="row justify-content-center align-items-center">
      <span>${message}</span>
    </div>
    <div class="row justify-content-center align-items-center">
      <a href="${pageContext.request.contextPath}/cart/manager"></a>
    </div>
  </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>