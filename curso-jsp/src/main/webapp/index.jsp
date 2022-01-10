<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<title>Curso JSP</title>

<style type="text/css">
	form{
		position: absolute;
		top: 40%;
		left: 33%;
		right: 33%;
	}
	
	h4{
		position: absolute;
		top: 28%;
		left: 33%;
		right: 33%;
	
	}
	
	h5{
		position: absolute;
		top: 85%;
		left: 33%;
		right: 33%;
		color: red;
	}
	
	
</style>

</head>
<body>

	<br>
	<h4>Bem vindo ao Curso de JSP</h4>

	<form action="ServeletLogin" method="post" class="row g-3">
		<input type="hidden" value="<%= request.getParameter("url")%>" name="url">
		
	<div class="col-md-6">
		<label class="form-label">Login</label>
		<input name="login" type="text" class="form-control">
	</div>
	
	<div class="col-md-6">						
		<label class="form-label">Senha</label>
		<input name="password" type="password" class="form-control">
	</div>		

	
		<input type="submit" value="Acessar" class="btn btn-primary">
		
			
	</form>
	<h5>${msg}</h5>

<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

</body>
</html>