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

		<!-- PAGE STYLESHEET -->
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/profile.css" />

		<!-- JAVASCRIPT -->
		<script src="js/formfunctions.js" type="text/javascript"></script>
		<script src="js/layout.js" type="text/javascript"></script>
	</head>
	<body class="nav-sticky">
		<c:set var="shoppingcart" value="${sessionScope.ShoppingCart}" />
		<c:set var="loggedUser" value="${sessionScope.Account}" />
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
							<span><c:out value="${loggedUser.firstname}"/> <c:out value="${loggedUser.lastname}"/></span>
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
				<li data-id="all">All</li>
				<li data-id="backpack">Backpack</li>
				<li data-id="handbag">Handbag</li>
				<li data-id="totebag">Tote bag</li>
				<li data-id="messengerbag">Messenger bag</li>
				<li data-id="travelbag">Travel bag</li>
				<li data-id="slingbag">Sling bag</li>
				<li data-id="weekenderbag">Weekender bag</li>
			</ul>
		</nav>

		<section id="account">
			<h1 id="profile-title">Your Account Settings</h1>
			<hr/>
			<div class="flex-start">
				<div id="context-wrapper">
					<button>General Information</button>
					<hr/>
					<button>Change Password</button>
					<hr/>
					<button class="active">Address Information</button>
				</div>
				<form action="profile" method="post" id="profile-form">
					<h1 id="context-title">Address Information</h1>
					<label>
						<span>Location</span>
						<c:choose>
							<c:when test="${address != null}">
								<input type="text" name="location" value="${address.location}" placeholder="Edit your location" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="text" name="location" value="" placeholder="Edit your location" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>City</span>
						<c:choose>
							<c:when test="${address != null}">
								<input type="text" name="city" value="${address.city}" placeholder="Edit your city" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="text" name="city" value="" placeholder="Edit your city" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>Postcode</span>
						<c:choose>
							<c:when test="${address != null}">
								<input type="number" name="postcode" value="${address.postcode}" placeholder="Edit your postcode" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="number" name="postcode" value="" placeholder="Edit your postcode" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>Province</span>
						<c:choose>
							<c:when test="${address != null}">
								<input type="text" name="province" value="${address.province}" placeholder="Edit your province" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="text" name="province" value="" placeholder="Edit your province" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<div id="confirm-buttons" class="flex-end">
						<button class="hallow" data-id="pa" id="profile-discard-changes">Cancel</button>
						<input type="submit" value="Save Changes" id="save-changes" />
					</div>
					<input type="hidden" name="purpose" value="edit-pa" />
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