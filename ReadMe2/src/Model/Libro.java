package Model;

public class Libro {
	
	//Dichiarazione delle variabili di istanza
	private int code;
	private String nome;
	private String autore;
	private int price;
	private int copie;
	private String immagine;
	
	public Libro() {
		super();
	}
	
	public Libro (Libro l) {
		this.code= l.getCode();
		this.nome= l.getNome();
		this.autore= l.getAutore();
		this.price= l.getPrice();
		this.copie= l.getCopie();
		this.immagine= l.getImmagine();
	}
	
	public Libro (String nome, String autore, int prezzo, int num_copie, String path_image) {
		this.nome= nome;
		this.autore= autore;
		this.price= prezzo;
		this.copie= num_copie;
		this.immagine= path_image;
	}
	
	public Libro (int codice, String nome, String autore, int prezzo, int num_copie, String path_image) {
		this.code= codice;
		this.nome= nome;
		this.autore= autore;
		this.price= prezzo;
		this.copie= num_copie;
		this.immagine= path_image;
	}

	@Override
	public String toString() {
		return "Libro [nome=" + nome + ", autore=" + autore + ", price=" + price + ", copie=" + copie + ", immagine="
				+ immagine + "]";
	}

	public String getNome() {
		return nome;
	}
	
	public int getCode() {
		return code;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCode(int codice) {
		this.code = codice;
	}
	
	

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCopie() {
		return copie;
	}

	public void setCopie(int copie) {
		this.copie = copie;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	
	//Metodo che controlla se eliminare o meno il libro dal db per mancanza di copie
	public boolean controlla_eliminazione(int numero) {
		if (numero==0) {
			return true;
		}else {
			return false;
		}
	}
	
	//Metodo che decremente il numero delle copie
	public int decrementa_copie(int numero) {
		numero--;
		return numero;
	}
}