<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/static/css/style.css">
<!------ Include the above in your HEAD tag ---------->
</head>

<body>
<%
System.out.println("Hii");
%>
			
		<form action="${contextPath}/forgotPassword1" method="POST">
		 <span>${successMessage}</span>
		<div class="white-panel-pass">
			<div class="login-show">
			<div class="text-center">
			<h3><i class="fa fa-lock fa-4x"></i></h3>
				<h2 align="center" style="color:maroon">Forgot Password?</h2>
				
				<p>Please enter your email id to reset your password.</p>
				<p>Verification mail will be send to your entered email </p>
			<div class="form-group">	
			<div class="input-group">
                          <span class="input-group-addon"><i class="fa fa-envelope icon color-maroon"></i></span>
                          <input id="email" name="email" placeholder="email address" class="form-control" type="email">
                        </div>
				
			</div>
			</div>
				<div class="form-group">
							<button type="submit"  class="btn login-btn btn-block"
								style="color: white; background-color: maroon" >Reset</button>
						</div>
			 <span>${errorMessage}</span>
		</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
        </form>
</body>
<script>

    $(document).ready(function(){
    $('.login-info-box').fadeOut();
    $('.login-show').addClass('show-log-panel');
});



</script>
</html>
    