<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Tela que mostra os erros</title>
</head>
<body>
	<h1>Mensagem de erro: Entre em contato com o suporte do sistema.</h1>
	<p>Contato: wyltamarjavadev@gmail.com <br>
		Watssapp: 05583999645517
	</p>
	<%
		out.print(request.getAttribute("msg"));
	%>
</body>
</html>