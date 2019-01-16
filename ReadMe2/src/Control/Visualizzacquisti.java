package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.QueryDB;
import Model.Acquisto;
import Model.Libro;
import Model.Richiesta;

/**
 * Servlet implementation class Visualizzacquisti
 */
@WebServlet("/Visualizzacquisti")
public class Visualizzacquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Visualizzacquisti() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Acquisto> acquisti= new ArrayList<>();
		ArrayList<Libro> libri= new ArrayList<>();
		ArrayList<Libro> libri_acquistati= new ArrayList<>();

		try {
			acquisti= QueryDB.tuttigliacquisti();
			System.out.println(acquisti.toString());
			libri= QueryDB.ritorna_libri();
			System.out.println(libri.toString());
			
			if (acquisti.toString().equals("[]")) {
				System.out.println("Nessun elemento presente nel Database, nessun acquisto effettuato");
			}else {
				for (Acquisto a: acquisti) {
					for (Libro l: libri) {
						if (a.getIsbn()==l.getCode()) {
							libri_acquistati.add(l);
						}
					}
				}
				System.out.println("Libri acquistati: " + libri_acquistati.toString());
				request.setAttribute("acquisti", libri_acquistati);
				request.getRequestDispatcher("viewAcquisti.jsp").forward(request, response);
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
