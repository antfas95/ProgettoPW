package Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.QueryDB;
import Model.Admin;

/**
 * Servlet implementation class NuovoAdmin
 */
@WebServlet("/NuovoAdmin")
public class NuovoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Admin admin;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Prendo il riferimento alle variabili che sono state inseriti all'interno della pagina jsp
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String indirizzo= request.getParameter("indirizzo");
		String mail= request.getParameter("postaelettronica");
		String pass= request.getParameter("password1");
		String pass1= request.getParameter("password2");
	
		
		if(pass.equals(pass1)) {
			//Le password coincidono si può procedere con l'inserimento del nuovo Admin
			admin= new Admin(nome, cognome, indirizzo, mail, pass);
			try {
				QueryDB.Aggiungi_nuovo_admin(admin);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			request.getRequestDispatcher("errorPass.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}