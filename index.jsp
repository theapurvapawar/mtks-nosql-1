<!DOCTYPE html>
<html>
	<head>

		<title> Search Page | MTKS </title>
		<link rel="stylesheet" type="text/css" href="css/style.css">

	</head>

	<body>
		
		<%@ include file="includes/header-div.jsp" %>

		<div id='container'>
			
			<form action="result.jsp" method="post">

				<img id='logo_img' src="images/logo.png" alt="Logo">
				<h2>Multi-term Keyword Search</h2>
				<h3>in NoSQL Systems</h3>
				<input id='txtbox' type='text' placeholder='Enter Search Term Here...'></input>
				<input id='s_button' type='submit' value='Go'></input>
			
			</form>

		</div>

		<%@ include file="includes/footer-div.jsp" %>
  
	</body>
</html>
