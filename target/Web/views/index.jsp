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

<main class="container-fluid">
  <div class="container">
    <c:choose>
      <c:when test="${pageContext.request.userPrincipal.name != null}">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/cart/manager">${pageContext.request.userPrincipal.name}</a>
        <a class="btn btn-primary" href="<c:url value='/logout' />"><s:message code="messages.logout"></s:message></a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/manager"><s:message code="messages.user_manager"></s:message></a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/product/list"><s:message code="messages.product_manager"></s:message></a>
      </c:when>
      <c:otherwise>
        <a class="btn btn-primary" href="<c:url value='/login' />"><s:message code="messages.sign_in"></s:message></a>
        <a class="btn btn-primary" href="<c:url value='/register' />"><s:message code="messages.sign_up"></s:message></a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/manager"><s:message code="messages.user_manager"></s:message></a>
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/product/list"><s:message code="messages.product_manager"></s:message></a>
      </c:otherwise>
    </c:choose>
  </div>

  <div class="container">
    <div class="mini-cart box-cart">
      <form action="${pageContext.request.contextPath}/cart/info" method="get" class="last">
        <input type="hidden" name="cmd" value="_cart">
        <input type="hidden" name="display" value="1">
        <button class="view-cart btn btn-danger" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down"
                                                                                         aria-hidden="true"></i>
        </button>
      </form>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-lg-3 filter-product">
        <div class="pt-1">
          <h3><s:message code="messages.category"></s:message></h3>
          <div class="form">
            <div class="col">
              <a class="row pl-5" href="${pageContext.request.contextPath}/?category=1"><s:message code="messages.gaming"></s:message></a>
              <a class="row pl-5" href="${pageContext.request.contextPath}/?category=2"><s:message code="messages.business"></s:message></a>
            </div>
          </div>
        </div>

<%--        <div>--%>
<%--          <h3><s:message code="messages.price_range"></s:message></h3>--%>
<%--          <div class='form'>--%>
<%--            <div>--%>
<%--              <div class='slider-label -thumb'>--%>
<%--                <span class='center -price'>0</span>--%>
<%--              </div>--%>
<%--              <div class='form-slider -price' data-js-form-slider='true'>--%>
<%--                <input class='field' max='10000' min='500' type='range' value='3000'>--%>
<%--                <span class='fill'></span>--%>
<%--              </div>--%>
<%--              <div class='slider-label'>--%>
<%--                <span class='left -price'>500</span>--%>
<%--                <span class='right -price'>10000</span>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--          </div>--%>
<%--        </div>--%>
      </div>

      <div class="col-lg-9">
        <div class="container">
          <div class="row">
            <div class="col-md-8 ">
              <label class="left-grid">
                <select id="sort" name="select_item" class="custom-select select_item" onchange="location = this.value;">
                  <option value="${pageContext.request.contextPath}/?category=${category}&order=default" selected="selected"><s:message code="messages.sort_default"></s:message></option>
                  <option value="${pageContext.request.contextPath}/?category=${category}&order=aToZ"><s:message code="messages.sort_name"></s:message></option>
                  <option value="${pageContext.request.contextPath}/?category=${category}&order=lowToHigh"><s:message code="messages.sort_low"></s:message></option>
                  <option value="${pageContext.request.contextPath}/?category=${category}&order=highToLow"><s:message code="messages.sort_high"></s:message></option>
                </select>
              </label>
            </div>
            <div class="col-md-4">
              <div class="pt-1">
                <strong><s:message code="messages.display"></s:message></strong>
                <div class="btn-group">
                  <a href="#" id="list" class="btn btn-outline-dark btn-sm">
                    <span class="glyphicon glyphicon-th-list"></span><s:message code="messages.list"></s:message>
                  </a>
                  <a href="#" id="grid" class="btn btn-outline-dark btn-sm">
                    <span class="glyphicon glyphicon-th"></span><s:message code="messages.grid"></s:message>
                  </a>
                </div>
              </div>
            </div>
          </div>
        </div>
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
                             href="${pageContext.request.contextPath}/?category=${category}&order=${order}&page=${page}">${page}</a>
                        </c:if>
                        <c:if test="${page != productsList.currentPage}">
                          <a class="btn btn-outline-dark m-1"
                             href="${pageContext.request.contextPath}/?category=${category}&order=${order}&page=${page}">${page}</a>
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