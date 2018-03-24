<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<body>
		<!-- Sticky navigation -->
		<nav class="sticky">
			<ul class="mainnav flex-between">
				<li class="flex-start" id="search">
					<img src="assets/icons/magnifying-glass.svg" class="icon" />
					<span>Discover</span>
				</li>
				<li><img src="assets/images/Pottermore.png" id="main-logo" /></li>
				<li class="flex-start">
					<div class="flex-start" id="login">
						<span>Login</span>
						<img src="assets/icons/avatar.svg" class="icon" />
					</div>
					<img src="assets/icons/shopping-cart.svg" class="icon" id="cart" />
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
			<!-- CONTENT HERE -->
			<div class="product-feed flex-between">
				<div class="content-wrapper">
					<div class="featured-image"></div>
					<h3 class="product-name">Voyager Indigo Stripe Print</h3>
					<h3 class="product-price">$110.00</h3>
					<button class="hallow view-product">View product details</button>
				</div>
				<div class="content-wrapper">
					<div class="featured-image"></div>
					<h3 class="product-name">Functional Backpack Gravel C</h3>
					<h3 class="product-price">$140.00</h3>
					<button class="hallow view-product">View product details</button>
				</div>
				<div class="content-wrapper">
					<div class="featured-image"></div>
					<h3 class="product-name">CR Small Weekender True Black C</h3>
					<h3 class="product-price">$125.00</h3>
					<button class="hallow view-product">View product details</button>
				</div>
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