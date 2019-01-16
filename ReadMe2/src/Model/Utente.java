package Model;

public class Utente {
	
	//Dichiarazione delle variabili di istanza
	private String nome;
	private String cognome;
	private String indirizzo;
	private String mail;
	private String password;
	private String domanda_sicurezza;
	private String risposta_sicurezza;
	
	public Utente(){
		super();
	}
	
	public Utente(String password, String domanda, String risposta) {
		this.password= password;
		this.domanda_sicurezza= domanda;
		this.risposta_sicurezza= risposta;
	}
	
	public Utente(String name, String surname, String address, String mailu, String pass, String domanda, String risposta){
		this.nome= name;
		this.cognome= surname;
		this.indirizzo= address;
		this.mail= mailu;
		this.password= pass;
		this.domanda_sicurezza= domanda;
		this.risposta_sicurezza= risposta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDomanda_sicurezza() {
		return domanda_sicurezza;
	}

	public void setDomanda_sicurezza(String domanda_sicurezza) {
		this.domanda_sicurezza = domanda_sicurezza;
	}

	public String getRisposta_sicurezza() {
		return risposta_sicurezza;
	}

	public void setRisposta_sicurezza(String risposta_sicurezza) {
		this.risposta_sicurezza = risposta_sicurezza;
	}

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + ", mail=" + mail
				+ ", password=" + password + ", domanda_sicurezza=" + domanda_sicurezza + ", risposta_sicurezza="
				+ risposta_sicurezza + "]";
	}
}