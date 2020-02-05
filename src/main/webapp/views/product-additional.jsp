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
               action="${pageContext.request.contextPath}/product/save"
               modelAttribute="productForm"
               method="POST">
      <div class="col-lg-7">
        <form:input type="hidden" name="productId" path="productId"/>
        <div class="form-row">
          <label for="productName">Product Name</label>
          <form:input type="text" id="productName" name="productName" onkeyup="checkExistProduct()" path="productName"
                      class="form-control" placeholder="Product Name"/>
          <span id="ajax-response-product" style="display: none"></span>
          <form:errors path="productName" class="error-message"/>
        </div>
        <div class="form-row pt-3">
          <label for="productImage">Product Image</label>
          <form:input type="text" name="productImage" path="productImage" class="form-control"
                      placeholder="Link Image"/>
          <form:errors path="productImage" class="error-message"/>
        </div>
        <div class="form-row pt-3">
          <label for="productDescription">Description</label>
          <form:input type="text" name="productDescription" path="productDescription" class="form-control"
                      placeholder="Product Desciption"/>
        </div>
        <div class="form-row input-group mb-3 pt-4">
          <div class="input-group-prepend">
            <label class="input-group-text" for="categoryInput">Category</label>
          </div>
          <form:select class="custom-select" path="category.categoryId" id="categoryInput">
            <c:forEach items="${categories}" var="category">
              <option value="${category.categoryId}"> ${category.categoryName}</option>
            </c:forEach>
          </form:select>
        </div>
        <div class="form-row">
          <div class="col">
            <label for="productPrice">Price</label>
            <form:input type="text" class="form-control" path="productPrice" id="productPrice" placeholder="Price"/>
            <form:errors path="productPrice" class="error-message"/>
          </div>
          <div class="col">
            <label for="productQuantity">Quantity</label>
            <form:input type="text" class="form-control" path="productQuantity" id="productQuantity"
                        placeholder="Quantity"/>
            <form:errors path="productQuantity" class="error-message"/>
          </div>
        </div>
<%--        <div class="form-group pt-3 input-group mb-3">--%>
<%--            <div class="custom-file">--%>
<%--              <input type="file" class="custom-file-input" id="input-upload">--%>
<%--              <label class="custom-file-label" for="input-upload" aria-describedby="input-upload-add">Choose--%>
<%--                file</label>--%>
<%--            </div>--%>
<%--            <div class="input-group-append">--%>
<%--              <span class="input-group-text" id="input-upload-add">Upload</span>--%>
<%--            </div>--%>
<%--        </div>--%>
        <div class="form-group pt-1">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="gridCheck" required="required">
            <label class="form-check-label" for="gridCheck">
              Check me out
            </label>
          </div>
        </div>
        <p style="color: #ff2c66"> ${registerFail}</p>

        <input class="btn btn-success" type="submit" value="Add"/>
      </div>
    </form:form>
  </div>
</main>
<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>