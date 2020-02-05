<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body class="h-100">
<h1>Spring MVC file upload example</h1>

<form method="POST" action="${pageContext.request.contextPath}/" enctype="multipart/form-data">
  <input type="file" name="file" /><br/>
  <input type="submit" value="Submit" />
</form>

</body>
</html>