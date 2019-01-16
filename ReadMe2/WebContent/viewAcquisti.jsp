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
<link rel="stylesheet" type="text/css" href="CSS/registrazione.css">
<link rel="stylesheet" type="text/css" href="CSS/homeadminresp.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tutti gli acquisti</title>
</head>
<%
	//Prendo il riferimento delle richieste per poter inserire le stesse in maniera dinamica all'interno della pagina
	ArrayList<Libro> acquisti = new ArrayList<>();
	acquisti = (ArrayList<Libro>) request.getAttribute("acquisti");
	//Prendo il riferimento che definisce l'importo totale guadagnato
	Admin u = (Admin) session.getAttribute("admin");
	if (u == null) {
		request.getRequestDispatcher("erroreSession.jsp").forward(request, response);
	}
	int importo = 0;
%>
<body>

	<center>
		<img src="Img/logo.png" alt="Non riesco a caricare l'immagine">
	</center>

	<h1>Vista degli acquisti</h1>

	<!-- Creazione della tabella che permette la visualizzazione dinamica degli acquisti che sono stati fatti -->

	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8 tablediv">
			<!-- qui si inserisce il codice della tabella -->
			<%
				if (acquisti.toString().equals("[]")) {
			%>
			<center>
				<h1>Nessun acquisto effettuato nella piattaforma</h1>
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
					</tr>
				</thead>
				<tbody>
					<%
						for (Libro l : acquisti) {
					%>
					<tr>
						<%
							importo += l.getPrice();
						%>
						<td><img class="icon" src="Img/<%=l.getImmagine()%>.jpg"
							alt="Errore"></td>
						<td class="nome_libro"><%=l.getNome()%></td>
						<td><%=l.getAutore()%></td>
						<td class="prezzo"><%=l.getPrice()%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
			<hr>
			<p class="importo">
				Guadagno totale:
				<%=importo%>
				euro
			</p>
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