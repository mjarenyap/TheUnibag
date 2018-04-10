<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/modal.css" />

		<!-- PAGE STYLESHEET -->
		<link rel="stylesheet" type="text/css" href="css/page-stylesheet/product-list.css" />

		<!-- JAVASCRIPT -->
		<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
		<script src="js/layout.js" type="text/javascript"></script>
		<script src="js/formfunctions.js" type="text/javascript"></script>
	</head>
	<body class="nav-sticky">
		<c:set var="shoppingcart" value="${sessionScope.ShoppingCart}" />
		<c:set var="loggedUser" value="${sessionScope.Account}" />
		<input type="hidden" id="typeFilter" name="typeFilter" value="${typeFilter}" />
		<!-- Sticky navigation -->
		<div id="modal-overlay">
			<i class="fa fa-close fa-3x" id="close-modal"></i>
			<form method="get" action="search" id="searchform">
				<input type="text" name="keyword" placeholder="Search for bags..." class="full-width" />
			</form>
		</div>
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
							<input type="hidden" id="pRedirect" value="products" />
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
			<div id="main-controls">
				<h1 id="main-heading">Browse <c:out value="${bagType}" /> Products</h1>
				<div class="flex-start">
					<select id="sortProducts">
						<option value="${sortingMode}"><c:out value="${sortName}" /></option>
						<option value="0">Names A-Z</option>
						<option value="1">Names Z-A</option>
						<option value="2">Price Ascending</option>
						<option value="3">Price Descending</option>
						<option value="4">Type of bag</option>
						<option value="5">Brand</option>
					</select>
				</div>
			</div>
			<div class="flex-between" id="product-list">
				<div id="filters">
					<h3 id="filter-heading">Filter products by:</h3>
					<hr/>

					<h4 class="criteria">Price ranges</h4>
					<label>
						<input type="checkbox" id="price-range-1" name="price-range-1" class="side-filter" checked />
						<span></span>
						less than $40.00
					</label>
					<label>
						<input type="checkbox" id="price-range-2" name="price-range-2" class="side-filter" checked />
						<span></span>
						$40.00 - $99.99
					</label>
					<label>
						<input type="checkbox" id="price-range-3" name="price-range-3" class="side-filter" checked />
						<span></span>
						$100.00 - $149.99
					</label>
					<label>
						<input type="checkbox" id="price-range-4" name="price-range-4" class="side-filter" checked />
						<span></span>
						$150.00 - $199.99
					</label>
					<label>
						<input type="checkbox" id="price-range-5" name="price-range-5" class="side-filter" checked />
						<span></span>
						more than $200.00
					</label>
					<!--
					<h4 class="criteria">Collections</h4>
					<label>
						<input type="checkbox" id="collection-1" name="collection-1" class="side-filter" checked />
						<span></span>
						Classic Collection
					</label>
					<label>
						<input type="checkbox" id="collection-2" name="collection-2" class="side-filter" checked />
						<span></span>
						Essential Collection
					</label>
					<label>
						<input type="checkbox" id="collection-3" name="collection-3" class="side-filter" checked />
						<span></span>
						Travel System Collection
					</label>
					-->
				</div>
				<div id="product-feed">
					<c:forEach items="${baglist}" var="bag" varStatus="status">
						<div class="content-wrapper ${pfilter[status.index]}">
							<div class="details">
								<div class="featured-image"></div>
								<div class="product-info">
									<h1 class="product-name"><c:out value="${bag.name}"/></h1>
									<h3 class="product-price">$<c:out value="${bag.price}"/></h3>
									<h4 class="product-brand">Brand: <span><c:out value="${bag.brand}"/></span></h4>
									<h4 class="product-type">Type of bag: <span><c:out value="${bag.type}"/></span></h4>
									<h4 class="product-rating">Product rating: <span><c:out value="${bag.rating}"/> out of 5</span></h4>
									<button class="hallow view-product" data-id="${productnames[status.index]}">View product details</button>
								</div>
							</div>
							<br/>
							<hr/>
						</div>
					</c:forEach>
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