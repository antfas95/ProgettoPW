<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, Control.*, DataBase.*, Model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/logincss.css">
<link rel="stylesheet" type="text/css" href="CSS/loginresponsive.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area Login</title>
</head>
<body>

	<center class="miglioramento">Entra, per poter usufruire di
		tutte le funzionalità da noi offerte.</center>
	
	<center>
		<img src="Img/logo.png" alt="Non riesco a caricare l'immagine">
	</center>
	<form action= "Login" method="post">
		<center>
			<label>Username: <input type="text" placeholder="username"
				name="username" class="form-control input-lg">
			</label> <label class="posizionamento">Password: <input
				type="password" placeholder="password" name="password">
			</label>
			<button name="accesso" id="cambiamento">Login</button>
			<a href= "chisiamo.jsp"><input type="button" value="Entra come ospite" id="cambiamento" class= "posizione"></a>
			<a class= "posizione" href="registrazione.jsp"> Crea account </a> o <a href="recuperodati.jsp" class= "posizione">recupera dati</a>
		</center>
	</form>
</body>
</html>