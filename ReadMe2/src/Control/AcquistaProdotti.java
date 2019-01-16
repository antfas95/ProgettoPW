package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.QueryDB;
import Model.Libro;
import Model.Utente;

/**
 * Servlet implementation class AcquistaProdotti
 */
@WebServlet("/AcquistaProdotti")
public class AcquistaProdotti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcquistaProdotti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Mi trovo nella servlet degli acquisti");
		HttpSession session= request.getSession();
		Utente u= (Utente) session.getAttribute("utente");
		String mail= u.getMail();
		System.out.println(mail);
		ArrayList<Libro> carrello= (ArrayList<Libro>) session.getAttribute("carrello");
		System.out.println(carrello.toString());
		System.out.println("InVOco QUERYDB");
		boolean risultato= QueryDB.acquista_Prodotti_carrello(carrello, mail);
		if (risultato==true) {
			System.out.println("Aggiorno nella sessione gli acquisti che sono stati fatti...");
			ArrayList<Libro> acquistati= (ArrayList<Libro>) session.getAttribute("acquistati");
			try {
				System.out.println(acquistati.toString());
				acquistati= QueryDB.my_Acquisti(mail);
				System.out.println(acquistati.toString());
				session.setAttribute("acquistati", acquistati);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Operazione avvenuta con successo");
			boolean ritorno= QueryDB.svuota_Carrello(mail, carrello);
			
			request.getRequestDispatcher("miei_acquisti.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("errorCopie.jsp").forward(request, response);
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
