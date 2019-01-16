package DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import org.apache.tomcat.jni.User;
import org.eclipse.jdt.internal.compiler.parser.RecoveredStatement;

import Model.Acquisto;
import Model.Admin;
import Model.Libro;
import Model.Richiesta;
import Model.Utente;

public class QueryDB {

	//Query che permettono la gestione dell'utente
	private static String queryAddUtente= "INSERT INTO utente (mail, nome, cognome, indirizzo, password, domandasicurezza, risposta) VALUES (?,?,?,?,?,?,?);";
	private static String nuovocarrello= "INSERT INTO readme.carlibri2 (mailuser) VALUES (?);";
	private static String effettua_login_user= "SELECT * FROM readme.utente WHERE mail= (?);";
	private static String effettua_login_admin= "SELECT mail, password FROM readme.admin where mail=(?);";
	private static String aggiungi_nuovo_admin= "INSERT INTO readme.admin (mail, password, nome, cognome, indirizzo) VALUES (?,?,?,?,?);";
	private static String elimanzione_admin= "DELETE FROM readme.admin where mail=(?);";
	private static String aggiungi_nuovo_libro= "INSERT INTO readme.libro(nome, autore, prezzo, num_copie, pathImage) values (?,?,?,?,?);";
	private static String eliminazione_libro= "DELETE FROM readme.libro where ISBN=(?);";
	private static String codici_libri= "SELECT ISBN FROM readme.libro;";
	private static String mail_admin="SELECT mail FROM readme.admin;";
	private static String mail_users= "SELECT mail FROM readme.utente;";
	private static String modifica_prezzo_libro= "update readme.libro SET prezzo=(?) where ISBN=(?);";
	private static String modifica_numero_copie= "update readme.libro SET num_copie=(?) where ISBN=(?);";
	private static String ritorna_richieste= "SELECT * FROM readme.richiesta;";
	private static String ritorna_acquisti= "SELECT * FROM readme.acquisti;";
	private static String ritorna_libri= "SELECT * FROM readme.libro;";
	private static String elimina_utente= "DELETE FROM readme.utente where mail=(?);";
	private static String aggiungi_nuova_offerta= "INSERT INTO readme.offerte(isbn, prezzo_offerta) VALUES (?,?);";
	private static String elimina_offerta= "DELETE FROM readme.offerte WHERE isbn=(?);";
	private static String tutte_offerte= "SELECT isbn FROM readme.offerte;";
	private static String aggingi_Richiesta= "INSERT INTO readme.richiesta (nome, descrizione, mailuser) values (?,?,?);";
	private static String effettua_ricerca= "SELECT * FROM readme.libro WHERE ISBN=?||nome=?||autore=?||prezzo<=?;";
	private static String effettua_ricerca1= "SELECT * FROM readme.libro WHERE ISBN=?||prezzo<=?;";
	private static String tutti_acquisti="SELECT * FROM readme.acquisti WHERE mailutente=?;";
	private static String mio_carrello= "select libro.* FROM readme.libro, readme.carlibri2 where carlibri2.mailuser=? && carlibri2.idlibro=libro.ISBN;";
	private static String svuota_carrello= "DELETE FROM readme.carlibri2 where carlibri2.mailuser=? && carlibri2.idlibro=?;";
	private static String rimuovi_libro_carrello= "DELETE FROM readme.carlibri2 WHERE carlibri2.mailuser=? && carlibri2.idlibro=?;";
	private static String aggiungi_libro_carrello= "INSERT INTO readme.carlibri2 (importo, mailuser, idlibro) values (?,?,?);";
	private static String acquista_Prodotti= "INSERT INTO readme.acquisti (mailutente, isbnlibro) VALUES (?,?);";
	private static String decrementa_numero_copie= "update readme.libro SET num_copie=num_copie-1 where ISBN=(?);";
	private static String seleziona_libro_da_id = "SELECT * FROM readme.libro WHERE ISBN = ?";
	private static String ritorna_utenti_recupero= "SELECT password, domandasicurezza, risposta FROM readme.utente;";
	//Memorizzo uno statement utile per la connessione


