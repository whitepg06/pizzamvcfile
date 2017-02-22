<%-- 
    Document   : order
    Created on : Feb 7, 2017, 3:13:05 PM
    Author     : John Phillips
--%>

<%@page import="pizzamvc.PizzaOrder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>John's Online Pizza Store</title>
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">John's Online Pizza Store</a></h1>
        <h2>Order Success! Thank you for your order!</h2>
        <h3>Order details:</h3>
        <p>Email = ${myOrder.email}</p>
        <p>Size = ${myOrder.size}</p>
        <p>Toppings: ${myOrder.toppings}</p>
        <p><a href='home.html'>Return to home page</a></p>
    </body>
</html>
