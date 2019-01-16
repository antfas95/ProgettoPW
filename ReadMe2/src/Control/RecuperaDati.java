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
import Model.Utente;

/**
 * Servlet implementation class RecuperaDati
 */
@WebServlet("/RecuperaDati")
public class RecuperaDati extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecuperaDati() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Dati per il recupero della password
		String username= request.getParameter("username");
		//Dati per il recupero della password
		System.out.println(username);
		String domanda_sicurezza= request.getParameter("domande");
		String risposta= request.getParameter("risposta");
		String pass= request.getParameter("password");
		
		boolean verifica= false;
		
		if (username.equals("")) {
			if (domanda_sicurezza.equals("") || risposta.equals("") || pass.equals("")) {
				request.getRequestDispatcher("errorReg.jsp").forward(request, response);
			}else {
				System.out.println("Vuoi recuperare l'username");
				try {
					ArrayList<Utente> utenti_recupero= QueryDB.ritorna_Utenti_particolari();
					for(Utente u: utenti_recupero) {
						if(u.getPassword().equals(pass)&&u.getDomanda_sicurezza().equals(domanda_sicurezza)&&u.getRisposta_sicurezza().equals(risposta)) {
							request.getRequestDispatcher("recuperoUsername.jsp").forward(request, response);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else {
			//Prendo il riferimento all'arrayList nel caso di recupero password
			try {
				ArrayList<String> mail= QueryDB.ritorna_utenti();
				for (String i: mail) {
					if (i.equals(username)) {
						verifica= true;
						break;
					}
				}
				if(verifica==true) {
					request.getRequestDispatcher("recuperoPassword.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("errorCode1.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