	public synchronized static boolean addUser(Utente utente) throws SQLException{

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try{

			conn= Database.getConnection();

			ps= conn.prepareStatement(queryAddUtente);

			ps.setString(1, utente.getMail());
			ps.setString(2, utente.getNome());
			ps.setString(3, utente.getCognome());
			ps.setString(4, utente.getIndirizzo());
			ps.setString(5, utente.getPassword());
			ps.setString(6, utente.getDomanda_sicurezza());
			ps.setString(7, utente.getRisposta_sicurezza());

			//Istruzione che permette l'esecuzione della query
			ps.executeUpdate();

			//Effettuo il commit della connessione
			conn.commit();

			//Creo adesso il carrello del nuovo utente che è stato creato
			aggiungi_carrello(utente.getMail());


			System.out.println("Effettuo la connessione....");

			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}

	public synchronized static boolean aggiungi_carrello (String email) {

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try{

			conn= Database.getConnection();

			ps= conn.prepareStatement(nuovocarrello);

			ps.setString(1, email);

			//Istruzione che permette l'esecuzione della query
			ps.executeUpdate();

			//Effettuo il commit della connessione
			conn.commit();

			System.out.println("Effettuo la connessione....");

			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}

		return false;
	}

	public synchronized static Utente effettua_login(String mail_riferimento) throws SQLException {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		Utente utente= new Utente();

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(effettua_login_user);

			ps.setString(1, mail_riferimento);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				utente.setMail(rs.getString(1));
				utente.setNome(rs.getString(2));
				utente.setCognome(rs.getString(3));
				utente.setIndirizzo(rs.getString(4));
				utente.setPassword(rs.getString(5));
				utente.setDomanda_sicurezza(rs.getString(6));
				utente.setRisposta_sicurezza(rs.getString(7));
				System.out.println(utente.toString());
			}
		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
		if (utente.getMail()==null) {
			return null;
		}else {
			return utente;
		}
	}

	public synchronized static boolean Aggiungi_nuovo_admin (Admin admin) throws SQLException {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(aggiungi_nuovo_admin);

			ps.setString(1, admin.getEmail());
			ps.setString(2, admin.getPassword());
			ps.setString(3, admin.getName());
			ps.setString(4, admin.getCognome());
			ps.setString(5, admin.getIndirizzo());

			//Prendo i risultati dal DataBase
			ps.executeUpdate();

			conn.commit();

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
		return true;
	}

	public synchronized static ArrayList<String> ritorna_amministratori() throws SQLException{

		//Istanzio l'arrayList che permetterà il ritorno dei valori di tutti i codici
		ArrayList<String> amministratori= new ArrayList<>();

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(mail_admin);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				String code= rs.getString(1);
				amministratori.add(code);
			}

