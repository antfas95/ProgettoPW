<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="java.util.*,Model.*,DataBase.*,Control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	Utente u = (Utente) session.getAttribute("utente");
	if (u == null) {
		request.getRequestDispatcher("erroreSession.jsp").forward(request, response);
	}
	ArrayList<Libro> offerti = new ArrayList<>();
	offerti = (ArrayList) session.getAttribute("offerte");
%>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css" href="CSS/homeuser.css">
<link rel="stylesheet" type="text/css" href="CSS/homeuseresp.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home utente</title>
</head>
<body>
	<header> <nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="chisiamo.jsp"><img class="iconnav"
				src="Img/logo1.png" alt="non riesco a visualizzare la pagina"></a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="HomeUser.jsp">Home</a></li>
			<li><a href="ricerca.jsp">Ricerca</a>
			<li><a href="miei_acquisti.jsp">Miei Acquisti</a></li>
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
			alt="Non riesco a visualizzare l'immagine">
		<h1 class="basiliare">
			Bentornato:
			<%=u.getNome() + " " + u.getCognome()%><!-- Possibile inserimento del nome dell'utente -->
		</h1>
	</center>

	<center>
		<p>Entra ogni giorno per controllora le nuove offerte messe a
			disposizione dalla piattaforma!!</p>
	</center>

	<center>
		<h1 class="basiliare">Offerte del giorno</h1>

		<!-- Creazione della tabella che permette la visualizzazione dinamica degli acquisti che sono stati fatti -->

		<div class="container-fluid">
			<div class="col-md-2"></div>
			<div class="col-md-8 tablediv">
				<!-- qui si inserisce il codice della tabella -->
				<%
					if (offerti.toString().equals("[]")) {
				%>
				<center>
					<h1>Nessuna offerta disponibile</h1>
				</center>
				<%
					} else {
				%>
				<!-- qui si inserisce il codice della tabella -->
				<table id="tabella" class="table table-striped">
					<thead class="thead-inverse">
						<tr>
							<th>Immagine</th>
							<th>Nome</th>
							<th>Autore</th>
							<th>Prezzo</th>
							<th>Copie disponibili</th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
							for (Libro l : offerti) {
						%>
						<tr>
							<td><img class="icon" src="Img/<%=l.getImmagine()%>.jpg"
								alt="Errore"></td>
							<td id="nome_libro"><%=l.getNome()%></td>
							<td><%=l.getAutore()%></td>
							<td class="prezzo"><%=l.getPrice()%></td>
							<td><%=l.getCopie()%></td>
							<td id="verificaClick"><button
									class="btn btn-mini btn-success aggiungi"
									value="<%=l.getCode()%>">Aggiungi</button></td>
						</tr>
						<%
							}
							}
						%>
					</tbody>
				</table>
			</div>
			<div class="col-md-2"></div>
		</div>

		<script type="text/javascript">
			$(document).on("click", ".aggiungi", function() {
				var idLibro = $(this).val();

				$.ajax({
					url : "AggiuntaCarrello",
					dataType : "HTML",
					data : {
						"codlibro" : idLibro
					},
					success : function(result) {
						alert("libro aggiunto correttamente al carrello");
					}
				});
			});
		</script>
</body>
</html>