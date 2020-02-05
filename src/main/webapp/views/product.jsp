<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="head.jsp"></jsp:include>

<body class="h-100">

<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">
  <div class="row">
    <div class="col-lg-4">
      <img class="product-image" src="${pageContext.request.contextPath}/lib/img/productsImage/MSI_NB_GE75_N18_photo05__85085.1555719062.jpg" alt="">
    </div>
    <div class="col-lg-8">
      <div class="row">
        <div class="pt-5">
          <h3>${product.productName}</h3>
        </div>
      </div>
    </div>
  </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
