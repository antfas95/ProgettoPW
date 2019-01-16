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
 * Servlet implementation class SvuotaCarrello
 */
@WebServlet("/SvuotaCarrello")
public class SvuotaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SvuotaCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Mi trovo nella servlet");
		HttpSession session= request.getSession();
		ArrayList<Libro> carrello= (ArrayList<Libro>) session.getAttribute("carrello");
		System.out.println(carrello.toString());
		Utente u= (Utente) session.getAttribute("utente");
		System.out.println(u.toString());
		String mail= u.getMail();
		System.out.println(mail);
		boolean ritorno= QueryDB.svuota_Carrello(mail, carrello);
		if (ritorno==true) {
			System.out.println("Operazione avvenuta con successo");
			carrello= QueryDB.mio_carrello_libri(mail);
			session.setAttribute("carrello", carrello);
			System.out.println(carrello.toString());
			ArrayList<Libro> carrello1= (ArrayList<Libro>) session.getAttribute("carrello");
			System.out.println(carrello1);
		}else {
			System.out.println("Operazione non riuscita");
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
