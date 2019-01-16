<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css" type="text/css"></link>
<link rel= "stylesheet" type= "text/css" href= "CSS/registrazione.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Recupero Dati</title>
</head>
<body>

	<center>
		<img src="Img/logo.png" alt="Non riesco a caricare l'immagine">
	</center>
	
	<h1>Recupera la tua password</h1>
	
	<form action="RecuperaDati" method="post">
		<label> Username </label>
			<input type="text" name= "username" class="form-control" placeholder="Username" id="name" value="">
			<center>
				<button name="recupera" id="cambiamento" action= "Prova">Recupera</button>
				<a href= "login.jsp"><input type="button" name="indietro" id="cambiamento" value="Annulla"></a>
			</center>
	</form>
	
	<h1>Recupera il tuo username</h1>
	
	<form action= "RecuperaDati" method="post">
		<label class= "rifinitura"> Domanda di sicurezza </label>
			<select name= "domande">
				<option value= "prima"> Nome della tua mamma? </option>
				<option value= "seconda"> Nome di tuo padre? </option>
				<option value= "terza"> Nome della tua squadra del cuore? </option>
				<option value= "quarta">Nome del tuo piatto prederito? </option>
			</select>
			<input type="text" name= "risposta" class="form-control" placeholder="Risposta" id="name" value="">
			<label> Password </label>
			<input type="text" name= "password" class="form-control" placeholder="Username" id="name" value="">
			<center>
				<input type="submit" name="recupera" id="cambiamento" value="Recupera"></input>
				<a href= "login.jsp"><input type="button" name="indietro" id="cambiamento" value="Annulla"></a>
			</center>
	</form>
	
	<p>Nel caso, non ricordi entrmabi i dati che garantiscono l'accesso alla nostra piattaforma <b>README</b> puoi contattarci
	tramite mail all'indirizzo: <b>antonio.fasulo95@gmail.com</b> oppure telefonicamente al numero: <b>3383177453</b>.</p>
</body>
</html>