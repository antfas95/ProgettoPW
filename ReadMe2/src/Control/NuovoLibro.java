package Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.QueryDB;
import Model.Libro;

/**
 * Servlet implementation class NuovoLibro
 */
@WebServlet("/NuovoLibro")
public class NuovoLibro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NuovoLibro() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nome= request.getParameter("nome");
		String autore= request.getParameter("autore");
		String prezzo= request.getParameter("prezzo");
		int price= Integer.parseInt(prezzo);
		String c= request.getParameter("copie");
		int copie= Integer.parseInt(c);
		String immagine= request.getParameter("immagine");
		
		Libro libro= new Libro(nome, autore, price, copie, immagine);
		
		try {
			boolean valore= QueryDB.aggiungi_nuovo_libro(libro);
			if (valore==true) {
				request.getRequestDispatcher("success_admin.jsp").forward(request, response);
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
