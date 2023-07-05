<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="basharatkaranwalfranco.store.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Product> products = (ArrayList<Product>) request.getAttribute("products");
    String selected = (String) request.getAttribute("category");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fresco | Home</title>
        <link rel="stylesheet" href="style.css"/>
        <link rel="icon" type="image/x-icon" href="favicon.jpg">
    </head>
    <body>
        <div class="topnav" id="topnav">
            <a id="btn all" class="active" href="?category=all">All Products</a>
            <a id="btn f" href="?category=f">Fruits</a>
            <a id="btn v" href="?category=v">Vegetables</a>
            <a id="btn m" href="?category=m">Meat</a>
        </div>
        <div class="card-container">

            <% if (products != null && products.size()>0) { %>

            <% for (Product product : products) { %>

            <div class="card">

                <%="<img src='"+product.getImageUrl()+"'>"%>
                <%="<h1>"+product.getName()+"</h1>"%>
                <%="<p class='price'>$ "+product.getPrice()+"</p>"%>
                <%="<p>"+product.getDescription()+"</p>"%>
                <%="<p><form method='post' action='?action=minus&productId="+product.getId()+"'><button type='submit'>-</button></form> "+product.getQuantity()+" <form method='post' action='?action=plus&productId="+product.getId()+"'><button type='submit'>+</button></form></p>"%>
            </div>

            <% } %>

            <% } else { %>
            <h3> No products to display </h3>
            <% }  %>
        </div>
        <script>
            window.onload = () => {
                // Get the container element
                var btnContainer = document.getElementById("topnav");

// Get all buttons with class="btn" inside the container
                var btns = btnContainer.getElementsByClassName("btn");

// Loop through the buttons and add the active class to the current/clicked button
                for (var i = 0; i < btns.length; i++) {
                    btns[i].addEventListener("click", function () {
                        var select = <%= selected %>;
                        var current = document.getElementById(select);
                        current.className = current.className.replace(" active", "");
                        this.className += " active";
                    });
                }
            };

        </script>
    </body>
</html>
