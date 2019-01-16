package Model;

public class Admin {
	
	//Dichiarazione delle variabili di istanza della classe
	private String nome;
	private String cognome;
	private String indirizzo;
	private String email;
	private String password;
	
	//Costruttore che permette di effettuare i set sulle sue istanze
	public Admin() {
		super();
	}
	
	//Secondo costruttore di riferimento
	public Admin(String mail, String pass) {
		this.email= email;
		this.password= pass;
	}
	
	public Admin (String name, String surname, String address, String mail, String pass) {
		this.nome= name;
		this.cognome= surname;
		this.indirizzo= address;
		this.email= mail;
		this.password= pass;
	}

	@Override
	public String toString() {
		return "Admin [email=" + email + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public String getIndirizzo() {
		return indirizzo;
	}
	
	public void setName(String name) {
		this.nome = name;
	}
	
	public void setCognome(String cognoem) {
		this.cognome = cognoem;
	}
	
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	
}
