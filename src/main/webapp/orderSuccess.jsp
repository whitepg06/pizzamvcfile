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
        <title>Perry's Online Pizza Store</title>
        <link rel="stylesheet" href="mystyle.css">
    </head>
    <body>
        <h1><a href="home.html">Perry's Online Pizza Store</a></h1>
        <h2>Order Success! Thank you for your order!</h2>
        <h3>Order details:</h3>
        <p>Email = ${myOrder.email}</p>
        <p>Size = ${myOrder.size}</p>
        <p>Toppings: ${myOrder.toppings}</p>
        <p>Curst = ${myOrder.crust}</p>
        <%
            String[] toppingArray = request.getParameterValues("toppings");
            double price= 0.0;
            String toppings;
            if(toppingArray!= null && toppingArray.length > 0) {
            toppings = "";
            if(toppingArray.length == 1) {
                toppings = toppingArray[0];
                price += 0.75;
            } else if (toppingArray.length == 1){
                toppings = toppingArray[0];
                price += 0.75;
                } else if (toppingArray.length > 1) {
                    for (int i = 0; i < toppingArray.length - 1; i++) {
                        toppings += toppingArray[i] + ", ";
                        price += 0.75;
                    }
                    toppings += toppingArray[toppingArray.length - 1];
            }
        }
            if(request.getParameter("size").equals("small")) {
                price += 5.00;
            }else if(request.getParameter("size").equals("medium")) {
                price += 6.00;
            }else if(request.getParameter("size").equals("large")) {
                price += 7.00;
            }
            if (request.getParameter("crust").equals("pan")){
                price += 1.00;
            }else if(request.getParameter("crust").equals("hand-tossed")){
            price += 0.50;
        }
            out.println("<p>Price: $" + price + "</p>");
            
          
        %>
        <p><a href='home.html'>Return to home page</a></p>
    </body>
</html>
