<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>401 Page - Session Timeout</title>
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
        
        <link rel="stylesheet" type="text/css" href="css/page-stylesheet/errorpage.css" />
    </head>
    <body>
        <div id="center">
            <div class="flex-between">
                <div><img src="assets/images/cat2.png" draggable="false"></div>
                <form method="post" action="../logout" class="error-msg" id="redirectForm">
                    <div id="error-number">401</div>
                    <div id="error-name" class="msg">Session Timeout</div>
                    <div id="msg" class="msg">Oops! It seems you've been gone a while. We logged you out just to be safe.</div>
                    <a href="#" onclick="document.getElementById('redirectForm').submit()"><span>Go Back to Home</span></a>
                </form>
            </div>
        </div>
    </body>
</html>