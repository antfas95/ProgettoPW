<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Model.*,DataBase.*,Control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css" type="text/css"></link>
<link rel= "stylesheet" type= "text/css" href= "CSS/registrazione.css">
<link rel="stylesheet" type="text/css" href="CSS/homeadminresp.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elimina amministratore</title>
</head>

<%
	Admin u = (Admin) session.getAttribute("admin");
	if (u == null) {
		request.getRequestDispatcher("erroreSession.jsp").forward(request, response);
	}
%>

<body>
	<center>
		<img src="Img/logo.png" alt="Non riesco a caricare l'immagine">
	</center>
	
	<h1>Elimina amministratore</h1>
	
	<form action="EliminaAdmin" method="post">
		<Label>E-Mail</Label>
		<input type="text" name= "mail" class="form-control" placeholder="E-mail" id="name" value="">
		
		<center>
				<button name="registrazione" id="cambiamento" action= "Prova">Elimina</button>
				<a href= "HomeAdmin.jsp"><input type="button" name="indietro" id="cambiamento" value="Indietro"></a>
		</center>
	</form>
</body>
</html>