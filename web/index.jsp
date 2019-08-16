<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="data.access.MapUsers" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>


<!DOCTYPE HTML>
<html>
<head>
    <title>electronic store</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%--    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/mycss.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Add fancyBox -->
    <link rel="stylesheet" href="jquery.fancybox.css" type="text/css" media="screen"/>
    <script type="text/javascript" src="jquery.fancybox.pack.js"></script>
    <script type="text/javascript" src="resources/js/script.js"></script>
<style>

    #add-prouduct {
        background-color: lightcyan;
        margin: 3px;
    }
    #add-prouduct label {
        font-weight: bolder;
        text-align: right;
        padding-right: 2px;
        display: inline-block;
        width: 110px;
    }
    #add-prouduct input[type="text|email|number|password"] {
        border-radius: 3px;
        font-size: 10px;
        display: inline-block;
        margin-left: 2px;
        background-color: lightgoldenrodyellow;
    }
    #add-prouduct h1 {
        font-size: 18px;
        width: 50%;
        margin-left: auto;
        margin-right: auto;
        font-weight: bolder;
    }
    div.products h4:first-of-type {
        color: royalblue;
    }
</style>
</head>
<div>
    <body>

        <%
    boolean isLoggedIn=false;
    isLoggedIn= ( session.getAttribute("userName") != null ) ;
%>

    <div id="header">
        <% if (isLoggedIn) { %>
        <button id="btn_add" class="btn btn-info"> Add Product</button>
        <button id="btn_logout" class="btn btn-info"> log out</button>
        <% } else { %>
        <button id="login" class="btn btn-info"> login</button>
        <button id="signup" class="btn btn-info"> signup</button>
        <% } %>
    </div>

    <%--<div id="login-form">--%>
    <%--    --%>
    <%--</div>--%>

    <div id="container">
        <c:forEach items="${products}" var="product">
            <div class="col-sm-4 col-md-3 products pdContainer">
                <div class="products" data-id="${product.id}" data-name="${product.name}" data-description="${product.description}" data-price="${product.price}">
                    <img src="resources/images/mobile.png" alt="mobile" class="img-responsive"/>
                    <h4 class="text-info"><c:out value="${product.name}"/></h4>
                    <h4> Description: <c:out value="${product.description}"/></h4>
                    <h4> Quantity: <c:out value="${product.quantity}"/></h4>
                    <h4> Price: <c:out value="$${product.price}"/></h4>
                    <input type="text" name="quantity" class="form-control" value="1">
                    <button class="btn btn-info" id="${product.id}" onclick="addToCart(this.id)"> Add To Cart</button>
                </div>
            </div>
        </c:forEach>

    </div>
    <div id="cart-container">

        <div id="add-prouduct" <%--class="hide">--%> >
            <%--  <pre>
                  <label>Name: </label> <input type="text" name="product-name" id="product-name" required>
                  <label>Description</label> <input type="text" name="product-description" id="product-description" required>
                  <label>Quantity: </label><input type="text" name="product-quantity" id="product-quantity" required>
                  <label>Price: </label><input type="text" name="product-price" placeholder="$100" id="product-price" required>
                  <button id="btn_add_product" class="btn btn-info">Add</button>
              </pre>--%>
        </div>
        <div id="save-new-product" class="hide buttonControl">
            <button id="btn_add_product" class="btn btn-info">Add</button>
        </div>
        <div id="login-control" class="hide buttonControl">
            <button id="btn-login-me" class="btn btn-info">Login</button>
        </div>
        <div id="signup-control" class="hide buttonControl">
            <button id="btn-sign-me" class="btn btn-info">Sign Up</button>
        </div>
        <div id="checkout-control" class="hide buttonControl">
            <button id="btn-checkout-me" class="btn btn-info">check out</button>
        </div>
        <h3> Items in your Cart </h3></br>
        <table class="table table-striped" cellpadding="2" cellspacing="2" id="order-table">
            <th>Option</th>
            <th>Id</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <c:forEach var="item" items="${sessionScope.cart}">
                <tr>
                    <td>
                        <button type="button" class="btn btn-danger" id="item-${item.id}"
                                onclick="removeItemFromCart(this.id)">Remove
                        </button>
                    </td>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>$${item.price}</td>
                    <td>${item.quantity}</td>
                    <td>$${item.price * item.quantity}</td>
                </tr>

            </c:forEach>

        </table>

        <h6 id="total-price">Total: $<%=session.getAttribute("total") != null ? session.getAttribute("total") : "0.0"%>
        </h6>

        <button class="btn btn-info" id="checkout"> Check Out</button>
    </div>
</div>

</body>
<%--<footer>--%>
<%--    <p> Designed by: Tsige, Aklilu and Milki.</p>--%>

<%--    <p> Contact info: <a href="mailto: tbirhane.mum.edu" >tbirhane.mum.edu</a>--%>
<%--</footer>--%>
</html>
