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

<main>
  <div class="container">
    <div class="row p-4">
      <a class="btn btn-primary m-1" href="${pageContext.request.contextPath}/manager">Users Manage</a>
      <a class="btn btn-primary m-1" href="${pageContext.request.contextPath}/product/createNew"> Add Product</a>
    </div>
    <div class="products-table">
      <table class="table">
        <thead class="thead-dark">
        <tr>
          <th scope="col">#</th>
          <th scope="col">Product Name</th>
          <th scope="col">Price</th>
          <th scope="col">Quantity</th>
          <th scope="col">Control</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${productsList.list}" var="product" varStatus="loop">
          <tr>
            <th scope="row">${loop.index}</th>
            <td>${product.productName}</td>
            <td><fmt:formatNumber value=" ${product.productPrice}" type="currency" maxFractionDigits="0"/></td>
            <td>${product.productQuantity}</td>
            <td>

              <c:url var="updateLink" value="/product/update">
                <c:param name="productId" value="${product.productId}" />
              </c:url>
              <a href="${updateLink}">
                <button type="button" class="badge badge-warning">Edit</button>
              </a>
              <a class="badge badge-danger" data-toggle="modal" data-target="#myModal"
                 href="${pageContext.request.contextPath}/product/delete-${product.productId}">
                Remove
              </a>
              <div id="myModal"  class="modal fade" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">Confirm Remove Product</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      <h6> Remove <span class="text-danger">${product.productName}</span>?</h6>
                    </div>
                    <div class="modal-footer">
                      <form action="${pageContext.request.contextPath}/product/delete-${product.productId}">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                      </form>
                    </div>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
              </div><!-- /.modal -->
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>

    <c:if test="${productsList.totalPages > 1}">
    <div class="container">
      <div class="row d-flex align-items-center justify-content-center p-3">
        <ul class="pagination">
          <c:forEach items="${productsList.navigationPages}" var="page">
            <c:if test="${page != -1 }">
              <c:if test="${page == productsList.currentPage}">
                <a class="btn btn-dark m-1"
                   href="${pageContext.request.contextPath}/product/list?page=${page}">${page}</a>
              </c:if>
              <c:if test="${page != productsList.currentPage}">
                <a class="btn btn-outline-dark m-1"
                   href="${pageContext.request.contextPath}/product/list?page=${page}">${page}</a>
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
</main>
<div class="clearfix"></div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
