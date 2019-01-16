package Control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase.QueryDB;
import Model.Libro;

/**
 * Servlet implementation class Ricerca
 */
@WebServlet("/Ricerca")
public class Ricerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ricerca() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oggetto_ricerca= request.getParameter("ricerca");
		ArrayList<Libro> libri_trovatiS= QueryDB.la_RicercaStringa(oggetto_ricerca);
		ArrayList<Libro> ricercati= new ArrayList<>();
		//ArrayList<Libro> libri_trovatiN=null;
		//boolean verifica= false;
		
		if(libri_trovatiS.toString().equals("[]")) {
			request.getRequestDispatcher("nessun_elemento.jsp").forward(request, response);
		}else {
			request.setAttribute("trovati", libri_trovatiS);
			request.getRequestDispatcher("risultato_ricerca.jsp").forward(request, response);
			
			//Pezzo di codice che mi avrebbe permesso di mantenere i libri che sono stati oggetto della ricerca dall'utente
			
			/*for (Libro i: libri_trovatiS) {
				ricercati.add(i);
			}
			System.out.println("Stampo Ricercati: " + ricercati.toString());
			HttpSession sessione= request.getSession();
			sessione.setAttribute("ricercati", ricercati);*/
		}
		
		/*
		//Controllo che il risultato ottenuto non sia dato da un array di libri vuoto
		if (!libri_trovatiS.toString().equals("[]")) {
			System.out.println(libri_trovatiS.toString());
		}else {
			libri_trovatiN= QueryDB.la_RicercaIntero(oggetto_ricerca);
			verifica= true;
		}
		
		if (verifica=true) {
			if (libri_trovatiN.toString().equals("[]")) {
				request.getRequestDispatcher("errorCode.jsp").forward(request, response);
			}else {
				for (Libro i: libri_trovatiN) {
					System.out.println(i.getNome());
				}
			}
		}
		*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
