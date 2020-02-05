<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page session="false" %>
<html>
<head>

  <jsp:include page="head.jsp"></jsp:include>

</head>
<body class="h-100">

<jsp:include page="navbar.jsp"></jsp:include>

<div class="container">

  <c:if test="${empty cartForm or empty cartForm.cartLines}">
    <div class="container">
      <div class="row justify-content-center align-items-center">
        <h2 class="mt-3">There is no items in Cart</h2>
      </div>
      <div class="row justify-content-center align-items-center">
        <a class="btn btn-primary mb-3" href="${pageContext.request.contextPath}/">Show Product List</a>
      </div>
    </div>
  </c:if>

  <c:if test="${not empty cartForm and not empty cartForm.cartLines}">
  <div class="container">

      <c:forEach items="${cartForm.cartLines}" var="cartLineInfo" varStatus="loop">
        <div class="row border-bottom">
          <div class="col-lg-4"><img class="product-image"
                                     src="${pageContext.request.contextPath}/lib/img/productsImage/MSI%20GE63%2015.6.jpg"/>
          </div>
          <div class="col-lg-8">
            <div class="pt-5">
              <input id="productId" type="hidden" value="${cartLineInfo.productInfo.productId}">
              <h2>${cartLineInfo.productInfo.productName}</h2>
              <p>Price: <span class="price special-text"><fmt:formatNumber
                  value="${cartLineInfo.productInfo.productPrice}"
                  type="currency" maxFractionDigits="0"/></span>
              </p>
              <label>Quantity:
                <input id="quantity" name="quantity" onkeyup="checkOutOfStock()" type="text" value="${cartLineInfo.quantity}"/>
                <span id="ajax-response-quantity" style="display: none"></span>
              </label>
              <h5>Total: <fmt:formatNumber value=" ${cartLineInfo.amount}" type="currency" maxFractionDigits="0"/></h5>
              <a class="badge badge-primary" id="regLink"
                 href="${pageContext.request.contextPath}/cart/update?productId=${cartLineInfo.productInfo.productId}&quantity=">Update</a>
              <a class="badge badge-danger"
                 href="${pageContext.request.contextPath}/cart/remove?productId=${cartLineInfo.productInfo.productId}">Remove</a>
            </div>
          </div>
        </div>
      </c:forEach>
      <div class="container pt-3">
        <h4 class="">Grand Total: <span class="subtotal"><fmt:formatNumber value="${cartForm.amountTotal}" type="currency"
                                                                           maxFractionDigits="0"/></span</h4>
        <div style="clear: both"></div>
        <a class="btn btn-success" href="${pageContext.request.contextPath}/cart/accept">Check Out</a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/">Continue Buy</a>
      </div>
    </c:if>

  </div>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
