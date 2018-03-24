<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Browse Products - Unibag</title>
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
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/select.css" />
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/checkbox.css" />

		<!-- PAGE STYLESHEET -->
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/product-list.css" />
	</head>
	<body>
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
		<section>
			<div id="main-controls">
				<h1 id="main-heading">Browse All Products</h1>
				<div class="flex-start">
					<select>
						<option>Names A-Z</option>
						<option>Names Z-A</option>
						<option>Price</option>
						<option>Type of bag</option>
						<option>Brand</option>
					</select>
					<div id="pagination">
						<ul>
							<li class="active">1</li>
							<li>2</li>
							<li>3</li>
							<li>4</li>
							<li>5</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="flex-between" id="product-list">
				<div id="filters">
					<h3 id="filter-heading">Filter products by:</h3>
					<hr/>

					<h4 class="criteria">Price ranges</h4>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						less than $40.00
					</label>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						$40.00 - $99.00
					</label>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						$100.00 - $149.00
					</label>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						$150.00 - $200.00
					</label>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						more than $200.00
					</label>


					<h4 class="criteria">Collections</h4>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						Classic Collection
					</label>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						Essential Collection
					</label>
					<label>
						<input type="checkbox" checked="checked" />
						<span></span>
						Travel System Collection
					</label>
				</div>
				<div id="product-feed">
					<div class="content-wrapper">
						<div class="featured-image"></div>
						<div class="product-info">
							<h1 class="product-name">Voyager Indigo Stripe Print</h1>
							<h3 class="product-price">$110.00</h3>
							<h4 class="product-brand">Brand: <span>LeSportSac</span></h4>
							<h4 class="product-type">Type of bag: <span>Tote bag</span></h4>
							<h4 class="product-rating">Product rating: <span>5 out of 5</span></h4>
							<button class="hallow view-product">View product details</button>
						</div>
					</div>
					<hr/>
					<div class="content-wrapper">
						<div class="featured-image"></div>
						<div class="product-info">
							<h1 class="product-name">Functional Backpack Gravel C</h1>
							<h3 class="product-price">$140.00</h3>
							<h4 class="product-brand">Brand: <span>LeSportSac</span></h4>
							<h4 class="product-type">Type of bag: <span>Tote bag</span></h4>
							<h4 class="product-rating">Product rating: <span>5 out of 5</span></h4>
							<button class="hallow view-product">View product details</button>
						</div>
					</div>
					<hr/>
					<div class="content-wrapper">
						<div class="featured-image"></div>
						<div class="product-info">
							<h1 class="product-name">CR Small Weekender True Black C</h1>
							<h3 class="product-price">$125.00</h3>
							<h4 class="product-brand">Brand: <span>LeSportSac</span></h4>
							<h4 class="product-type">Type of bag: <span>Tote bag</span></h4>
							<h4 class="product-rating">Product rating: <span>5 out of 5</span></h4>
							<button class="hallow view-product">View product details</button>
						</div>
					</div>
					<hr/>
					<div class="content-wrapper">
						<div class="featured-image"></div>
						<div class="product-info">
							<h1 class="product-name">NoHo Backpack Black</h1>
							<h3 class="product-price">$140.00</h3>
							<h4 class="product-brand">Brand: <span>LeSportSac</span></h4>
							<h4 class="product-type">Type of bag: <span>Tote bag</span></h4>
							<h4 class="product-rating">Product rating: <span>5 out of 5</span></h4>
							<button class="hallow view-product">View product details</button>
						</div>
					</div>
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