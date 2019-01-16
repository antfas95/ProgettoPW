package Control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.QueryDB;
import Model.Richiesta;

/**
 * Servlet implementation class Visualizzalerichieste
 */
@WebServlet("/Visualizzalerichieste")
public class Visualizzalerichieste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Visualizzalerichieste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Richiesta> richieste= new ArrayList<>();
		
		try {
			richieste= QueryDB.tutteLeRichieste();
			System.out.println(richieste);
			request.setAttribute("richieste", richieste);
			request.getRequestDispatcher("viewRichieste.jsp").forward(request, response);
			
			if (richieste.toString()=="[]") {
				request.setAttribute("visibile", false);
				System.out.println(richieste);
			}else {
				request.setAttribute("visibile", true);
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
