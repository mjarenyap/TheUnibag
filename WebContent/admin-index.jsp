<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>THE Unibag - Admin</title>
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
		<link rel="stylesheet" type="text/css" href="css/framework-stylesheet/sidenav.css" />
        
        <!-- PAGE STYLESHEET -->
        <link rel="stylesheet" type="text/css" href="css/page-stylesheet/admin-index.css" />

        <!-- JAVASCRIPT -->
        <script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="js/adminformfunctions.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="sidenav">
            <div class="accent"><img id="logo" src="assets/images/Unibag-logo.png" draggable="false"></div>
            <div class="items">
                <div class="headline">Orders</div>
                <hr>
                <a href="admin-index.html"><div class="option active">View all orders</div></a>
            </div>
            
            <div class="items">
                <div class="headline">Products</div>
                <hr>
                <a href="admin-bag.html"><div class="option">View all products</div></a>
                <a href="add-product.html"><div class="option">Add new product</div></a>
            </div>
            
            <div class="items">
                <div class="headline">Users</div>
                <hr>
                <a href="admin-user.html"><div class="option">View all users</div></a>
                <a href="add-user.html"><div class="option">Add new user</div></a>
            </div>
            
            <div class="items admin">
                <div class="headline">System Admin</div>
                <hr>
                <a href="edit-user.html"><div class="option">Edit account</div></a>
                <div class="option">Sign out</div>
            </div>
        </div>
        
        <div class="dashboard">
            <div class="nav-header flex-between">
                <div>
                    <input type="text" placeholder="Search for orders" id="bar">
                </div>
                <div><button>SAVE CHANGES</button></div>
            </div>
            <div class="table">
                <table>  
                    <tr>
                        <th>Del. Status</th>
                        <th>Order ID</th>
                        <th>Order Date</th>
                        <th>Location</th>
                        <th>City</th>
                        <th></th>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00001</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00002</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00003</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00004</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00005</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00006</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00007</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00008</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00009</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                    <tr>
                        <td><input type="checkbox"></td>
                        <td>00010</td>
                        <td>03/11/18</td>
                        <td>#41</td>
                        <td>Quezon</td>
                        <td><a href="view-order.html"><button class="hallow">View</button></a></td>
                    </tr>
                    
                </table>
            </div>
        </div>
    </body>
</html>