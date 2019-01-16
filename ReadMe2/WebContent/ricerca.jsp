<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,Model.*,DataBase.*,Control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca</title>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css" href="CSS/homeuser.css">
<link rel="stylesheet" type="text/css" href="CSS/homeuseresp.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/homeuser.css">
</head>

<%
	Utente u = (Utente) session.getAttribute("utente");
	if (u == null) {
		request.getRequestDispatcher("erroreSession.jsp").forward(request, response);
	}
%>

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
	</center>

	<center>
		<form action="#" method="post" id="form-ricerca">
			<div class="posizionamento">
				<input type="text" class="controllo" name="ricerca" id="ricerca"
					placeholder="Nome-Prezzo-Autore-Codice" id="name" value="">
			</div>
			<input type="submit" class="controllosubmit" value="Ricerca">
		</form>
	</center>

	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<div id="risultato"></div>
		</div>
		<div class="col-md-2"></div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#form-ricerca").submit(function() {
				var ricerca = $("#ricerca").val();

				$.ajax({
					url : "Ricerca",
					dataType : "HTML",
					data : {
						"ricerca" : ricerca
					},
					success : function(result) {
						$("#risultato").html(result);
					}
				});
				return false;
			});
		});

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