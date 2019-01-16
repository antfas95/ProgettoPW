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
import Model.Acquisto;
import Model.Admin;
import Model.Libro;
import Model.Utente;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email= request.getParameter("username");
		String pass= request.getParameter("password");
		
		//Istanzio l'array nel quale inserisco tutti i libri che si trovano in offerta
		ArrayList<Libro> offerti= new ArrayList<>();
		ArrayList<Libro> acquistati= new ArrayList<>();
		ArrayList<Libro> carrello= new ArrayList<>();
		
		try {
			Utente u= QueryDB.effettua_login(email);
			Admin amministratore= QueryDB.entra_come_Admin(email);
			
			if (u!=null) {
				System.out.println("Sei un semplice cliente....");
				if (u.getPassword().equals(pass)) {
					HttpSession session= request.getSession();
					session.setAttribute("utente", u);
					System.out.println(u.toString());
					System.out.println("Mi connetto alla Home dell'utente");
					offerti= QueryDB.tutte_le_offerte();
					session.setAttribute("offerte", offerti);
					System.out.println(offerti.toString());
					System.out.println("Prendo i libri che sono stati acquistati dall'utente");
					acquistati= QueryDB.my_Acquisti(email);
					session.setAttribute("acquistati", acquistati);
					//Bisognerebbe memorizzare tutti i libri che l'utente ha nel suo carrello
					System.out.println("Prendo i libri che l'utente possiede all'interno del suo carrello");
					carrello= QueryDB.mio_carrello_libri(email);
					session.setAttribute("carrello", carrello);
					request.getRequestDispatcher("HomeUser.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("errorLog.jsp").forward(request, response);
				}
			}else if(amministratore!=null){
				System.out.println("Sei un amministratore.....");
				if (amministratore.getPassword().equals(pass)) {
					HttpSession session= request.getSession();
					session.setAttribute("admin", amministratore);
					System.out.println("Mi connetto alla Home dell'admin");
					request.getRequestDispatcher("HomeAdmin.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("errorLog.jsp").forward(request, response);
				}
			}else {
				request.getRequestDispatcher("errorLog.jsp").forward(request, response);
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
