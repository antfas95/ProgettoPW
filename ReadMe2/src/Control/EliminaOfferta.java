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
 * Servlet implementation class EliminaOfferta
 */
@WebServlet("/EliminaOfferta")
public class EliminaOfferta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminaOfferta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cod= request.getParameter("code");
		System.out.println(cod);
		int codice= Integer.parseInt(cod);
		String nuovo= request.getParameter("prezzo");
		System.out.println(nuovo);
		int nuovo_prezzo= Integer.parseInt(nuovo);
		
		try {
			boolean valore= QueryDB.elimina_offerta(codice);
			boolean valore2= QueryDB.modifica_prezzo(codice, nuovo_prezzo);
			if (valore==true && valore2==true) {
				request.getRequestDispatcher("success_admin.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("ErrorCode.jsp").forward(request, response);
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