			conn.commit();

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}

		return amministratori;
	}


	public synchronized static boolean elimina_admin(String mail) throws SQLException {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		ArrayList<String> admin= new ArrayList<>();
		int verifica=0;

		admin= ritorna_amministratori();

		for(String i: admin) {
			if (i==mail) {
				verifica=1;
			}
		}

		try {

			if (verifica==1) {
				conn= Database.getConnection();

				ps= conn.prepareStatement(elimanzione_admin);

				ps.setString(1, mail);

				//Prendo i risultati dal DataBase
				ps.executeUpdate();

				conn.commit();

				return true;
			}else {
				return false;
			}

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
	}

	public synchronized static boolean elimina_libro(int codice) throws SQLException {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		ArrayList<Integer> interi= new ArrayList<>();
		int verifica=0;

		interi= ritorna_codice_libri();

		for(Integer i: interi) {
			if (i==codice) {
				verifica=1;
			}
		}

		try {

			if (verifica==1) {
				conn= Database.getConnection();

				ps= conn.prepareStatement(eliminazione_libro);

				ps.setInt(1, codice);

				//Prendo i risultati dal DataBase
				ps.executeUpdate();

				conn.commit();

				return true;
			}else {
				return false;
			}

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
	}

	public synchronized static boolean modifica_prezzo(int codice, int prezzo) throws SQLException {

		//Istanzio l'arrayList che permetterà il ritorno dei valori di tutti i codici
		ArrayList<Integer> codici= new ArrayList<>();
		int verifica=0;

		codici= ritorna_codice_libri();

		for (Integer i: codici) {
			if (i==codice) {
				verifica=1;
			}
		}

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			if (verifica==1) {
				conn= Database.getConnection();

				ps= conn.prepareStatement(modifica_prezzo_libro);

				ps.setInt(1, prezzo);
				ps.setInt(2, codice);

				//Prendo i risultati dal DataBase
				ps.executeUpdate();

				conn.commit();

				return true;
			}else {
				return false;
			}

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
	}

	public synchronized static boolean modifica_copie(int codice, int copie) throws SQLException {

		//Istanzio l'arrayList che permetterà il ritorno dei valori di tutti i codici
		ArrayList<Integer> codici= new ArrayList<>();
		int verifica=0;

		codici= ritorna_codice_libri();

		for (Integer i: codici) {
			if (i==codice) {
				verifica=1;
			}
		}

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			if (verifica==1) {
				conn= Database.getConnection();

				ps= conn.prepareStatement(modifica_numero_copie);

				ps.setInt(1, copie);
				ps.setInt(2, codice);

				//Prendo i risultati dal DataBase
				ps.executeUpdate();

				conn.commit();

				return true;
			}else {
				return false;
			}

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
	}

	public synchronized static boolean decrementa_Copie_libro(int codice) throws SQLException {

		//Istanzio l'arrayList che permetterà il ritorno dei valori di tutti i codici
		ArrayList<Integer> codici= new ArrayList<>();
		int verifica=0;

		codici= ritorna_codice_libri();

		for (Integer i: codici) {
			if (i==codice) {
				verifica=1;
			}
		}

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			if (verifica==1) {
				conn= Database.getConnection();

				ps= conn.prepareStatement(decrementa_numero_copie);

				ps.setInt(1, codice);

				//Prendo i risultati dal DataBase
				ps.executeUpdate();

				return true;
			}else {
				return false;
			}

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
	}

	public synchronized static ArrayList<Integer> ritorna_codice_libri() throws SQLException{

		//Istanzio l'arrayList che permetterà il ritorno dei valori di tutti i codici
		ArrayList<Integer> codici= new ArrayList<>();

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(codici_libri);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				int code= rs.getInt(1);
				codici.add(code);
			}

			conn.commit();

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}

		return codici;
	}

	public synchronized static boolean aggiungi_nuovo_libro(Libro libro) throws SQLException {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(aggiungi_nuovo_libro);

			ps.setString(1, libro.getNome());
			ps.setString(2, libro.getAutore());
			ps.setInt(3, libro.getPrice());
			ps.setInt(4, libro.getCopie());
			ps.setString(5, libro.getImmagine());

			//Prendo i risultati dal DataBase
			ps.executeUpdate();

			conn.commit();

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
		return true;
	}

	public synchronized static Admin entra_come_Admin(String mail_riferimento) throws SQLException {

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		Admin amministratore= new Admin();

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(effettua_login_admin);

			ps.setString(1, mail_riferimento);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				amministratore.setEmail(rs.getString(1));
				amministratore.setPassword(rs.getString(2));
				System.out.println(amministratore.toString());
			}
		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
		if (amministratore.getEmail()==null) {
			return null;
		}else {
			return amministratore;
		}
	}

	public synchronized static ArrayList<Richiesta> tutteLeRichieste() throws SQLException{

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		ArrayList<Richiesta> richieste= new ArrayList<>();
		Richiesta richiesta;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(ritorna_richieste);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				richiesta= new Richiesta();
				richiesta.setIdentificativo(rs.getInt(1));
				richiesta.setName(rs.getString(2));
				richiesta.setDescrizione(rs.getString(3));
				richiesta.setReferente(rs.getString(4));
				richieste.add(richiesta);
			}
		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
		return richieste;
	}

	public synchronized static ArrayList<Acquisto> tuttigliacquisti() throws SQLException{

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		ArrayList<Acquisto> acquisti= new ArrayList<>();
		Acquisto acquisto;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(ritorna_acquisti);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				acquisto= new Acquisto();
				acquisto.setCodice(rs.getInt(1));
				acquisto.setUtente_acquista(rs.getString(2));
				acquisto.setIsbn(rs.getInt(3));
				acquisti.add(acquisto);
			}
		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
		return acquisti;
	}

	public synchronized static boolean elima_Utente(String mail) throws SQLException {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		ArrayList<String> utenti= new ArrayList<>();
		System.out.println(utenti);
		int verifica=0;

		utenti= ritorna_amministratori();

		for(String i: utenti) {
			if (i==mail) {
				verifica=1;
				System.out.println(verifica);
			}
		}

		try {

			if (verifica==1) {
				conn= Database.getConnection();

				ps= conn.prepareStatement(elimina_utente);

				ps.setString(1, mail);

				//Prendo i risultati dal DataBase
				ps.executeUpdate();

				conn.commit();

				return true;
			}else {
				return false;
			}

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}

	}

	public synchronized static ArrayList<String> ritorna_utenti() throws SQLException{

		//Istanzio l'arrayList che permetterà il ritorno dei valori di tutti i codici
		ArrayList<String> utenti= new ArrayList<>();

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(mail_users);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				String code= rs.getString(1);
				utenti.add(code);
				System.out.println(utenti);
			}

			conn.commit();

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}

		return utenti;
	}

	public synchronized static ArrayList<Libro> ritorna_libri() throws SQLException{

		//Istanzio l'arrayList che conterrà tutti i libri presenti nel database
		ArrayList<Libro> libreria= new ArrayList<>();

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		Libro libro= null;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(ritorna_libri);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			while(rs.next()) {
				libro= new Libro();
				libro.setCode(rs.getInt(1));
				libro.setNome(rs.getString(2));
				libro.setAutore(rs.getString(3));
				libro.setPrice(rs.getInt(4));
				libro.setCopie(rs.getInt(5));
				libro.setImmagine(rs.getString(6));
				libreria.add(libro);
				System.out.println(libro.toString());
			}

			conn.commit();

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}

		}
		return libreria;
	}

	public synchronized static boolean add_offerte(int code, int price) throws SQLException {

		//Prendo il riferimento dei libri che sono presenti all'interno della libreria
		ArrayList<Libro> libreria= ritorna_libri();

		boolean prova= false;

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		//Prendo il riferimento del libro sul quale apportare l'offerta
		Libro libro= null;

		if (libreria.toString().equals("[]")) {
			return false;
		}else {
			for(Libro l: libreria) {
				if (l.getCode()==code) {
					libro= new Libro(l);
					System.out.println("Il libro è stato trovato nel DataBase");
					System.out.println(libro.toString());
					prova= true;
					break;
				}
			}
		}

		System.out.println("Libro trovato: " + libro.toString());

		if (prova=false) {
			System.out.println("Ciaoo");
			return false;
		}else {
			try{

				conn= Database.getConnection();

				ps= conn.prepareStatement(aggiungi_nuova_offerta);

				ps.setInt(1, code);
				ps.setInt(2, price);

				//Istruzione che permette l'esecuzione della query
				ps.executeUpdate();

				//Effettuo il commit della connessione
				conn.commit();

				//Creo adesso il carrello del nuovo utente che è stato creato
				modifica_prezzo(code, price);


				System.out.println("Effettuo la connessione....");

				ps.close();

			}catch(SQLException e){
				e.printStackTrace();
			}
			return true;
		}
	}

	public synchronized static boolean elimina_offerta(int codice) {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try{

			conn= Database.getConnection();

			ps= conn.prepareStatement(elimina_offerta);

			ps.setInt(1, codice);

			//Istruzione che permette l'esecuzione della query
			ps.executeUpdate();

			//Effettuo il commit della connessione
			conn.commit();

			System.out.println("Effettuo la connessione....");

			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}

	public synchronized static ArrayList<Libro> tutte_le_offerte() {

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		//Creo il riferimento dell'arrayList che bisogna ritornare
		ArrayList<Integer> offerte= new ArrayList<>();
		ArrayList<Libro> offerti= new ArrayList<>();
		Libro da_aggiungere= null;

		try{

			conn= Database.getConnection();

			ps= conn.prepareStatement(tutte_offerte);

			//Istruzione che permette l'esecuzione della query
			ResultSet rs= ps.executeQuery();

			while(rs.next()) {
				int codice= rs.getInt(1);
				offerte.add(codice);
			}

			ArrayList<Libro> libreria= new ArrayList<>();
			libreria= ritorna_libri();

			for(Libro l: libreria) {
				for(Integer i: offerte) {
					if(l.getCode()==i) {
						da_aggiungere= new Libro(l);
						offerti.add(da_aggiungere);
						System.out.println("Libro trovato: " + da_aggiungere.toString());
						break;
					}
				}
			}

			//Effettuo il commit della connessione
			conn.commit();

			System.out.println("Effettuo la connessione....");

			ps.close();

		}catch(SQLException e){
			e.printStackTrace();
		}

		return offerti;
	}

	public synchronized static boolean add_Request(String nome, String description, String mail) {

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(aggingi_Richiesta);
			ps.setString(1, nome);
			ps.setString(2, description);
			ps.setString(3, mail);

			ps.executeUpdate();

			//Effettuo il commit della connessione
			conn.commit();

			System.out.println("Effettuo la connessione....");

			ps.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	public synchronized static ArrayList<Libro> la_RicercaStringa(String stringa){

		//Creo il riferimento all'array di libri che deve essere generato per poi essere ritornato
		ArrayList<Libro> ricercati= new ArrayList<>();
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		//Riferimento ad un libro
		Libro libro= null;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(effettua_ricerca);

			ps.setString(1, stringa);
			ps.setString(2, stringa);
			ps.setString(3, stringa);
			ps.setString(4, stringa);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			while(rs.next()) {
				libro= new Libro();
				libro.setCode(rs.getInt(1));
				libro.setNome(rs.getString(2));
				libro.setAutore(rs.getString(3));
				libro.setPrice(rs.getInt(4));
				libro.setCopie(rs.getInt(5));
				libro.setImmagine(rs.getString(6));
				ricercati.add(libro);
				System.out.println(libro.toString());
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ricercati;
	}

	public synchronized static ArrayList<Libro> la_RicercaIntero(String stringa){

		//Creo il riferimento all'array di libri che deve essere generato per poi essere ritornato
		ArrayList<Libro> ricercati= new ArrayList<>();
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		//Effettuo la trasformazione della stringa in un valore intero
		int numero= Integer.parseInt(stringa);

		//Riferimento ad un libro
		Libro libro= null;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(effettua_ricerca1);

			ps.setInt(1, numero);
			ps.setInt(2, numero);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			while(rs.next()) {
				libro= new Libro();
				libro.setCode(rs.getInt(1));
				libro.setNome(rs.getString(2));
				libro.setAutore(rs.getString(3));
				libro.setPrice(rs.getInt(4));
				libro.setCopie(rs.getInt(5));
				libro.setImmagine(rs.getString(6));
				ricercati.add(libro);
				System.out.println(libro.toString());
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ricercati;
	}

	public synchronized static ArrayList<Libro> my_Acquisti(String mail) throws SQLException {

		//Istanzio l'arrayList che dovrà essere di ritorno dalla chiamata del metodo
		ArrayList<Acquisto> acquisti= new ArrayList<Acquisto>();
		ArrayList<Libro> acquistati= new ArrayList<Libro>();
		ArrayList<Libro> presenti= new ArrayList<Libro>();
 
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		//Riferimento ad un Acquisto
		Acquisto acquisto= null;
		presenti= ritorna_libri();

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(tutti_acquisti);

			ps.setString(1, mail);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			while(rs.next()) {
				acquisto= new Acquisto();
				acquisto.setCodice(rs.getInt(1));
				acquisto.setUtente_acquista(rs.getString(2));
				acquisto.setIsbn(rs.getInt(3));
				acquisti.add(acquisto);
			}
			
			for (Acquisto i: acquisti) {
				for (Libro l: presenti) {
					if (i.getIsbn()==l.getCode()&&i.getUtente_acquista().equals(mail)) {
						acquistati.add(l);
					}
				}
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return acquistati;
	}

	public synchronized static ArrayList<Libro> mio_carrello_libri(String mail){

		//Creo il riferimento dell'ArrayList che dovrà essere ritornato dal sistema
		ArrayList<Libro> carrello= new ArrayList<Libro>();

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		//Riferimento ad un Libro
		Libro libro= null;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(mio_carrello);

			ps.setString(1, mail);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			while(rs.next()) {
				libro= new Libro();
				libro.setCode(rs.getInt(1));
				libro.setNome(rs.getString(2));
				libro.setAutore(rs.getString(3));
				libro.setPrice(rs.getInt(4));
				libro.setCopie(rs.getInt(5));
				libro.setImmagine(rs.getString(6));
				carrello.add(libro);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return carrello;
	}

	public synchronized static boolean svuota_Carrello(String mail, ArrayList<Libro> carrello) {

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(svuota_carrello);

			for(Libro i: carrello) {
				ps.setString(1, mail);
				ps.setInt(2, i.getCode());

				//Prendo i risultati dal DataBase
				ps.executeUpdate();
			}



			ps.setString(1, mail);

			//Prendo i risultati dal DataBase
			ps.executeUpdate();

			conn.commit();

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	public synchronized static boolean elimina_libro_Carrello(String mail, int code) {

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(rimuovi_libro_carrello);

			ps.setString(1, mail);
			ps.setInt(2, code);

			//Prendo i risultati dal DataBase
			ps.executeUpdate();
			
			conn.commit();

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	public synchronized static boolean aggiungi_Libro_Carrello(int importo, String mail, int idLibro) {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(aggiungi_libro_carrello);

			ps.setInt(1, importo);
			ps.setString(2, mail);
			ps.setInt(3, idLibro);

			//Prendo i risultati dal DataBase
			ps.executeUpdate();

			conn.commit();

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public synchronized static boolean acquista_Prodotti_carrello(ArrayList<Libro> carrello, String mail) {

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;
		
		boolean ritorno= true;

		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(acquista_Prodotti);
			
			ps.setString(1, mail);
			System.out.println(mail);
			
			for(Libro i: carrello) {
				ps.setInt(2, i.getCode());
				System.out.println(i.getCode());
				
				if(i.getCopie()==0) {
					ritorno= false;
				}
				//Esecuzione della query per un determinato libro dal carrello
				ps.executeUpdate();
			}
			
			conn.commit();
			
			for(Libro i: carrello) {
				decrementa_Copie_libro(i.getCode());
			}

			System.out.println("Alla fine svuoto il carrello");
			svuota_Carrello(mail, carrello);

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ritorno;
	}


	public static Libro selectLibroById(int idLibro) {
		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;
		Libro libro = null;
		try {
			conn= Database.getConnection();

			ps= conn.prepareStatement(seleziona_libro_da_id);

			ps.setInt(1, idLibro);

			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				//libro = new Libro(rs.getInt("ISBN"), rs.getString("nome"), rs.getString("autore"), rs.getInt("prezzo"), rs.getInt("num_copie"), rs.getString("pathImage"));
				libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6));
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		return libro;

	}

	public static ArrayList<Utente> ritorna_Utenti_particolari() throws SQLException{

		//ArrayList di ritorno
		ArrayList<Utente> utenti= new ArrayList<Utente>();

		//istanzio la connessione
		Connection conn= null;
		PreparedStatement ps= null;
		
		Utente utente= null;

		try {

			conn= Database.getConnection();

			ps= conn.prepareStatement(ritorna_utenti_recupero);

			//Prendo i risultati dal DataBase
			ResultSet rs= ps.executeQuery();

			conn.commit();

			while(rs.next()) {
				String pass= rs.getString(1);
				String domanda= rs.getString(2);
				String risposta= rs.getString(3);
				utente= new Utente(pass, domanda, risposta);
				utenti.add(utente);
				System.out.println(utenti);
			}

			conn.commit();

		}finally {

			try {

				if (ps != null)

					ps.close();

			} finally {

				Database.releaseConnection(conn);

			}
		}
		return utenti;
	}
}