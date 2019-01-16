package DataBase;

import java.sql.SQLException;

import Control.Registrazione;
import Model.Utente;

public class testMetodiDb {

	public static void main(String[] args) throws SQLException {
		Utente utente = new Utente();
		utente.setCognome("fdfd");
		utente.setDomanda_sicurezza("dsfdsfds");
		utente.setIndirizzo("dfdsfds");
		utente.setMail("ejeiejieje");
		utente.setNome("dsfdsfds");
		utente.setPassword("dsfdsfds");
		utente.setRisposta_sicurezza("dsfdsfsd");
		//Registrazione reg = new Registrazione();
		QueryDB.addUser(utente);
	}
}