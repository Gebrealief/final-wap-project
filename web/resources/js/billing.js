$(function () {
    $('#btn').click(placeOrder);
    $('#logout-button').click(logout);
    function placeOrder() {
        var checkout = {action: "checkout"};
        $.get('checkout',{checkout: JSON.stringify("checkout")}).done(confirm).fail(fail);
    }
    function confirm(data) {
       // alert(data);
        console.log("data: "+data);
        if(data == "confirmed"){
           $('#message').text("Thank you for shopping with us! ");
        }
    }
    function  fail() {
        //alert("fail");
        $('#message').text("Thank you for shopping with us! ");
    }
    function message(err) {
        //alert(err);
        $('#message').text("Thank you for shopping with us! ");
    }
    function logout() {
        $.post('logout',{logout:JSON.stringify("logout")}).done(out).fail(fail);
    }
    function out() {
        //alert("out");
        $('#message').text("Thank you for shopping with us! ");
    }
});


function lettersOnly(input){
    var regx = /[^a-z]/gi;
    input.value = input.value.replace(regx, "");
}

function numOnly(inp){
    var reg = /[^0-9]/g;
    inp.value = inp.value.replace(reg, "");
}
function checkb(){
var dv = document.getElementById('id1');
var chb = document.getElementById('hide');
// dv.style.display = 'none';
   if(chb.checked == true){
       dv.style.display = 'initial';
   }else{
       dv.style.display = 'none';
   }
}


