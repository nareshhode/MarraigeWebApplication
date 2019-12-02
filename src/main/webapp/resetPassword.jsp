<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reset Password</title>
</head>
<body>
<div class="container" id="container">
<form action="${contextPath}/reset" method="POST">
    <input type="text" name="password" placeholder="New Password">
    <input type="text" name="password1" placeholder="Confirm New Password">
    <span>${successMessage}</span>
    <span>${errorMessage}</span>
    <br>
	<button>Submit</button>
</form>
</div>	
</body>

</html>