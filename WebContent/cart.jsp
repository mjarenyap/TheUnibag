<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

		<!-- PAGE STYLESHEET -->
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/cart.css" />
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
					<div class="flex-between">
						<span>(0)</span>
						<img src="assets/icons/shopping-cart.svg" class="icon" id="cart" />
					</div>
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

		<section id="cart-section">
			<h1 id="cart-heading">Your Shopping Cart</h1>
			<div class="flex-start">
				<table id="table-of-cart">
					<thead>
						<th>Item</th>
						<th>Product Name</th>
						<th>Price</th>
						<th></th>
					</thead>
					<tbody>
						<tr>
							<td><div class="featured-image"></div></td>
							<td>Voyager Indigo Stripe Print</td>
							<td>$110.00</td>
							<td><img src="assets/icons/garbage.svg" class="remove" /></td>
						</tr>
						<tr>
							<td><div class="featured-image"></div></td>
							<td>Functional Backpack Gravel C</td>
							<td>$140.00</td>
							<td><img src="assets/icons/garbage.svg" class="remove" /></td>
						</tr>
						<tr>
							<td><div class="featured-image"></div></td>
							<td>CR Small Weekender True Black C</td>
							<td>$125.00</td>
							<td><img src="assets/icons/garbage.svg" class="remove" /></td>
						</tr>
					</tbody>
				</table>
				<div id="summary-pane">
					<h3 id="summary-title">Your Order Summary</h3>
					<div id="summary-content">
						<p id="count-summary"><b>No. of products</b>: 3</p>
						<p id="total-summary"><b>Grand total</b>: $1000.75</p>
					</div>
					<button id="checkout">Proceed to checkout</button>
				</div>
			</div>
			<button class="hallow">Continue Shopping</button>
			<button class="hallow">Clear my cart</button>
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