<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setLocale value="en_US" scope="session"/>
<header class="bg-light w-100">
  <div class="container" id="navbar-top">
    <nav class="navbar navbar-expand-lg navbar-light">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/lib/img/icon/logo.png" width="150" height="40" alt="index"></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContentLG"
              aria-controls="navbarSupportedContentLG" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContentLG">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/"><s:message code="messages.home"></s:message><span
                class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Link</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/product/list"
               id="navbarDropdownLG" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <s:message code="messages.product"></s:message>
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownLG">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/?category=1"><s:message code="messages.gaming"></s:message></a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/?category=2"><s:message code="messages.business"></s:message></a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">Something else here</a>
            </div>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/about"><s:message code="messages.about_us"></s:message></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/contact"><s:message code="messages.contact_us"></s:message></a>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/search" method="post">
          <input class="form-control mr-sm-2" type="text" name="keywords" placeholder="<s:message code="messages.search"></s:message>" aria-label="Search">
          <button class="btn btn-outline-dark my-2 my-sm-0" type="submit"><s:message code="messages.search"></s:message></button>
        </form>
      </div>
    </nav>
  </div>
</header>
