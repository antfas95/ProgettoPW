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
<link rel="stylesheet" type="text/css" href="CSS/homeuseresp.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Miei Acquisti</title>

<%
	//Prendo il riferimento alla mail dell'utente da passare alla servlet
	ArrayList<Libro> acquistati = (ArrayList<Libro>) session.getAttribute("acquistati");
	//Prendo il riferimento della variabile che definisce l'importo totale che è stato speso
	int totale= 0;
	
	Utente u= (Utente) session.getAttribute("utente");
	if (u==null){
		request.getRequestDispatcher("erroreSession.jsp").forward(request, response);
	}
	
%>

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
			<li><a href="miei_acquisti.jsp">Miei
					Acquisti</a></li>
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

	<h1>Da questa pagina puoi trovare tutti i libri che hai acquistato</h1>

	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table table-striped">
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
						for (Libro i : acquistati) {
					%>
					<tr>
						<td><img class="icon" src="Img/<%=i.getImmagine()%>.jpg"
							alt="Non esiste l'immagine"></td>
						<td><%=i.getNome()%></td>
						<td><%=i.getAutore()%></td>
						<td><%=i.getPrice()%></td>
						<%totale+= i.getPrice(); %>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
			<hr>
			<p class="importo">
				Totale acquisti:
				<%=totale%>
				euro
			</p>
		</div>
		<div class="col-md-2"></div>
	</div>
</body>
</html>