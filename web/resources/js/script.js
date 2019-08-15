var id;
/*
* event handler after Add to cart btn click
*
* */
function addToCart(clicked_id) {
    id =clicked_id;
    console.log(id);
    var siblings = $('#'+id).siblings().not('img');
    var item = {id: id,
        name: siblings[0].innerText,
        description: siblings[1].innerText.split(":")[1].trim(),
        quantity: siblings[4].value,
        price: siblings[3].innerText.split(":")[1].split("$")[1].trim(),
       // number:siblings[4].value
    };
    $.post('order', {item: JSON.stringify(item)}).done(saveToCart).fail(fail);
}
// event handler after remove btn click
function removeItemFromCart(clicked_id) {
 //  alert(clicked_id);
    id = clicked_id;
    id = clicked_id.split("-")[1];
    $.get("removeitem",{removeitem:JSON.stringify(id)}).done(removed).fail(fail);
}
// removes item from cart
function removed(data) {
   // alert(data);

    var td = $('#item-'+data).parent();
    var value = td.siblings()[4].innerText.split("$")[1].trim();
    var tr = td.parent();
    tr.remove();
    calculateTotal(-parseFloat(value));
      //parent.remove();
}
//save item details to cart
function saveToCart(data) {

    console.log(parseInt(data.price.trim()));
    console.log(parseInt(data.quantity));

    var td1 =  $('<td>').append($('<button>').addClass("btn btn-danger")
        .attr("id","item-"+data.id)
        .attr("onclick","removeItemFromCart(this.id)")
        .text("Remove"));
    var td2 =  $('<td>').text(data.id);
    var td3 =  $('<td>').text(data.name);
    var td4 =  $('<td>').text("$" + data.price);
    var td5 =  $('<td>').text(data.quantity);
    var td6 =  $('<td>').addClass("subtotal").text("$" + parseFloat(data.price) * parseInt(data.quantity));
    var tr = $('<tr>').append(td1).append(td2).append(td3).append(td4).append(td5).append(td6);
    $('#order-table').append(tr);
    calculateTotal(parseFloat(data.price) * parseInt(data.quantity));
}
//calculate total price
function calculateTotal(data) {
    console.log("data: "+data);
     console.log($('#total-price').text());
     var subtotal = $('#total-price').text().split("$")[1].trim();
    // console.log("subtotal:" + subtotal);
    $('#total-price').text("Total: $" + (parseFloat(subtotal) + (data)));
}
function fail(data) {
     alert("fail");
    console.log(data);
}
$(function () {

    'use strict';
    //display add product form
    $('#btn_add').click(displayPage);
    $('#btn_add_product').click(sendProductDetails);


    //load login page
    $('#login').click(loadLoginPage);
    $('#btn-login-me').click(sendLoginDetails);


    $('#btn_logout').click(loadLogout);
    $('#checkout').click(checkout);
    $('#signup').click(signUpFunction);
    function signUpFunction(evt) {
        window.location.href= "signup.jsp";
    }
    function sendLoginDetails(evt) {
        var userName= $('user-name').val();
        var password= $('password').val();
        var checkbox= $('checkbox').val();
        $.post("login",{
            "user-name": userName,
            "password": password,
            "checkbox": checkbox,
        }).done(successLoginDetail).fail(successLoginDetail);
    }
    function successLoginDetail(data) {
        if(data === "TRUE"){
            adjustTopMenu(true);
            window.location.href="products";
        }else {
            $('#login').click();
        }
    }
    function sendProductDetails() {
        //alert('trying to send ajax ');
        var productName = $('#product-name').val();
        var productDescription = $('#product-description').val();
        var productQuantity = $('#product-quantity').val();
        var productPrice = $('#product-price').val();
        $('save-new-product').removeClass('hide');
        //var product = {name:productName, price:productPrice};
        var product = new Product(productName,productDescription,productQuantity,productPrice);
        //$.post('products',{product: JSON.stringify(product)}, addProduct, "json");
        //alert('name: ' +  productName + " , desc: " + productDescription + " , Quantity: " + productQuantity + " , price: " + productPrice);
        $.post("products",{
                name: productName,
                description: productDescription,
                quantity: productQuantity,
                price: productPrice
        }).done(addProduct).fail(addProductFail);
}
function adjustTopMenu(isLoggedIn) {
    $('#btn_logout').toggleClass('hide');
    $('#btn_add').toggleClass('hide');
    $('#signup').toggleClass('hide');
    $('#login').toggleClass('hide');
}

function addProductFail(xhr, status, exception ) {
        //alert('Ajax Request Failed!');
}
// adds a product to page
    function addProduct(data) {
  /*      console.log("add called");
        //var form = $("<form>").attr("action","order").attr("method","post");
        var prodiv = $("<div>").addClass("products");
        var img = $("<img>").attr("src","resources/images/mobile.png").attr("alt","iPhone").addClass("img-responsive"); // continue worikin
        //var prod_id = $("<input>").attr("type","hidden").attr("value",data.id);
        var prod_name = $("<h4>").text(data.name).addClass("text-info");
        var prod_info = $("<h4>").text("Description: "+data.description);
        var prod_quantity = $("<h4>").text("Quantity: "+data.quantity);
        var prod_price = $("<h4>").text("Price: "+data.price);
        var quantity = $("<input>").attr("type","text").attr("name","quantity").attr("value","1").addClass("form-control");
        var addtocart = $("<button>").addClass("btn btn-info").attr("id",data.id).attr("onclick", "addToCart(this.id)").text("Add To Cart ");
        // var price = $("<input>").attr("type","text").attr("name","price").attr("value","1");
        prodiv.append(img).append(prod_name)
           // .append(prod_id)
            .append(prod_info).append(prod_quantity)
            .append(prod_price).append(quantity)
            .append().append(addtocart);
        //form.append(prodiv);
        $("#container").prepend($("<div>").addClass("col-sm-4 col-md-3 products").append(prodiv));
        $('#add-prouduct').addClass("hide");*/
       //alert(data);
    }
    // display add product form
    function displayPage() {
        $('#save-new-product').removeClass("hide");
        $.ajax("ajaxProduct", {
            type: "GET"
        }).done(function(data) {
            $('#add-prouduct').html(data);
        });
    }
    /*
    * Constructor function
    * */
    function Product(name,description,quantity,price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    function checkout() {
        window.location.href = "billing.jsp";
    }

    function processData(data) {
        alert(data.id);
    }

    //login
    function loadLoginPage(){
        //$.ajax({ "url": "login.jsp", "type": "GET", "success": myAjaxSuccessFunction, "error": ajaxFailure});
        //window.location.href = "login.jsp";
       // $('#login').load('login.jsp');
        $('#add-prouduct').find("*").remove();
        $.ajax("login", {
            type: "GET"
        }).done(function(data) {
            $('#add-prouduct').html(data);
        });
        $('#login-control').removeClass('hide');

    }
    function loadLogout(){
        //$.ajax({ "url": "login.jsp", "type": "GET", "success": myAjaxSuccessFunction, "error": ajaxFailure});
        //alert('about to log out');
        window.location.href = "logout";
       //("body").openURL("products");
        // $('#login').load('logout.jsp');

    }
});

