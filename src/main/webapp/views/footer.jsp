<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<div class="clearfix"></div>
<footer>
  <div class="newsletter">
    <div class="container">
      <div class="row">
        <div class="col-md-6 newsletter-left">
          <h3>Newsletter</h3>
          <p>Excepteur sint occaecat cupidatat non proident, sunt.</p>
        </div>
        <div class="col-md-6 newsletter-right">
          <form class="input-group" action="#" method="post">
            <input class="input-group-text bg-light" type="email" name="Email" placeholder="Email" required="">
            <div class="input-group-append">
              <button class="btn btn-danger" type="button" id="button-addon2">Email</button>
            </div>
          </form>
        </div>
      </div>
    </div>
    <div class="clearfix"></div>
  </div>
  <!-- //newsletter -->
  <!-- footer -->
  <div class="bg-light p-4">
    <div class="container">
      <div class="row">
        <div class="col-md-3 footer-grid">
          <h3><s:message code="messages.contact"></s:message></h3>
          <p>Duis aute irure dolor in reprehenderit in voluptate velit esse.</p>
          <ul class="address">
            <li class="row"><i class="material-icons">directions</i>1234k Avenue, 4th block, <span>New York City.</span>
            </li>
            <li class="row"><i class="material-icons">email</i> <a href="mailto:info@example.com"> info@example.com</a>
            </li>
            <li class="row"><i class="material-icons">smartphone</i> +1234 567 567</li>
          </ul>
        </div>
        <div class="col-md-3 footer-grid">
          <h3><s:message code="messages.information"></s:message></h3>
          <ul class="info">
            <li class="row"><i class="material-icons">help</i><a href="${pageContext.request.contextPath}/about">
              <s:message code="messages.about_us"></s:message>
            </a></li>
            <li class="row"><i class="material-icons">account_box</i><a
                href="${pageContext.request.contextPath}/contact"><s:message code="messages.contact_us"></s:message></a></li>
          </ul>
        </div>
        <div class="col-md-3 footer-grid">
          <h3><s:message code="messages.category_footer"></s:message></h3>
          <ul class="info">
            <li class="row"><i class="material-icons">laptop</i><a
                href="${pageContext.request.contextPath}/?category=1"><s:message code="messages.gaming"></s:message></a></li>
            <li class="row"><i class="material-icons">laptop_mac</i><a
                href="${pageContext.request.contextPath}/?category=2"><s:message code="messages.business"></s:message></a></li>
          </ul>
        </div>
        <div class="col-md-3 footer-grid">
          <h3><s:message code="messages.profile"></s:message></h3>
          <ul class="info">
            <li class="row"><i class="material-icons">home</i><a href="${pageContext.request.contextPath}/home"><s:message code="messages.home"></s:message></a>
            </li>
            <li class="row"><i class="material-icons">query_builder</i><a
                href="${pageContext.request.contextPath}/product/list">Deals</a></li>
          </ul>
          <div>
            <h4>Follow Us</h4>
          </div>
          <div class="social-button">
            <ul>
              <li><a href="#" class="facebook"><i
                  class="fa fa-facebook d-flex align-items-center justify-content-center"></i></a></li>
              <li><a href="#" class="twitter"><i
                  class="fa fa-twitter d-flex align-items-center justify-content-center"></i></a></li>
              <li><a href="#" class="google"><i
                  class="fa fa-google d-flex align-items-center justify-content-center"></i></a></li>
            </ul>
          </div>
        </div>
        <div class="clearfix"></div>
      </div>
    </div>
  </div>
  <div class="footer-copy">
    <div class="container">
      <div id="on-top" class="on-top">
        <div class="on-top-pos">
          <a href="#" class="scroll"><img src="${pageContext.request.contextPath}/lib/img/icon/arrow.png" alt=" "/></a>
        </div>
      </div>
    </div>
    <div class="container">
      <div class="container">
        <div class="row justify-content-center align-items-center">
          <a class="text p-1" href="${requestScope['javax.servlet.forward.request_uri']}?lang=vi">Vietnamese</a>
          <a class="text p-1" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en">English</a>
        </div>
      </div>
      <p>&copy; 2019 K Tech Store. All rights reserved | Design by khoavu882@gmail.com</p>
    </div>
  </div>
</footer>

<script src="${pageContext.request.contextPath}/lib/jquery/jquery.slim.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/lib/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/app.js"></script>
<script src="${pageContext.request.contextPath}/lib/js/minicart.js"></script>
<script src='//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
