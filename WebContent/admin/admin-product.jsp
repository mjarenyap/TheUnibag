<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>The Unibag Admin - View All Bags</title>
        <!-- FONT EXTERNAL LINKS -->
        <link href="https://fonts.googleapis.com/css?family=Source+Serif+Pro:400,700" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,400i,700" rel="stylesheet" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

        <!-- FRAMEWORK STYLESHEET | NOTE: DO NOT EDIT -->
        <link rel="stylesheet" type="text/css" href="../css/framework-stylesheet/theme.css" />
        <link rel="stylesheet" type="text/css" href="../css/framework-stylesheet/nav.css" />
        <link rel="stylesheet" type="text/css" href="../css/framework-stylesheet/footer.css" />
        <link rel="stylesheet" type="text/css" href="../css/framework-stylesheet/flex.css" />
        <link rel="stylesheet" type="text/css" href="../css/framework-stylesheet/button.css" />
        <link rel="stylesheet" type="text/css" href="../css/framework-stylesheet/field.css" />
        <link rel="stylesheet" type="text/css" href="../css/framework-stylesheet/sidenav.css" />
        
        <!-- PAGE STYLESHEET -->
        <link rel="stylesheet" type="text/css" href="../css/page-stylesheet/admin-index.css" />

        <!-- JAVASCRIPT -->
        <script src="../js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="../js/adminformfunctions.js" type="text/javascript"></script>     
    </head>
    <body>
        <div class="sidenav">
            <div class="accent"><img id="logo" src="../assets/images/Unibag-logo.png" draggable="false"></div>
            <div class="items">
                <div class="headline">Orders</div>
                <hr>
                <div class="option" data-id="allorders">View all orders</div>
            </div>
            
            <div class="items">
                <div class="headline">Products</div>
                <hr>
                <div class="option active" data-id="allproducts">View all products</div>
                <div class="option" data-id="addproduct">Add new product</div>
            </div>
            
            <div class="items">
                <div class="headline">Users</div>
                <hr>
                <div class="option" data-id="allusers">View all users</div>
                <div class="option" data-id="adduser">Add new user</div>
            </div>
            
            <div class="items admin">
                <div class="headline">System Admin</div>
                <hr>
                <div class="option">Edit account</div>
                <div class="option">Sign out</div>
            </div>
        </div>
        <div class="dashboard">
            <div class="nav-header flex-between">
                <div><input type="text" placeholder="search" id="bar"></div>              
                <div><button id="save-changes" data-id="dp">SAVE CHANGES</button></div> 
            </div>
            <div class="table">
                <table>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Brand</th>
                        <th>Type</th>
                        <th>Collection</th>
                        <th>Rating</th>
                        <th>Price</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${baglist}" var="bag" varStatus="status">
                        <tr class="content-product-row">
                            <td><input type="checkbox" class="delete-status" data-id="${productNames[status.index]}" /></td>
                            <td><c:out value="${bag.name}" /></td>
                            <td><c:out value="${bag.brand}" /></td>
                            <td><c:out value="${bag.type}" /></td>
                            <td><c:out value="${bag.collection}" /></td>
                            <td><c:out value="${bag.rating}" /> out of 5</td>
                            <td>$<c:out value="${bag.price}" /></td>
                            <td><button class="hallow" data-id="${productNames[status.index]}">Edit</button></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>