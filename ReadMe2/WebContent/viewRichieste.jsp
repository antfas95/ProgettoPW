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
<title>Vista delle richieste</title>
</head>

<%
	//Prendo il riferimento delle richieste per poter inserire le stesse in maniera dinamica all'interno della pagina
	ArrayList<Richiesta> richieste = new ArrayList<>();
	richieste = (ArrayList) request.getAttribute("richieste");
	Admin u = (Admin) session.getAttribute("admin");
	if (u == null) {
		request.getRequestDispatcher("erroreSession.jsp").forward(request, response);
	}
%>

<body>
	<center>
		<img src="Img/logo.png" alt="Non riesco a caricare l'immagine">
	</center>

	<h1>Vista delle richieste</h1>
	<!-- Creazione della tabella che permette la visualizzazione dinamica degli acquisti che sono stati fatti -->

	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8 tablediv">
			<!-- qui si inserisce il codice della tabella -->
			<table class="table table-striped">
				<thead class="thead-inverse">
					<tr>
						<th>Numero</th>
						<th>Nome</th>
						<th>Descrizione</th>
						<th>Referente</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Richiesta i : richieste) {
					%>
					<tr>
						<td><%=i.getIdentificativo()%></td>
						<td><%=i.getName()%></td>
						<td><%=i.getDescrizione()%></td>
						<td><%=i.getReferente()%></td>
						<td><a href="aggiungilibro.jsp"  class="btn btn-mini btn-success">Aggiungi</a></td>				
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
			<a href= "HomeAdmin.jsp"><button type="button" class="btn btn-info">Indietro</button></a>
		</div>
	</center>
</body>
</html>