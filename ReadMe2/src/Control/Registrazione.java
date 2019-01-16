package Control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DataBase.QueryDB;
import Model.Utente;

/**
 * Servlet implementation class Registrazione
 */
@WebServlet("/Registrazione")
public class Registrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Utente utente;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws ServletException 
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//Prelevo le informazione dalla form per poter poi effettuare la query che riguarda la registrazione
		String nome= request.getParameter("nome");
		System.out.println(nome);
		String cognome= request.getParameter("cognome");
		System.out.println(cognome);
		String indirizzo= request.getParameter("indirizzo");
		System.out.println(indirizzo);
		String mail= request.getParameter("postaelettronica");
		String password1= request.getParameter("password1");
		System.out.println(password1);
		String password2= request.getParameter("password2");
		System.out.println(password2);
		String domanda= request.getParameter("domande");
		System.out.println(domanda);
		String risposta= request.getParameter("risposta");
		System.out.println(risposta);
		if (password1.equals(password2)==true){
			System.out.println("Ok");
			//Dopo il controllo istanzio l'oggetto Utente per la registrazione
			utente= new Utente(nome, cognome, indirizzo, mail, password1, domanda, risposta);
			System.out.println(utente.toString());
			if(utente.getMail().equals("") || utente.getPassword().equals("") || utente.getRisposta_sicurezza().equals("") || utente.getIndirizzo().equals("")) {
				request.getRequestDispatcher("erroReg.jsp").forward(request, response);
			}else {
				try {
					boolean valore= QueryDB.addUser(utente);
					if (valore==true) {
						request.getRequestDispatcher("success_registrazione.jsp").forward(request, response);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}else{
			request.getRequestDispatcher("errorPass.jsp").forward(request, response);
			System.out.println("Le due password non coincidono");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
