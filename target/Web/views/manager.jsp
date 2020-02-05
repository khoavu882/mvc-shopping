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
      <a class="btn btn-primary m-1" href="${pageContext.request.contextPath}/product/list">Products Manage</a>
      <a class="btn btn-primary m-1" href="${pageContext.request.contextPath}/register">Add User</a>
    </div>
    <div class="user-table">
      <table class="table">
        <thead class="thead-dark">
        <tr>
          <th scope="col">#</th>
          <th scope="col">User Name</th>
          <th scope="col">Full Name</th>
          <th scope="col">Role</th>
          <th scope="col">Control</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${usersList.list}" var="user" varStatus="loop">
          <tr>
            <th scope="row">${loop.index}</th>
            <td>${user.userName}</td>
            <td>${user.firstName} ${user.lastName}</td>
            <td>
              <c:forEach items="${user.roles}" var="role" varStatus="loop2">
                <a class="badge badge-primary" href="#">${role.roleName}</a>
              </c:forEach>
            </td>
            <td><a class="badge badge-warning" href="${pageContext.request.contextPath}">Edit</a></td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <c:if test="${usersList.totalPages > 1}">
      <div class="container">
        <div class="row d-flex align-items-center justify-content-center p-3">
          <ul class="pagination">
            <c:forEach items="${usersList.navigationPages}" var="page">
              <c:if test="${page != -1 }">
                <c:if test="${page == usersList.currentPage}">
                  <a class="btn btn-dark m-1"
                     href="${pageContext.request.contextPath}/manager?page=${page}">${page}</a>
                </c:if>
                <c:if test="${page != usersList.currentPage}">
                  <a class="btn btn-outline-dark m-1"
                     href="${pageContext.request.contextPath}/manager?page=${page}">${page}</a>
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

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
