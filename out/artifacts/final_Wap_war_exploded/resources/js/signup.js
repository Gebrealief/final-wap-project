  function loadingFunction() {


    $('#userNameSignUp').on('blur',onBlurUsername);
    $('#emailSignUp').on('blur',onBluremail);



}
  function onBlurUsername() {
    var username=$('#userNameSignUp').val();
    $.get('/signup',{"username":username},afterBlur,"json");
}

function afterBlur(data) {
    console.log(data);
    if(data===true) {
        var warning = $('<p>', {
            style: "color:red",
            "id": "warning",
            text: "User name already exists"
        });
        $('#userNameSignUp').after(warning);
    }
    else{
        $('#warning').remove();
    }
}
function onBluremail() {
    var email=$('#emailSignUp').val();
    $.get('/signup',{"email":email},afterEmailBlur,"json");
}
function afterEmailBlur(data) {
    if(data===true) {
        var warning = $('<p>', {
            style: "color:red",
            "id": "warning",
            text: "Email already exists"
        });
        $('#emailSignUp').after(warning);
    }
    else{
        $('#warning').remove();
    }
}
window.onload=loadingFunction;