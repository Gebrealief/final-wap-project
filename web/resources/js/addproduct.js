$(function () {
    $('#btn_add').click(displayPage);
    //add product to store
    $('#btn_add_product').click(sendProductDetails);
    //load login page

    function sendProductDetails() {
        var productName = $('#product-name').val();
        var productDescription = $('#product-description').val();
        var productQuantity = $('#product-quantity').val();
        var productPrice = $('#product-price').val();

        //var product = {name:productName, price:productPrice};
        var product = new Product(productName,productDescription,productQuantity,productPrice);
        $.post('products',{product: JSON.stringify(product)}, addProduct, "json");
    }
    function addProduct(data) {
        console.log("add called");
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
        $('#add-prouduct').addClass("hide");
    }
})