package Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.QueryDB;

/**
 * Servlet implementation class ModificaCopie
 */
@WebServlet("/ModificaCopie")
public class ModificaCopie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaCopie() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Prendo i riferimenti dalla pagina JSP
		String codice= request.getParameter("codice");
		int code= Integer.parseInt(codice);
		String copie= request.getParameter("copie");
		int nuove_copie= Integer.parseInt(copie);

		try {
			boolean risultato= QueryDB.modifica_copie(code, nuove_copie);

			if (risultato==true) {
				System.out.println("Il libro con codice: " +code+ " è stato trovato." );
				request.getRequestDispatcher("success_admin.jsp").forward(request, response);
			}else {
				System.out.println("Libro non trovato");
				request.getRequestDispatcher("errorCode.jsp").forward(request, response);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
}

}
