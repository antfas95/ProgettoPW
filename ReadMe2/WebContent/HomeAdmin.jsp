<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,Model.*,DataBase.*,Control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css" type="text/css"></link>
<link rel="stylesheet" type="text/css" href="CSS/homeadmin.css">
<link rel="stylesheet" type="text/css" href="CSS/homeadminresp.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Admin</title>
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
		<p>Attenzione! Con queste operazioni apporterai delle modifiche <b>consistenti</b> all'interno del sistema. Essere sicuri del 
	cambiamento da apportare</p>
	</center>
	
	<h1> Modifica Dati </h1>
	
	<div>
		<center>
			<a href= "aggiungilibro.jsp"><button name="nuovolibro" id="cambiamento">Aggiungi un nuovo libro</button></a>
			<a href= "eliminalibro.jsp"><button name="eliminalibro" id="cambiamento">Elimina un libro dal sistema</button></a>
			<a href= "aggiunginewadmin.jsp"><button name="nuovoadmin" id="cambiamento">Aggiungi nuovo amministratore</button></a>
			<a href= "eliminamministratore.jsp"><button name="nuovoadmin" id="cambiamento">Elimina amministratore</button></a>
			<a href= "modificaprezzo.jsp"><button name="cambioprezzo" id="cambiamento">Modifica prezzo libro</button></a>
			<a href= "modifica_copie.jsp"><button name="cambioprezzo" id="cambiamento">Modifica copie libro</button></a>
			<a href= "eliminazioneutente.jsp"><button name="cambioprezzo" id="cambiamento">Elimina un utente</button></a>
			<form action= "Visualizzacquisti" method= "post">
			<a href= "view_libri_acquistati.jsp"><button name="viewacquisti" id="cambiamento">Visualizza libri acquistati</button></a></form>
			<form action= "Visualizzalerichieste" method= "post">
			<a href= "viewRichieste.jsp"><button name="viewrichieste" id="cambiamento">Visualizza richieste libro</button></a></form>
			<a href= "aggiungiofferta.jsp"><button name="nuovolibro" id="cambiamento">Aggiungi nuova offerta</button></a>
			<a href= "eliminaofferta.jsp"><button name="nuovolibro" id="cambiamento">Elimina offerta</button></a>
			<a href= "login.jsp"><button name="indietro" id="cambiamento">Esci</button></a>
		</center>
	</div>
	
</body>
</html>