<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Unibag Admin - Edit Your Account</title>
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
        <link rel="stylesheet" type="text/css" href="css/page-stylesheet/add-user.css" />

        <!-- JAVASCRIPT -->
        <script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="js/adminformfunctions.js" type="text/javascript"></script>
    </head>
    <body>
        <c:set var="loggedUser" value="${sessionScope.adminAccount}" />
        <c:set var="loggedAddress" value="${adminAddress}" />
        <div class="sidenav">
            <div class="accent"><img id="logo" src="assets/images/unibag-logo.png" draggable="false"></div>
            <div class="items">
                <div class="headline">Orders</div>
                <hr>
                <a href="admin-index.html"><div class="option">View all orders</div></a>
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
                <a href="edit-user.html"><div class="option active">Edit account</div></a>
                <div class="option">Sign out</div>
            </div>
        </div>
        <div class="dashboard">
            <div class="nav-header">
                <p class="flex-start">
                    <i class="fa fa-angle-left fa-2x"></i>
                    <a href="admin-user.html"><span>View all users</span></a>
                </p>
            </div>
            <div class="info">
                <div class="header">Edit Your Account</div>
                <c:if test="${error == true}">
                    <br/>
                    <div class="error-banner flex-between">
                        <p>An error has occured. Please try again.</p>
                        <i class="fa fa-close"></i>
                    </div>
                </c:if>
                <div class="details">
                    <!--First One -->
                    <div class="part">
                        <div class="type flex-between">
                            <div>
                                <div>First name<span>*</span></div>
                                <input type="text" name="firstname" placeholder="Type your first name" value="${loggedUser.firstName}" />
                            </div>
                            
                            <div>
                                <div>Location<span>*</span></div>
                                <input type="text" name="location" placeholder="Type your location" value="${loggedAddress.location}" />
                            </div>
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Last name<span>*</span></div>
                                <input type="text" name="lastname" placeholder="Type your last name" value="${loggedUser.lastName}" />
                            </div>
                            <div>
                                <div>City<span>*</span></div>
                                <input type="text" name="city" placeholder="Type your city" value="${loggedAddress.city}" />
                            </div>
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Email address<span>*</span></div>
                                <input type="text" name="email" placeholder="Type your email address" value="${loggedUser.email}" />
                            </div>
                            <div>
                                <div>Postcode<span>*</span></div>
                                <input type="text" name="postcode" placeholder="Type your postcode" value="${loggedAddress.postcode}" />
                            </div>
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Phone number</div>
                                <c:choose>
                                    <c:when test="${loggedUser.phone == null}">
                                        <input type="number" name="phone" placeholder="Type your phone number" value="" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="number" name="phone" placeholder="Type your phone number" value="${loggedUser.phone}" />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div>
                                <div>Province<span>*</span></div>
                                <input type="text" placeholder="Type your province" value="${loggedAddress.province}" />
                            </div>
                        </div>
                    </div>
                    <!--Second One -->
                    <div class="part">
                        <div class="type flex-between">
                            <div>
                                <div>Old Password<span>*</span></div>
                                <input type="password" name="oldpassword" placeholder="Type your old password" />
                            </div>
                            
                            <div>
                                <div>Type of user<span>*</span></div>
                                <select name="type">
                                    <option value="normal">Normal</option>
                                    <option value="admin">Admin</option>
                                </select>
                            </div>
                        </div>    
                        <div class="type">
                            <div>
                                <div>New password<span>*</span></div>
                                <input type="password" name="newpassword" placeholder="Type your new password" />
                            </div>
                            <div>
                                <div>Confirm new password<span>*</span></div>
                                <input type="password" name="confirmpassword" placeholder="Retype your new password" />
                            </div>
                        </div>    
                    </div>
                    <!-- Save Changes -->
                    <div class="buttons">
                        <button class="hallow butt">DISCARD</button>
                        <button class="butt save">SAVE CHANGES</button>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>