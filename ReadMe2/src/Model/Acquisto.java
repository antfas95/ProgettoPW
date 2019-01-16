package Model;

import java.sql.Date;

import com.mysql.fabric.xmlrpc.base.Data;

public class Acquisto {
	
	//Dichiarazione delle variabili private
	private int codice;
	private String utente_acquista;
	private int isbn;
	
	public Acquisto() {
		super();
	}
	
	public Acquisto(int code, String utente, int is) {
		this.codice= code;
		this.utente_acquista= utente;
		this.isbn= is;
	}

	@Override
	public String toString() {
		return "Acquisto [codice=" + codice + ", utente_acquista=" + utente_acquista + ", isbn=" + isbn + ", date=" + "]";
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getUtente_acquista() {
		return utente_acquista;
	}

	public void setUtente_acquista(String utente_acquista) {
		this.utente_acquista = utente_acquista;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
}