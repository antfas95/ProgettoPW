<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*,Model.*,DataBase.*,Control.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="CSSBoot/bootstrap.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="CSSBoot/bootstrap-theme.css"
	type="text/css"></link>
<link rel="stylesheet" type="text/css" href="CSS/homeuser.css">
<link rel="stylesheet" type="text/css" href="CSS/carrelloresp.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Carrello</title>
</head>

<%
	ArrayList<Libro> carrello = new ArrayList<>();
	carrello = (ArrayList) session.getAttribute("carrello");
	int importo = 0;
	
	Utente u= (Utente) session.getAttribute("utente");
	if (u==null){
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
			alt="Non riesco a visualizzare l'immagine">
		<p class="carrello">Da questa pagina è possibile visualizzare i
			tuoi elementi presenti all'interno del carrello, inoltre potrai
			acquistare i tuoi prodotti, cliccando il pulsante 'Acquista'</p>
	</center>

	<center>
		<h1 class="basiliare">Ecco il tuo carrello</h1>
	</center>

	<!-- Creazione della tabella che permette la visualizzazione dinamica degli acquisti che sono stati fatti -->

	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8 tablediv">
			<!-- qui si inserisce il codice della tabella -->
			<%
				if (carrello.toString().equals("[]")) {
			%>
			<center>
				<h1>Nessun elemento nel carrello</h1>
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
						<th></th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Libro l : carrello) {
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
						<td id="verificaClick"><button type="button"
								class="btn btn-danger elimina" value="<%=l.getCode()%>">Rimuovi</button></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
			<hr>
			<p class="importo">
				Importo totale:
				<%=importo%>
				euro
			</p>
		</div>
		<div class="col-md-2"></div>
	</div>
	<br>
	<br>

	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h1 class="formacquisto">Acquista</h1>
			<form action="AcquistaProdotti" method="post">
				<Label class="rifinitura">Pagamento</Label> <select name="domande">
					<option>Carta di credito</option>
					<option>PostePay</option>
					<option>PayPal</option>
				</select> <br> <label class="rifinitura">Numero della carta</label> <input type="text"
					name="nome" class="form-control inputcarr" placeholder="Numero della carta"
					id="name" value="">
				<p class="descrizione"> Cliccando il pulsante acquista accetterai di acquistare i seguenti libri:<br>
				<% for(Libro i: carrello){ %>
					Libro= Nome: <i><%= i.getNome()%></i>, Autore: <i><%= i.getAutore() %></i>, Prezzo: <i><%= i.getPrice() %></i>
				<%} %>.
				<br>
				Il totale da pagare sarà di: <i><%=importo %></i> euro.
				</p>
				<center>
					<button name="registrazione" id="cambiamento">Acquista</button>
				</center>
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>
	
	<div class="container-fluid">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<h1 class="formacquisto">Svuota carrello</h1>
			<form action="#" method="post">
				<p class="descrizione"> Cliccando il pulsante 'svuota carrello' eliminerai tutti gli elementi dal tuo carrello<br>
				</p>
				<center>
					<button name="registrazione" id="cambiamento" class="svuotacarr">Svuota carrello</button>
				</center>
			</form>
		</div>
		<div class="col-md-2"></div>
	</div>
	
	<script type="text/javascript">
	
		$(document).on("click", ".elimina", function(){
			var idLibro= $(this).val();

			$.ajax({
				url : "EliminaLibroCarrello",
				dataType : "HTML",
				data : {
					"codlibro" : idLibro
				},
				success : function(result) {
					window.location.reload();
					alert("libro eliminato dal carrello");
				}
			});
		});
		
		$(document).on("click", ".svuotacarr", function(){

			$.ajax({
				url : "SvuotaCarrello",
				dataType : "HTML",
				data : {
				},
				success : function(result) {
					window.location.reload();
				}
			});
		});
	</script>

</body>
</html>