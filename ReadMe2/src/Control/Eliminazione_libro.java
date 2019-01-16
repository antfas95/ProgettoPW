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
 * Servlet implementation class Eliminazione_libro
 */
@WebServlet("/Eliminazione_libro")
public class Eliminazione_libro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eliminazione_libro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String code= request.getParameter("codice");
		int codice= Integer.parseInt(code);
		
		try {
			boolean risultato= QueryDB.elimina_libro(codice);
			
			if (risultato==true) {
				System.out.println("Query effettuata con successo");
				request.getRequestDispatcher("success_admin.jsp").forward(request, response);
			}else {
				System.out.println("Non era presente nel DataBase un libro con quel codice");
				//Possibilità di aggiungere una pagina di errore (libro non esistente)
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
