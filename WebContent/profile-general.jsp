<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>My Profile</title>
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
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/profile.css" />

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

		<section id="account">
			<h1 id="profile-title">Your Account Settings</h1>
			<hr/>
			<div class="flex-start">
				<div id="context-wrapper">
					<button class="active profile-nav" data-id="profile-general">General Information</button>
					<hr/>
					<button class="profile-nav" data-id="profile-password">Change Password</button>
					<hr/>
					<button class="profile-nav" data-id="profile-address">Address Information</button>
					<br/>
					<br/>
					<br/>
					<hr/>
					<button id="logout">Logout</button>
				</div>
				<form action="profile" method="post" id="profile-form">
					<c:if test="${error == true}">
						<div class="error-banner flex-between">
							<p>Oh no! There was an error saving your changes.</p>
							<i class="fa fa-close"></i>
						</div>
						<br/>
					</c:if>
					<h1 id="context-title">General Information</h1>
					<label>
						<span>First name</span>
						<input type="text" name="firstname" value="${loggedUser.firstName}" placeholder="Edit your first name" class="full-width" />
					</label>
					<label>
						<span>Last name</span>
						<input type="text" name="lastname" value="${loggedUser.lastName}" placeholder="Edit your last name" class="full-width" />
					</label>
					<label>
						<span>Email address</span>
						<input type="email" name="email" value="${loggedUser.email}" placeholder="Edit your email address" class="full-width" />
					</label>
					<label>
						<span>Phone number</span>
						<c:choose>
							<c:when test="${loggedUser.phone != null}">
								<input type="text" name="phone" value="${loggedUser.phone}" placeholder="Edit your phone number" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="number" name="phone" value="" placeholder="Edit your phone number" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>A secret random answer</span>
						<input type="text" name="securityAnswer" value="${loggedUser.answer}" placeholder="Type here your security answer (Case sensitive)" class="full-width" />
					</label>
					<br/><br/>
					<label>
						<span>Confirm Password to Continue</span>
						<input type="password" name="securityPassword" placeholder="Type your password to continue" class="full-width" />
					</label>
					<input type="hidden" name="purpose" value="edit-pg" />
					<div id="confirm-buttons" class="flex-end">
						<button class="hallow" data-id="pg" id="profile-discard-changes">Cancel</button>
						<input type="submit" value="Save Changes" id="save-changes" />
					</div>
				</form>
			</div>
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