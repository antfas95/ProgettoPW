<%@page import="Model.Acquisto"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Acquisto> acquisti = (ArrayList<Acquisto>) request.getAttribute("acquistati");
%>


<table class="table table-striped">
	<thead class="thead-inverse">
		<tr>
			<th>Codice</th>
			<th>Codice Libro</th>
			<th>Data</th>
		</tr>
	</thead>
	<tbody>
		<%
			for (Acquisto i : acquisti) {
		%>
		<tr>
			<td><%=i.getCodice()%></td>
			<td><%=i.getIsbn()%></td>
			<td><%=i.getDate()%></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>