<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>billing</title>

    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="resources/css/billing.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"   integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="   crossorigin="anonymous"></script>
    <script src="resources/js/billing.js"></script>
</head>
<body>
<%--<form name="bill-form" method="POST">--%>
    <div id="main">
        <div id="header">
            <h3>Shiping and Payment</h3>
        </div>
        <div class="row" id="div1">
            <div class="col-md-4">
                <input type="text" name="city" placeholder="Ciity" onkeyup ="lettersOnly(this)" required/>
            </div>
            <div class="col-md-4">
                <input type="text" name="state"  placeholder="State (two letters)" onkeyup ="lettersOnly(this)" required/>
            </div>
            <div class="col-md-4">
                <input type="text" name="country" placeholder="Country" onkeyup ="lettersOnly(this)" required/>
            </div>
        </div>

        <div class="row">

            <div class="col-md-4">
                <input type="text" name="st-num" placeholder="Street-Number" onkeyup = "numOnly(this)" required />
            </div>
            <div class="col-md-4">
                <input type="text" name="address" placeholder="Address-line 1" required/>
            </div>
            <div class="col-md-4">
                <input type="text" name="address2" placeholder="Address-line 2"/>
            </div>

        </div>

        <fieldset>
            <legend>Card type</legend>
            <div class="row">

                <div class="col-md-4">
                    <input type="radio" name="card" />  <img src="resources/images/master.png" width="70px" />
                </div>

                <div class="col-md-4">
                    <input type="radio" name="card" /> <img src="resources/images/visa.png" width="60px" />
                </div>
                <div class="col-md-4">
                    <input type="radio" name="card" />  <img src="resources/images/amex.png" width="60px" />
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <input type="number" name="card-num" placeholder="Card-Number"  onkeyup = "numOnly(this)" required/>
                </div>

                <div class="col-md-4">
                    <input type="number" name="scv" placeholder="Security-code"  onkeyup = "numOnly(this)" required/>
                </div>
                <div class="col-md-4">
                    <input type="checkbox" id="hide" onchange="checkb()"> Shiping Address is deferent from Billing Address.</input>
                </div>

            </div>
        </fieldset>

        <div id="id1">


            <div class="row">
                <div class="col-md-4">
                    <input type="text" name="city" placeholder="Ciity" onkeyup ="lettersOnly(this)" required/>
                </div>
                <div class="col-md-4">
                    <input type="text" name="state"  placeholder="State (two letters)" onkeyup ="lettersOnly(this)" required/>
                </div>
                <div class="col-md-4">
                    <input type="text" name="country" placeholder="Country" onkeyup ="lettersOnly(this)" required/>
                </div>
            </div>
            <div class="row">

                <div class="col-md-4">
                    <input type="text" name="st-num" placeholder="Street-Number" onkeyup = "numOnly(this)" required />
                </div>
                <div class="col-md-4">
                    <input type="text" name="address" placeholder="Address-line 1" required/>
                </div>
                <div class="col-md-4">
                    <input type="text" name="address2" placeholder="Address-line 2"/>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
<%--                <input type="button" name="orderbtn" id="btn" value="Place Order" />--%>
                <button id="btn"> Place Order</button>
            </div>
        </div>
    </div>
<div id="message-box">
    <p id="message">

    </p>
    <a href="products"> Continute shopping ... </a>
    <form action="products" method="get">
        <input type="submit" name="logout" value="logout">
    </form>
</div>
<%--</form>--%>
<div id="pop">This is just to try</div>
</body>
</html>
