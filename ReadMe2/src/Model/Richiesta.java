package Model;

public class Richiesta {
	
	//Dichiarazione delle variabili di istanza
	private int identificativo;
	private String name;
	private String descrizione;
	private String referente;

	public Richiesta() {
		super();
	}
	
	public Richiesta(int id, String nome, String description, String referenza) {
		this.identificativo= id;
		this.name= nome;
		this.descrizione= description;
		this.referente= referenza;
	}
	
	public Richiesta(String nome, String description, String referenza) {
		this.name= nome;
		this.descrizione= description;
		this.referente= referenza;
	}

	@Override
	public String toString() {
		return "Richiesta [identificativo=" + identificativo + ", name=" + name + ", descrizione=" + descrizione
				+ ", referente=" + referente + "]";
	}

	public int getIdentificativo() {
		return identificativo;
	}

	public void setIdentificativo(int identificativo) {
		this.identificativo = identificativo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getReferente() {
		return referente;
	}

	public void setReferente(String referente) {
		this.referente = referente;
	}
	
}