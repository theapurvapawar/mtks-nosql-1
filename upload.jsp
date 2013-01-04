<!DOCTYPE html>
<html>
	<head>

		<title> Upload Page | MTKS </title>
		<link rel="stylesheet" type="text/css" href="css/style.css">

	</head>

	<body>
		
		<%@ include file="includes/header-div.jsp" %> 

		<div id='container'>
			
			<form action="result.html" method="post">

				<input id='u_button' type='submit' value='Go'></input>
				<input id='titlebox' type='text' placeholder='Enter Title Here...'></input>
				<textarea placeholder='Enter Text Here...'></textarea>
			
			</form>

		</div>

		<%@ include file="includes/footer-div.jsp" %>
  
	</body>
</html>
