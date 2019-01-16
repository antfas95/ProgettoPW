package Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.QueryDB;
import Model.Utente;

/**
 * Servlet implementation class Aggiungi_richiesta
 */
@WebServlet("/Aggiungi_richiesta")
public class Aggiungi_richiesta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Aggiungi_richiesta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Prendi i parametri dalla pagina JSP
		String nome= request.getParameter("nome");
		System.out.println(nome);
		String descrizione= request.getParameter("descrizione");
		System.out.println(descrizione);
		HttpSession session= request.getSession();
		Utente utente= (Utente) session.getAttribute("utente");
		System.out.println(utente.toString());
		String mail= utente.getMail();
		boolean ritorno= QueryDB.add_Request(nome, descrizione, mail);
		if(ritorno==true) {
			request.getRequestDispatcher("HomeUser.jsp").forward(request, response);
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
