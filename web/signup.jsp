<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<html lang="en">
<head>
	<title>Sign Up</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="resources/css/mycss.css">
<script >
	$('#submit').click(function(evt) {
		let fullName= $('fullName').val();
		let userName= $('userName').val();
		let email= $('email').val();
		let password= $('password').val();
		let flag=true;
		flag= flag && (fullName);
		flag= flag && (userName);
		flag= flag && (email);
		flag= flag && (password);
		if(! flag ) {
			alert('One or more fields have invalid input.');
			return false;
		}

	});
</script>
</head>
<body background=" resources/img/bg-01.jpg";>
<div >
	<form method="post" action="signup">
		<p>
			<label for="fullName">Full Name: <input id="fullName" type="text" name="fullName" /> </label>
		</p>

		<p>
			<label for="userName">User Name: <input id="userName" type="text" name="userName" /> </label>
		</p>

		<p>
			<label for="password">Password: <input id="password" type="password" name="password" /> </label>
		</p>

		<p>
			<label for="email">Email: <input type="email" id="email" name="email" /> </label>
		</p>
		<p>
			<input id="submit" type="submit" name="submit" />
		</p>
	</form>

</div>

</body>
</html>