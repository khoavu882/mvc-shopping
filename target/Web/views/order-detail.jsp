<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>

  <jsp:include page="head.jsp"></jsp:include>

</head>
<body class="h-100">

<jsp:include page="navbar.jsp"></jsp:include>
<main class="container">
  <div class="user-table">
    <table class="table">
      <thead class="thead-dark">
      <tr>
        <th scope="col">#</th>
        <th scope="col">Product Name</th>
        <th scope="col">Quantity</th>
        <th scope="col">Amount</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${orderDetails.list}" var="item" varStatus="loop">
      <tr>
        <th scope="row">${loop.index}</th>
        <td><a href="${pageContext.request.contextPath}/product/detail?productId=${item.product.productId}">${item.product.productName}</a></td>
        <td>${item.quantity}</td>
        <td>${item.quantity*item.product.productPrice}</td>
      </tbody>
      </c:forEach>
    </table>
  </div>
  <c:if test="${orderDetails.totalPages > 1}">
    <div class="container">
      <div class="row d-flex align-items-center justify-content-center p-3">
        <ul class="pagination">
          <c:forEach items="${orderDetails.navigationPages}" var="page">
            <c:if test="${page != -1 }">
              <c:if test="${page == orderDetails.currentPage}">
                <a class="btn btn-dark m-1"
                   href="${pageContext.request.contextPath}/&page=${page}">${page}</a>
              </c:if>
              <c:if test="${page != orderDetails.currentPage}">
                <a class="btn btn-outline-dark m-1"
                   href="${pageContext.request.contextPath}/&page=${page}">${page}</a>
              </c:if>
            </c:if>
            <c:if test="${page == -1 }">
              <span class="nav-item"> ... </span>
            </c:if>
          </c:forEach>
        </ul>
      </div>
    </div>
  </c:if>
</main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
