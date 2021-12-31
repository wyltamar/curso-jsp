<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Curso JSP</title>
</head>
<body>

	<br>
	<h1>Welcome to the JSP course</h1>

	<form action="ServeletLogin" method="post">
		<table>

			<tr>
				<td><label>Login:</label>
				<td><input name="login" type="text"></td>
					
			</tr>
			
			<tr>
				<td><label>Password:</label>
				<td><input name="password" type="password"></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Submit"></td>
			</tr>
			
		</table>
	</form>
	<h4>${msg}</h4>
</body>
</html>