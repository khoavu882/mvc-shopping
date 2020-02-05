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

<main class="container-fluid">

  <div class="container">
    <div class="row">
      <div class="col-lg-3 filter-product">
        <p>Tìm kiếm theo "${keywords}"</p>
      </div>

      <div class="col-lg-9">
        <div class="container">
          <div id="product" class="product-grid h-auto">
            <div class="row">
              <c:forEach items="${productsList.list}" var="product" varStatus="loop">
                <div id ="item" class="item col-md-6">
                  <div class="product">
                    <div class="col">
                      <div class="row d-flex justify-content-center align-items-center">
                        <img class="product-image"
                             src="${pageContext.request.contextPath}/lib/img/productsImage/MSI%20GE63%2015.6.jpg"
                             alt="">
                      </div>
                      <div class="row">
                        <a class="product-title" href="${pageContext.request.contextPath}/product/detail?productId=${product.productId}">
                          <span>${product.productName}</span>
                        </a>
                      </div>
                      <div class="row">
                        <div class="col-md-7">
                          <div class="row">
                            <i class="material-icons">monetization_on</i>
                            <strong><s:message code="messages.product_price"></s:message>: <fmt:formatNumber value="${product.productPrice}"
                                                                                                             maxFractionDigits="0"/></strong>
                          </div>
                        </div>
                        <a href="${pageContext.request.contextPath}/cart/add?productId=${product.productId}"><button class="btn btn-success"><s:message code="messages.add_cart"></s:message></button></a>
                      </div>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div>
            <c:if test="${productsList.totalPages > 1}">
              <div class="container">
                <div class="row d-flex align-items-center justify-content-center p-3">
                  <ul class="pagination">
                    <c:forEach items="${productsList.navigationPages}" var="page">
                      <c:if test="${page != -1 }">
                        <c:if test="${page == productsList.currentPage}">
                          <a class="btn btn-dark m-1"
                             href="${pageContext.request.contextPath}/?keywords=${keywords}&page=${page}">${page}</a>
                        </c:if>
                        <c:if test="${page != productsList.currentPage}">
                          <a class="btn btn-outline-dark m-1"
                             href="${pageContext.request.contextPath}/?keywords=${keywords}&page=${page}">${page}</a>
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
          </div>
        </div>
      </div>
    </div>
  </div>
</main>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>