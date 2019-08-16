$(function() {

    $("#testBtn").click(function() {
        repopulateProductList();
    } );
    $('div.products').find("button").on('click',addToCart );
});
function repopulateProductList() {
    $('#container').find("*").remove();
    $.post("ajaxProduct", {}).done(populateProductList).fail(populateProductList);
}
function populateProductList(data) {
    var $cont= $('#container');
    $.each(data, function(indx, product) {
        console.log(product.name);
        //var $topDiv= $('<div>').addClass('col-sm-4').addClass('col-md-3').addClass('products');
        var $topDiv= $('<div>').addClass('col-sm-4 col-md-3 products');
        var $productDiv= $('<div>');
        var $img= $("<img>").attr("src" ,"resources/images/mobile.png" ).attr("alt", "mobile").addClass("img-responsive");
        $productDiv.append($img);
        var $h41= $('<h4>').text(product.name);
        $h41.addClass('class-info');
        $productDiv.append($h41);
        var $h42= $('<h4>').text("Description: " + product.description);
        $productDiv.append($h42);
        var $h43= $('<h4>').text("Quantity: " + product.quantity);
        $productDiv.append($h43);
        var $h44= $('<h4>').text("Price: " + product.name);
        $productDiv.append($h44);
        var $input= $("<input>")
        $input.addClass('form-control');
        $input.attr({"type": "text", "value":"1"});
        $productDiv.append("Quantity: ");
        $productDiv.append($input);
        var $button= $('<button>');
        $button.addClass('btn btn-info');
        $button.text('Add To Cart');
        $button.attr('id', product.id);
        $button.on('click',addToCart )
        $productDiv.append($button);
        $productDiv.data('prod', JSON.stringify(product));
        $productDiv.data('id', product.id);
        $productDiv.data('name', product.name);
        $productDiv.data('description', product.description);
        $productDiv.data('price', product.price);
        //.addClass('btn-info').attr('id', product.id).on('click', addToCart(this.id)).text('Add To Cart');

        /*


                $productDiv.append($button);
*/
        $topDiv.append($productDiv);
        $cont.append($topDiv);
        //alert("appended element number: " + indx);
    });

}
function populateProductListFail(xhr, status, exception) {
    alert('json failed.');
}
var id;
/*
* event handler after Add to cart btn click
*
* */
function addToCart(evt) {
/*    id =clicked_id;
    id =this.id;
    alert(this.id);
    //console.log(id);
    var siblings = $('#'+id).siblings().not('img');
    var item = {id: id,
        name: siblings[0].innerText,
        description: siblings[1].innerText.split(":")[1].trim(),
        quantity: siblings[4].value,
        price: siblings[3].innerText.split(":")[1].split("$")[1].trim(),
       // number:siblings[4].value
    };*/
    var $parent= $(this).parent('div');
    var item= {};
    item.id= $parent.data('id');
    item.name=  $parent.data('name');
    item.description= $parent.data('description');
    var qt= parseInt($(this).siblings('input[type="text"]').val());
    item.price= $parent.data('price');
    item.quantity=qt;
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

    console.log(data);
}
$(function () {

    'use strict';



    //display add product form
    $('#btn_add').click(displayPage);
    $('#btn_add_product').click(sendProductDetails);


    //load login page
    $('#login').click(loadLoginPage);
    $('#btn-login-me').on('click', sendLoginDetails);
    //click(sendLoginDetails);


    $('#btn_logout').click(loadLogout);

    $('#checkout').click(checkout);

    $('#signup').click(loadSignUpPage);
    $("#btn-sign-me").click(sendSignUpDetails);
    function sendSignUpDetails(evt) {
        //window.location.href= "signup.jsp";
        var userName= $('#userName').val();
        var password= $('#password').val();
        var fullName= $('#fullName').val();
        var email= $('#email').val();
        $.post("signup",{
            "userName": userName,
            "password": password,
            "fullName": fullName,
            "email": email,
        }).done(successSignUpDetail).fail(successSignUpDetail);
    }
    function successSignUpDetail(data) {
        if(data === "TRUE"){
            adjustTopMenu(true);
            window.location.href="products";
        }else {
            $('#login').click();
        }

    }
    function hideAllControlsExcept(exceptID) {
        $('.buttonControl').addClass('hide');
        $(exceptID).removeClass('hide');
    }

    function loadSignUpPage (evt) {
        hideAllControlsExcept('#signup-control');
        $('#add-prouduct').find("*").remove();
        $.ajax("signup", {
            type: "GET"
        }).done(function(data) {
            $('#add-prouduct').html(data);
        });
        $('#signup-control').removeClass('hide');
    }
    function sendLoginDetails(evt) {

        var userName= $('#user-name').val();
        var password= $('#password').val();
        var checkbox= $('#checkbox').val();
        //alert("us: " + userName + " , ps: " + password + " , ch: " + checkbox );
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
        $('#product-name').val("");
        $('#product-description').val("");
        $('#product-quantity').val("");
        $('#product-price').val("");
        $('#product-name').focus();
        repopulateProductList();

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
        hideAllControlsExcept('#save-new-product');
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
        //alert("top login clicked.");
        hideAllControlsExcept('#login-control');
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

