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
                <div class="option active" data-id="addproduct">Add new product</div>
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
            <div class="nav-header">
                <p class="flex-start">
                    <i class="fa fa-angle-left fa-2x"></i>
                    <a href="admin-bag.html"><span>View all products</span></a>
                </p>
            </div>
            <form method="post" action="addedproduct" class="info">
                <div class="header">Add New Product</div>
                <div class="details flex-between" >
                    <!--First One -->
                    <div class="part">
                        <div class="type">
                            <div>
                                <div>Name<span>*</span></div>
                                <input type="text" name="name" placeholder="Type the product name" />
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Brand<span>*</span></div>
                                <input type="text" name="brand" placeholder="Type the brand name" />
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Type of bag<span>*</span></div>
                                <select name="type">
                                    <option value="Backpack">Backpack</option>
                                    <option value="Duffle Bag">Duffle Bag</option>
                                    <option value="Handbag">Handbag</option>
                                    <option value="Messenger Bag">Messenger Bag</option>
                                    <option value="Shoulder Bag">Shoulder Bag</option>
                                    <option value="Tote">Tote</option>
                                    <option value="Tote Bag">Tote Bag</option>
                                </select>
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Price</div>
                                <input type="number" name="price" placeholder="Type the price" />
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Color</div>
                                <input type="text" name="color" placeholder="Type the color" />
                            </div>
                        </div>
                    </div>
                    <!--Second One -->
                    <div class="part">
                        <div class="type">
                            <div>
                                <div>Collection<span>*</span></div>
                                <select name="collection">
                                    <option value="Classic Collection">Classic Collection</option>
                                    <option value="Essential Collection">Essential Collection</option>
                                    <option value="Travel System Collection">Travel System Collection</option>
                                </select>
                            </div>
                        </div>
                        <div class="type">
                            <div>
                                <div>Rating<span>*</span></div>
                                <select name="rating">
                                    <option value="1">1 out of 5</option>
                                    <option value="2">2 out of 5</option>
                                    <option value="3">3 out of 5</option>
                                    <option value="4">4 out of 5</option>
                                    <option value="5">5 out of 5</option>
                                </select>
                            </div>
                        </div>
                        <div class="type flex-between">
                            <div>
                                <div>Width<span>*</span></div>
                                <input class="dimension" type="text" name="width" placeholder="Width" />
                            </div>
                            
                            <div>
                                <div>Height<span>*</span></div>
                                <input class="dimension" type="text" name="height" placeholder="Height" />
                            </div>
                            
                            <div>
                                <div>Length<span>*</span></div>
                                <input class="dimension" type="text" name="length" placeholder="Length" />
                            </div>
                        </div>
                        <div class="type">
                            <div>Description<span>*</span></div>
                            <textarea class="description" name="description" placeholder="Give a short description of the product"></textarea>
                        </div>
                        
                        <!-- Save Changes -->
                        <div class="buttons">
                            <button class="hallow butt">DISCARD</button>
                            <button type="submit" class="butt save">SAVE CHANGES</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>