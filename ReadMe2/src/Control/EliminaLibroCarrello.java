package Control;

import java.io.IOException;
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
 * Servlet implementation class EliminaLibroCarrello
 */
@WebServlet("/EliminaLibroCarrello")
public class EliminaLibroCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaLibroCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Mi trovo nella servlet di eliminazione libro dal carrello");
		HttpSession session= request.getSession();
		int codLibro = Integer.parseInt(request.getParameter("codlibro"));
		System.out.println(codLibro);
		Libro x = QueryDB.selectLibroById(codLibro);
		System.out.println(x.toString());
		Utente u = (Utente) session.getAttribute("utente");
		QueryDB.elimina_libro_Carrello(u.getMail(), codLibro);
		ArrayList<Libro> carrello= (ArrayList<Libro>) session.getAttribute("carrello");
		System.out.println("Carrello prima: " + carrello.toString());
		carrello.remove(x);
		System.out.println("Carrello dopo: " + carrello.toString());
		session.setAttribute("carrello", carrello);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
