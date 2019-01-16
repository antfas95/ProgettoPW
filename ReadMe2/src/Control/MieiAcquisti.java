package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.QueryDB;
import Model.Acquisto;
import Model.Libro;
import Model.Utente;

/**
 * Servlet implementation class MieiAcquisti
 */
@WebServlet("/MieiAcquisti")
public class MieiAcquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MieiAcquisti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail= request.getParameter("mail");
		ArrayList<Libro> acquistati = null;
		try {
			acquistati = QueryDB.my_Acquisti(mail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (acquistati.toString().equals("[]")) {
			request.getRequestDispatcher("nessun_elemento.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("risultato_acquisti.jsp").forward(request, response);
			request.setAttribute("acquistati", acquistati);
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
