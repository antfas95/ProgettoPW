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
 * Servlet implementation class AggiuntaCarrello
 */
@WebServlet("/AggiuntaCarrello")
public class AggiuntaCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiuntaCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		int codLibro = Integer.parseInt(request.getParameter("codlibro"));
		Libro x = QueryDB.selectLibroById(codLibro);
		Utente u = (Utente) session.getAttribute("utente");
		QueryDB.aggiungi_Libro_Carrello(x.getPrice(), u.getMail(), x.getCode());
		ArrayList<Libro> carrello= (ArrayList<Libro>) session.getAttribute("carrello");
		carrello.add(x);
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
