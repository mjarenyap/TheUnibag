<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Welcome to Unibag</title>
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
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/homepage.css" />
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
					<c:if test="${loggedUser} == null">
						<div class="flex-start" id="login">
							<span>Login</span>
							<img src="assets/icons/avatar.svg" class="icon" />
						</div>
					</c:if>
					<c:if test="${loggedUser} != null">
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

		<!-- Hero section -->
		<header>
			<h1 id="welcome">Welcome to The Unibag</h1>
			<h3 id="tagline">The shopping experience at it's best.</h3>
			<img src="assets/icons/spirals-of-vines.svg" class="ornaments">
			<button class="hallow-white" id="cta">Get started</button>
		</header>

		<!-- Services section -->
		<section id="services" class="flex-between">
			<div class="content-wrapper">
				<img src="assets/icons/worldwide.svg" />
				<p>
					Get a real-time connection to your browser. Make changes to CSS and HTML and you'll instantly see those changes on screen.
				</p>
			</div>
			<div class="content-wrapper">
				<img src="assets/icons/route.svg" />
				<p>
					Work with preprocessors in a whole new way. We know how important preprocessors are to your workflow. 
				</p>
			</div>
			<div class="content-wrapper">
				<img src="assets/icons/help.svg" />
				<p>
					Instead of jumping between file tabs, Brackets lets you open a window into the code you care about most.
				</p>
			</div>
		</section>

		<section class="white" id="brands">
			<h1>Popular Bag Brands</h1>
			<img src="assets/icons/spirals-of-vines-dark.svg" class="ornaments">
			<!-- CONTENT HERE -->
		</section>

		<!-- Featured bags section -->
		<section class="white" id="collections">
			<h1 class="title-heading">Featured Bags</h1>
			<img src="assets/icons/spirals-of-vines-dark.svg" class="ornaments">
			<!-- Product feed -->
			<div class="product-feed flex-between">
				<c:forEach items="${baglist}" var="bag">
					<div class="content-wrapper">
						<div class="featured-image"></div>
						<h3 class="product-name"><c:out value="${bag.name}" /></h3>
						<h3 class="product-price">$<c:out value="${bag.price}" /></h3>
						<button class="hallow view-product">View product details</button>
					</div>
				</c:forEach>
			</div>
			<button class="hallow see-more">See more</button>
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