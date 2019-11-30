<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html>
<head>
<!--<link href="bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="jquery-3.3.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/js/bootstrap.min.js" integrity="sha384-3qaqj0lc6sV/qpzrc1N5DC6i1VRn/HyX4qdPaiEFbn54VjQBEU341pvjz7Dv3n6P" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">-->
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
     <div id="bg"></div>
	<div class="login-reg-panel">
		<div class="login-info-box">
			<h2>Have an account?</h2>
			<label id="label-register" for="log-reg-show">Login</label> <input
				type="radio" name="active-log-panel" id="log-reg-show"
				checked="checked">
		</div>

		<div class="register-info-box">
			<h2>Don't have an account?</h2>
			<p>Enter your details and find your Special Someone</p>
			<label id="label-login" for="log-login-show">Signup</label> <input
				type="radio" name="active-log-panel" id="log-login-show">
		</div>

		<div class="white-panel">
			<div class="login-show">
				<div class="login-form">
					<span>${message}</span>
					<form action="${contextPath}/login" method="POST" id="loginForm">
						<h2 class="text-center" style="color: maroon">Sign in</h2>
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-envelope "></i></span> <input type="text"
									class="form-control" name="username" id="username" placeholder="Username *"
									required="required">
							</div>
						</div>
						<div class="form-group">
							<div class="input-group" id="show_hide_password">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg"></i></span> <input type="password"
									class="form-control" name="password" id="password" placeholder="Password *"
									required="required">
								<div class="input-group-addon">
									<a href="" style="border-left-style: none; color: maroon"><i
										class="fa fa-eye-slash" id="eyeIcon" aria-hidden="true"></i></a>
								</div>
							</div>
						</div>
						<span>${error}</span>
						<div class="form-group">
							<button type="submit"  class="btn login-btn btn-block"
								style="color: white; background-color: maroon" >Login</button>
						</div>
						<div class="clearfix">

							<label class="pull-left checkbox-inline"><input
								type="checkbox" name="remember-me"> Remember me</label> <a href="${contextPath}/forgotPassword"
								class="pull-right">Forgot Password?</a>
						</div>
						<div class="or-seperator">
							<i>or</i>
						</div>
						<p class="text-center">Login with your social media account</p>
						<div class="text-center social-btn">
							<a href="#" class="btn btn-primary"><i class="fa fa-facebook"></i>&nbsp;
								Facebook</a> <a href="#" class="btn btn-info"><i
								class="fa fa-twitter"></i>&nbsp; Twitter</a> <a href="#"
								class="btn btn-danger"><i class="fa fa-google"></i>&nbsp;
								Google</a>
						</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
					</form>
					<p class="text-center text-muted small">
						Don't have an account? <a href="#">Sign up here!</a>
					</p>
				</div>



			</div>
			<div class="register-show">
				<div class="signup-form">
				
				    <form:form action="${contextPath}/registration" method="POST" modelAttribute="userForm">
					<h2 class="text-center" style="color: maroon">Register</h2>
					<spring:bind path="username">
					<div class="form-group">
					
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-paper-plane fa-lg"></i></span> 
								<form:input type="text" path="username"
								class="form-control" name="username" placeholder="Email"
								required="required"></form:input>
								
						</div>
						<form:errors path="username"></form:errors>
					</div>
					</spring:bind>
					<div class="form-group">
					<spring:bind path="firstname">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-user fa-lg"></i></span> 
						    
								<form:input type="text"
								class="form-control" name="firstname" path="firstname" placeholder="Firstname"
								required="required"></form:input>
								<form:errors path="firstname"></form:errors>
								
						</div>
						</spring:bind>
					</div>
					<div class="form-group">
					<spring:bind path="lastname">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-user fa-lg"></i></span> 
								<form:input type="text"
								class="form-control" name="lastname" path="lastname" placeholder="Lastname"
								required="required"></form:input>
								<form:errors path="lastname"></form:errors>
						</div>
							</spring:bind>
					</div>
					<div class="form-group">
				<spring:bind path="mobileno">
					
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-mobile fa-lg"></i></span>
								<form:input type="text"
								class="form-control" path="mobileno" name="mobileno" placeholder="Mobile No"
								required="required"></form:input>
								<form:errors path="mobileno"></form:errors>
								
						</div>
						</spring:bind>
					</div>
					<div class="form-group">
					<spring:bind path="password">
						<div class="input-group">
							<span class="input-group-addon"><i
								class="fa fa-lock fa-lg"></i></span> 
								
								<form:input type="password"
								class="form-control" path="password" name="password" placeholder="Password"
								required="required"></form:input>
								<form:errors path="password"></form:errors>
								
						</div>
						</spring:bind>
					</div>
					<div class="form-group">
					<spring:bind path="passwordConfirm">
						<div class="input-group">
							<span class="input-group-addon"> <i
								class="fa fa-lock fa-lg"></i> <i class="fa fa-check"></i>
							</span> 
							
							<form:input type="password" class="form-control"
								name="confirmpassword" placeholder="Confirm Password" path="passwordConfirm"
								required="required"></form:input>
								<form:errors path="passwordConfirm"></form:errors>
						    
						</div>
						</spring:bind>
					</div>
					<div class="form-group">
						<label class="checkb.ox-inline">
					
						<input type="checkbox" name="termsofuse"
							required="required" >
						 I accept the <a data-toggle="modal"
							href="#termsAndCondition">Terms of Use</a> &amp; <a
							data-toggle="modal" href="#privatePolicy">Privacy Policy</a></label>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-lg"
							style="color: white; background-color: maroon">Sign Up</button>
					</div>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>	
					</form:form>
					<div class="text-center">
						Already have an account? <a href="#">Login here</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="privatePolicy" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" role="document"
			id="privatePolicyDialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="privatePolicyTitle" align="center">Private
						Policy</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					asdfadsfdfdsfsdfsdfsdafdsafsdafdsafdsfdsfdsfsdafsdafds</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="termsAndCondition" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-dialog-center" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="privatePolicyTitle" align="center">Terms
						& Condition</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					asdfadsfdfdsfsdfsdfsdafdsafsdafdsafdsfdsfdsfsdafsdafds</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script>
	$(document).ready(
			function() {
				$('.login-info-box').fadeOut();
				$('.login-show').addClass('show-log-panel');

				$("#show_hide_password a")
						.on(
								'click',
								function(event) {
									event.preventDefault();
									if ($('#show_hide_password input').attr(
											"type") == "text") {
										$('#show_hide_password input').attr(
												'type', 'password');
										$('#eyeIcon').addClass("fa-eye-slash");
										$('#eyeIcon').removeClass("fa-eye");
									} else if ($('#show_hide_password input')
											.attr("type") == "password") {
										$('#show_hide_password input').attr(
												'type', 'text');
										$('#eyeIcon').removeClass(
												"fa-eye-slash");
										$('#eyeIcon').addClass("fa-eye");
									}
								});
			});

	$('.login-reg-panel input[type="radio"]').on('change', function() {
		if ($('#log-login-show').is(':checked')) {
			$('.register-info-box').fadeOut();
			$('.login-info-box').fadeIn();

			$('.white-panel').addClass('right-log');
			$('.register-show').addClass('show-log-panel');
			$('.login-show').removeClass('show-log-panel');

		} else if ($('#log-reg-show').is(':checked')) {
			$('.register-info-box').fadeIn();
			$('.login-info-box').fadeOut();

			$('.white-panel').removeClass('right-log');

			$('.login-show').addClass('show-log-panel');
			$('.register-show').removeClass('show-log-panel');
		}
	});

	function testAnim(x) {
		$('.modal .modal-dialog').attr('class',
				'modal-dialog  ' + x + '  animated');
	};
	$('#privatePolicy').on('show.bs.modal', function(e) {
		// var anim = $('#entrance').val();
		testAnim("rollIn");
	})
	$('#privatePolicy').on('hide.bs.modal', function(e) {
		//var anim = $('#exit').val();
		testAnim("rollOut");
	})
	
</script>
</html>
