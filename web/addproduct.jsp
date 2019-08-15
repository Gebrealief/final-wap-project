
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add product</title>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%--    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">--%>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/css/mycss.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/script.js"></script>
</head>
<body>
<div id="add-prouduct">
  <pre>
      <label>Name: </label> <input type="text" name="product-name" id="product-name" required>
      <label>Description</label> <input type="text" name="product-description" id="product-description" required>
      <label>Quantity: </label><input type="text" name="product-quantity" id="product-quantity" required>
      <label>Price: </label><input type="text" name="product-price" id="product-price" required>
      <button id="btn_add_product" class="btn btn-info">Add</button>
  </pre>
</div>
</body>
</html>
