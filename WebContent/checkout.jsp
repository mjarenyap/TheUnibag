<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Checkout in Unibag</title>
		
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
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/checkout.css" />

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
							<input type="hidden" id="pRedirect" value="checkout" />
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

		<section id="checkout-section">
			<h1 id="checkout-title">Checkout</h1>
			<div class="flex-start">
				<form action="success" method="post" id="billing-form">
					<c:if test="${error == true}">
						<div class="error-banner flex-between">
							<p>Woah there! You have to fill out the necessary fields.</p>
							<i class="fa fa-close"></i>
						</div>
					</c:if>	
					<h1 id="billing-title"><b class="important">Step 1:</b> Billing Information</h1>
					<span class="reminder">All fields with (*) are required</span>
					<div class="flex-between forname">
						<label>
							<span>First name<b class="important">*</b></span>
							<c:choose>
								<c:when test="${loggedUser != null}">
									<input type="text" name="firstname" value="${loggedUser.firstName}" placeholder="Type here your first name" class="full-width" />
								</c:when>
								<c:otherwise>
									<input type="text" name="firstname" value="" placeholder="Type here your first name" class="full-width" />
								</c:otherwise>
							</c:choose>
						</label>
						<label>
							<span>Last name<b class="important">*</b></span>
							<c:choose>
								<c:when test="${loggedUser != null}">
									<input type="text" name="lastname" value="${loggedUser.lastName}" placeholder="Type here your last name" class="full-width" />
								</c:when>
								<c:otherwise>
									<input type="text" name="lastname" value="" placeholder="Type here your last name" class="full-width" />
								</c:otherwise>
							</c:choose>
						</label>
					</div>
					<label>
						<span>Email address<b class="important">*</b></span>
						<c:choose>
							<c:when test="${loggedUser != null}">
								<input type="email" name="email" value="${loggedUser.email}" placeholder="Type here your email address" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="email" name="email" value="" placeholder="Type here your email address" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>Phone number</span>
						<c:choose>
							<c:when test="${loggedUser != null && loggedUser.phone != null}">
								<input type="text" name="phone" value="${loggedUser.phone}" placeholder="(Optional) Type here you phone number" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="text" name="phone" value="" placeholder="(Optional) Type here you phone number" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>Location<b class="important">*</b></span>
						<c:choose>
							<c:when test="${loggedUser != null}">
								<input type="text" name="location" value="${address.location}" placeholder="Type here your location" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="text" name="location" value="" placeholder="Type here your location" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>City<b class="important">*</b></span>
						<c:choose>
							<c:when test="${loggedUser != null}">
								<input type="text" name="city" value="${address.city}" placeholder="Type here your city" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="text" name="city" value="" placeholder="Type here your city" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>Postcode<b class="important">*</b></span>
						<c:choose>
							<c:when test="${loggedUser != null}">
								<input type="number" name="postcode" value="${address.postcode}" placeholder="Type here your postcode" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="number" name="postcode" value="" placeholder="Type here your postcode" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<label>
						<span>Province<b class="important">*</b></span>
						<c:choose>
							<c:when test="${loggedUser != null}">
								<input type="text" name="province" value="${address.province}" placeholder="Type here your province" class="full-width" />
							</c:when>
							<c:otherwise>
								<input type="text" name="province" placeholder="Type here your province" value="" class="full-width" />
							</c:otherwise>
						</c:choose>
					</label>
					<input type="hidden" name="purpose" value="checkout" />
				</form>
				<div id="confirm-pane">
					<h1 id="confirm-title"><b class="important">Step 2:</b> Confirm Your Order</h1>
					<div id="product-feed">
						<c:forEach items="${shoppingcart}" var="bag">
							<div class="content-wrapper">
								<div class="featured-image"></div>
								<h4 class="product-name"><c:out value="${bag.name}" /></h4>
								<h4 class="product-price">$<c:out value="${bag.price}" /></h4>
							</div>
						</c:forEach>
					</div>
					<button id="confirm-button">Confirm Order</button>
					<p id="go-back">Go back to my shopping cart</p>
				</div>
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