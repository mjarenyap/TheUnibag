<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Get started with Unibag</title>
		<!-- FONT EXTERNAL LINKS -->
		<link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,700" rel="stylesheet" />
		<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,700" rel="stylesheet" />
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

		<!-- FRAMEWORK STYLESHEET | NOTE: DO NOT EDIT -->
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/theme.css" />
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/nav.css" />
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/footer.css" />
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/flex.css" />
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/button.css" />
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/field.css" />
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/modal.css" />

		<!-- PAGE STYLESHEET -->
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/signup.css" />

		<!-- JAVASCRIPT -->
		<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="js/layout.js" type="text/javascript"></script>
		<script src="js/formfunctions.js" type="text/javascript"></script>
	</head>
	<body class="nav-sticky">
		<c:set var="shoppingcart" value="${sessionScope.ShoppingCart}" />
		<c:set var="loggedUser" value="${sessionScope.Account}" />
		<div id="modal-overlay">
			<i class="fa fa-close fa-3x" id="close-modal"></i>
			<form method="get" action="search" id="searchform">
				<input type="text" name="keyword" placeholder="Search for bags..." class="full-width" />
			</form>
		</div>
		<!-- Sticky navigation -->
		<nav class="sticky">
			<ul class="mainnav flex-between">
				<li class="flex-start" id="search">
					<img src="assets/icons/magnifying-glass.svg" class="icon" />
					<span>Discover</span>
				</li>
				<li><img src="assets/images/unibag-logo.png" id="main-logo" /></li>
				<li class="flex-start">
					<c:if test="${loggedUser == null}">
						<div class="flex-start" id="login">
							<span>Login</span>
							<img src="assets/icons/avatar.svg" class="icon" />
						</div>
					</c:if>
					<c:if test="${loggedUser != null}">
						<div class="flex-start" id="logged-account">
							<span><c:out value="${loggedUser.firstName}"/> <c:out value="${loggedUser.lastName}"/></span>
							<img src="assets/icons/avatar.svg" class="icon" />
						</div>
					</c:if>
					<div class="flex-between">
						<span>(<c:out value="${fn:length(shoppingcart)}"/>)</span>
						<img src="assets/icons/shopping-cart.svg" class="icon" id="cart" />
					</div>
				</li>
			</ul>
			<ul class="subnav flex-center">
				<!-- Navigation items -->
			</ul>
		</nav>

		<section>
			<form action="account" method="post" id="signup-form">
				<c:if test="${error == true}">
					<div class="error-banner flex-between">
						<div>
							<p>Woah there! Did you fill out the important parts?</p>
							<br/>
							<p>
								<b>Did you follow the password policy?</b><br/>
								at least 8 characters long. <br/>
								at least one capital letter. <br/>
								at least one number. <br/>
								at least one symbol. <br/>
							</p>
							<br/>
							<p>Figured it out? Please try again.</p>
						</div>
						<i class="fa fa-close"></i>
					</div>	
				</c:if>
				<div class="heading">
					<h1 class="title">Get started with Unibag</h1>
					<span class="reminder">All fields with (*) are required</span>
				</div>
				<div class="flex-between forname">
					<label>
						<span>First name<b class="important">*</b></span>
						<input type="text" name="firstname" placeholder="William Luther" class="full-width" />
					</label>
					<label>
						<span>Last name<b class="important">*</b></span>
						<input type="text" name="lastname" placeholder="Shakespeare" class="full-width" />
					</label>
				</div>
				<label>
					<span>Email address<b class="important">*</b></span>
					<input type="email" name="email" placeholder="user@example.com" class="full-width" />
				</label>
				<label>
					<span>Password<b class="important">*</b></span>
					<input type="password" name="password" placeholder="Type here your password" class="full-width" />
				</label>
				<label>
					<span>Confirm password<b class="important">*</b></span>
					<input type="password" name="confirmpass" placeholder="Re-type here your password" class="full-width" />
				</label>
				<label>
					<span>Phone number</span>
					<input type="text" name="phone" placeholder="(Optional) Type here you phone number" class="full-width" />
				</label>
				<div class="flex-end">
					<span class="help">Already have an account? <a href="#" id="go-login">Login</a> here.</span>
					<input type="submit" value="Sign Up" />
				</div>
				<input type="hidden" id="pRedirect" name="redirect" value="${purpose}" />
				<input type="hidden" name="processAccount" value="signup" />
			</form>
		</section>

		<footer>
			<div class="content-wrapper">
				<h3>Our Purpose</h3>
				<p>
					Materials from the Harry Potter series of films and from the film Fantastic Beasts And Where To Find Them are courtesy of Warner Bros. Entertainment.
				</p>
			</div>
			<div class="content-wrapper">
				<h3>Contact us</h3>
				<p>
					User Experience Society - DLSU
					<br/><br/>
					Gokongwei Building, De La Salle University, Metro Manila, NCR, Philippines
				</p>
			</div>
			<div class="content-wrapper">
				<h3>Follow us</h3>
				<i class="fa fa-facebook fa-2x"></i>
				<i class="fa fa-twitter fa-2x"></i>
				<i class="fa fa-instagram fa-2x"></i>
			</div>
		</footer>
	</body>
</html>