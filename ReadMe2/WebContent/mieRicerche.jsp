<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, Control.*, DataBase.*, Model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css" href="CSS/homeuser.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Le mie ricerche</title>
</head>

<%
	ArrayList<Libro> ricercati = new ArrayList<>();
	ricercati = (ArrayList) request.getAttribute("ricercati");
%>

<body>
	<header> <nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="chisiamo.jsp"><img class="iconnav"
				src="Img/logo1.png" alt="non riesco a visualizzare la pagina"></a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="HomeUser.jsp">Home</a></li>
			<li><a href="mieRicerche.jsp">Mie ricerche</a></li>
			<li class="dropdown"><a href="ricerca.jsp">Ricerca<span
					class="caret"></span></a>
			<li><a href="richiestaUtente.jsp">Richiesta</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="carrello.jsp"><img class="iconcar"
					src="Img/carrello.png" alt="Non riesco a caricare l'immagine"><span
					class="glyphicon glyphicon-user"></span></a></li>
			<li><a href="confirmLogout.jsp"><img class="iconcar"
					src="Img/logout.png" alt="Non riesco a caricare l'immagine"><span
					class="glyphicon glyphicon-log-in"></span></a></li>
		</ul>
	</div>
	</nav></header>

	<center>
		<img class="logo" src="Img/logo.png"
			alt="Non riesco a caricare l'immagine">
	</center>

	<h1 class="titolo">Le mie ricerche</h1>

	<!-- Creazione della tabella che permette la visualizzazione dinamica degli acquisti che sono stati fatti -->

	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8 tablediv">
			<!-- qui si inserisce il codice della tabella -->
			<%@page import="Model.Libro"%>
			<%@page import="java.util.ArrayList"%>
			<%
				ArrayList<Libro> trovati = (ArrayList<Libro>) request.getAttribute("trovati");
			%>


			<table class="table table-striped">
				<thead class="thead-inverse">
					<tr>
						<th>Immagine</th>
						<th>Nome</th>
						<th>Autore</th>
						<th>Prezzo</th>
						<th>Copie</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Libro i : trovati) {
					%>
					<tr>
						<td><img class="icon" src="Img/<%=i.getImmagine()%>.jpg"
							alt="Non esiste l'immagine"></td>
						<td><%=i.getNome()%></td>
						<td><%=i.getAutore()%></td>
						<td><%=i.getPrice()%></td>
						<td><%=i.getCopie()%></td>
						<td><a class="btn btn-mini btn-success">Aggiungi</a></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<div class="col-md-2"></div>
	</div>
	<center>
		<div id="show-table">
			<a href="HomeAdmin.jsp"><button type="button"
					class="btn btn-info">Indietro</button></a>
		</div>
	</center>

</body>
</html>