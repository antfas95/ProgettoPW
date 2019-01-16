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
			<td><button class="btn btn-mini btn-success aggiungi" value="<%= i.getCode() %>">Aggiungi</button></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>