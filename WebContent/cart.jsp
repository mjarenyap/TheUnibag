<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Shopping cart</title>
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
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/cart.css" />

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
							<input type="hidden" id="pRedirect" value="cart" />
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

		<section id="cart-section">
			<h1 id="cart-heading">Your Shopping Cart</h1>
			<c:choose>
				<c:when test="${fn:length(shoppingcart) == 0}">
					<div class="error-banner">
						<p class="large">It's empty! Try putting items into your cart.</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="flex-start">
						<table id="table-of-cart">
							<thead>
								<th>Item</th>
								<th>Product Name</th>
								<th>Price</th>
								<th></th>
							</thead>
							<tbody>
								<c:forEach items="${shoppingcart}" var="bag" varStatus="status">
									<tr>
										<td><div class="featured-image"></div></td>
										<td><c:out value="${bag.name}" /></td>
										<td><c:out value="${bag.price}" /></td>
										<td><img src="assets/icons/garbage.svg" class="remove" data-id="${productPaths[status.index]}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="summary-pane">
							<h3 id="summary-title">Your Order Summary</h3>
							<div id="summary-content">
								<p id="count-summary"><b>No. of products</b>: <c:out value="${fn:length(shoppingcart)}" /></p>
								<p id="total-summary"><b>Grand total</b>: $<c:out value="${subtotal}" /></p>
							</div>
							<button id="checkout">Proceed to checkout</button>
						</div>
					</div>
					<button class="hallow" id="continue-shopping">Continue Shopping</button>
					<button class="hallow" id="clear-cart">Clear my cart</button>
				</c:otherwise>
			</c:choose>
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