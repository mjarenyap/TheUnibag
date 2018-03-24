<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

		<!-- PAGE STYLESHEET -->
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/checkout.css" />
	</head>
	<body>
		<nav>
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
				<li>All</li>
				<li>Backpack</li>
				<li>Handbag</li>
				<li>Tote bag</li>
				<li>Messenger bag</li>
				<li>Travel bag</li>
				<li>Sling bag</li>
				<li>Weekender bag</li>
			</ul>
		</nav>

		<section id="checkout-section">
			<h1 id="checkout-title">Checkout</h1>
			<div class="flex-start">
				<form action="success" method="post" id="billing-form">
					<h1 id="billing-title"><b class="important">Step 1:</b> Billing Information</h1>
					<span class="reminder">All fields with (*) are required</span>
					<div class="flex-between forname">
						<label>
							<span>First name<b class="important">*</b></span>
							<input type="text" name="firstname" value="William Luther" class="full-width" />
						</label>
						<label>
							<span>Last name<b class="important">*</b></span>
							<input type="text" name="lastname" value="Shakespeare" class="full-width" />
						</label>
					</div>
					<label>
						<span>Email address<b class="important">*</b></span>
						<input type="email" name="email" value="user@example.com" class="full-width" />
					</label>
					<label>
						<span>Phone number</span>
						<input type="number" name="phone" placeholder="(Optional) Type here you phone number" class="full-width" />
					</label>
					<label>
						<span>Location<b class="important">*</b></span>
						<input type="text" name="location" value="Unit 2 cor Paseo de Sta Rosa" class="full-width" />
					</label>
					<label>
						<span>City<b class="important">*</b></span>
						<input type="text" name="city" value="Sta Rosa" class="full-width" />
					</label>
					<label>
						<span>Postcode<b class="important">*</b></span>
						<input type="number" name="postcode" value="4026" class="full-width" />
					</label>
					<label>
						<span>Province<b class="important">*</b></span>
						<input type="text" name="province" value="Laguna" class="full-width" />
					</label>
				</form>
				<div id="confirm-pane">
					<h1 id="confirm-title"><b class="important">Step 2:</b> Confirm Your Order</h1>
					<div id="product-feed">
						<div class="content-wrapper">
							<div class="featured-image"></div>
							<h4 class="product-name">Voyager Indigo Stripe Print</h4>
							<h4 class="product-price">$110.00</h4>
						</div>
						<div class="content-wrapper">
							<div class="featured-image"></div>
							<h4 class="product-name">Functional Backpack Gravel C</h4>
							<h4 class="product-price">$140.00</h4>
						</div>
						<div class="content-wrapper">
							<div class="featured-image"></div>
							<h4 class="product-name">CR Small Weekender True Black C</h4>
							<h4 class="product-price">$125.00</h4>
						</div>
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