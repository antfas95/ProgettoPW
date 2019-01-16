<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css" type="text/css"></link>
<link rel= "stylesheet" type= "text/css" href= "CSS/registrazione.css">
<link rel="stylesheet" type="text/css" href="CSS/homeadminresp.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Area Registrazione</title>
</head>
<body>
	<center>
		<img src="Img/logo.png" alt="Non riesco a caricare l'immagine">
	</center>
	
	<h1>Registrazione</h1>
	
		<form action="Registrazione" method="post">
			<Label>Nome</Label>
			<input type="text" name= "nome" class="form-control" placeholder="Nome" id="name" value="">
			<Label>Cognome</Label>
			<input type="text" name= "cognome" class="form-control" placeholder="Cognome" id="name" value="">
			<Label>Indirizzo</Label>
			<input type="text" name= "indirizzo" class="form-control" placeholder="Indirizzo" id="name" value="">
			<Label>E-mail</Label>
			<input type="text" name= "postaelettronica" class="form-control input-lg" placeholder="E-mail" id="name" value="">
			<Label>Password</Label>
			<input type="password" name= "password1" class="form-control" placeholder="Password" id="name" value="">
			<Label>Ridigita Password</Label>
			<input type="password" name= "password2" class="form-control" placeholder="Password" id="name" value="">
			<label class= "rifinitura"> Domanda di sicurezza </label>
			<select name= "domande">
				<option> Nome della tua mamma? </option>
				<option> Nome di tuo padre? </option>
				<option> Nome della tua squadra del cuore? </option>
				<option>Nome del tuo piatto prederito? </option>
			</select>
			<input type="text" name= "risposta" class="form-control" placeholder="Risposta" id="name" value="">
			<center>
				<button name="registrazione" id="cambiamento" action= "Prova">Registrati</button>
				<a href= "login.jsp"><input type="button" id="cambiamento" value="Indietro"></a>
			</center>
		</form>
		
</body>
</html>