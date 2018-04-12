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
        <link rel="stylesheet" type="text/css" href="css/page-stylesheet/add-user.css" />

        <!-- JAVASCRIPT -->
        <script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="js/adminformfunctions.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="sidenav">
            <div class="accent"><img id="logo" src="assets/images/unibag-logo.png" draggable="false"></div>
            <div class="items">
                <div class="headline">Orders</div>
                <hr>
                <div class="option" data-id="allorders">View all orders</div>
            </div>
            
            <div class="items">
                <div class="headline">Products</div>
                <hr>
                <div class="option" data-id="allproducts">View all products</div>
                <div class="option" data-id="addproduct">Add new product</div>
            </div>
            
            <div class="items">
                <div class="headline">Users</div>
                <hr>
                <div class="option" data-id="allusers">View all users</div>
                <div class="option active" data-id="adduser">Add new user</div>
            </div>
            
            <div class="items admin">
                <div class="headline">System Admin</div>
                <hr>
                <div class="option" id="edit-account">Edit account</div>
                <div class="option" id="logout">Sign out</div>
            </div>
        </div>
        <div class="dashboard">
            <div class="nav-header">
                <p class="flex-start">
                    <i class="fa fa-angle-left fa-2x"></i>
                    <span id="back-all-users">View all users</span>
                </p>
            </div>
            <form method="post" action="addeduser" class="info">
                <div class="header">Add New User</div>
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
                                <input type="text" name="firstname" placeholder="Type the first name" />
                            </div>
                            <div>
                                <div>Location<span>*</span></div>
                                <input type="text" name="location" placeholder="Type the location" />
                            </div>
                            
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Last name<span>*</span></div>
                                <input type="text" name="lastname" placeholder="Type the last name" />
                            </div>
                            <div>
                                <div>City<span>*</span></div>
                                <input type="text" name="city" placeholder="Type the city" />
                            </div>
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Email address<span>*</span></div>
                                <input type="email" name="email" placeholder="Type the email address" />
                            </div>
                            <div>
                                <div>Postcode<span>*</span></div>
                                <input type="number" name="postcode" placeholder="Type the postcode" />
                            </div>
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Phone number</div>
                                <input type="text" name="phone" placeholder="Type the phone number" />
                            </div>
                            <div>
                                <div>Province<span>*</span></div>
                                <input type="text" name="province" placeholder="Type the province" />
                            </div>
                        </div>
                    </div>

                    <!--Second One -->
                    <div class="part">
                        <div class="type flex-between">
                            <div>
                                <div>Password<span>*</span></div>
                                <input type="password" name="password" placeholder="Type the password" />
                            </div>
                            
                            <div>
                                <div>Type of user<span>*</span></div>
                                <select name="userType">
                                    <option value="normal">Normal</option>
                                    <option value="admin">Admin</option>
                                </select>
                            </div>
                        </div>    
                        
                        <div class="type">
                            <div>
                                <div>Confirm password<span>*</span></div>
                                <input type="password" name="confirmpassword" placeholder="Retype the password" />
                            </div>
                        </div>
                        <div>
                            <div>A secret random answer<span>*</span></div>
                            <input type="password" name="securityAnswer" placeholder="Type your new secret answer" />
                        </div>
                    </div>
                    
                    <!-- Save Changes -->
                    <div class="buttons">
                        <button class="hallow butt" type="button" id="discard" data-id="allusers">DISCARD</button>
                        <button type="submit" class="butt save">SAVE CHANGES</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>