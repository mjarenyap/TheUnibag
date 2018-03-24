<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

		<section id="account">
			<h1 id="profile-title">Your Account Settings</h1>
			<hr/>
			<div class="flex-start">
				<div id="context-wrapper">
					<button class="active">General Information</button>
					<hr/>
					<button>Change Password</button>
					<hr/>
					<button>Address Information</button>
				</div>
				<form action="profile" method="post" id="profile-form">
					<h1 id="context-title">General Information</h1>
					<label>
						<span>First name</span>
						<input type="text" name="firstname" value="William Luther" class="full-width" />
					</label>
					<label>
						<span>Last name</span>
						<input type="text" name="lastname" value="Shakespeare" class="full-width" />
					</label>
					<label>
						<span>Email address</span>
						<input type="email" name="email" value="user@example.com" class="full-width" />
					</label>
					<label>
						<span>Phone number</span>
						<input type="number" name="phone" value="091234567" class="full-width" />
					</label>
					<div id="confirm-buttons" class="flex-end">
						<button class="hallow">Cancel</button>
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