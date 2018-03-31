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
        <link rel="stylesheet" type="text/css" href="css/page-stylesheet/add-product.css" />

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
                <a href="admin-index.html"><div class="option">View all orders</div></a>
            </div>
            <div class="items">
                <div class="headline">Products</div>
                <hr>
                <a href="admin-bag.html"><div class="option">View all products</div></a>
                <a href="add-product.html"><div class="option active">Add new product</div></a>
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
            <div class="nav-header">
                <p class="flex-start">
                    <i class="fa fa-angle-left fa-2x"></i>
                    <a href="admin-bag.html"><span>View all products</span></a>
                </p>
            </div>
            <div class="info">
                <div class="header">Add New Product</div>
                <div class="details flex-between" >
                    <!--First One -->
                    <div class="part">
                        <div class="type">
                            <div>
                                <div>Name<span>*</span></div>
                                <input type="text" placeholder="Type the product name">
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Brand<span>*</span></div>
                                <input type="text" placeholder="Type the brand name">
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Type of bag<span>*</span></div>
                                <select>
                                    <option>Backpack</option>
                                    <option></option>
                                </select>
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Price</div>
                                <input type="text" placeholder="Type the price">
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Color</div>
                                <input type="text" placeholder="Type the color">
                            </div>
                        </div>
                    </div>
                    <!--Second One -->
                    <div class="part">
                        <div class="type">
                            <div>
                                <div>Rating<span>*</span></div>
                                <select>
                                    <option>1 out of 5</option>
                                    <option>2 out of 5</option>
                                    <option>3 out of 5</option>
                                    <option>4 out of 5</option>
                                    <option>5 out of 5</option>
                                </select>
                            </div>
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Width<span>*</span></div>
                                <input class="dimension" type="text" placeholder="Width">
                            </div>
                            
                            <div>
                                <div>Height<span>*</span></div>
                                <input class="dimension" type="text" placeholder="Height">
                            </div>
                            
                            <div>
                                <div>Length<span>*</span></div>
                                <input class="dimension" type="text" placeholder="Length">
                            </div>
                        </div>
                        <div class="type">
                            <div>Description<span>*</span></div>
                            <textarea class="description" placeholder="Give a short description of the product"></textarea>
                        </div>
                        
                        <!-- Save Changes -->
                        <div class="buttons">
                            <button class="hallow butt">DISCARD</button>
                            <button class="butt save">SAVE CHANGES</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>