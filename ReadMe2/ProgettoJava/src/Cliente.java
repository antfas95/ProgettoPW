import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Un cliente presenta un nickName identificativo e una password per farsi identificare dal sistema.
 */
public class Cliente implements Serializable
{
	private String nome;
	private String password;
	private ArrayList<Biglietto> listaBigliettiPrenotati, listaBigliettiAcquistati;
	
	/**
	 * Costruttore della classe Cliente.
	 * @param nickName	Il nome di riferimento del cliente	
	 * @param password	La password identificativa del cliente
	 */
	public Cliente(String nome, String password)
	{
		this.nome = nome;
		this.password = password;
		
		listaBigliettiPrenotati = new ArrayList<>();
		listaBigliettiAcquistati = new ArrayList<>();
		
	}
	
	/**
	 * Ispeziona il nickName del cliente.
	 * @return Il nickName 
	 */
	public String getNome()
	{
		return nome;
	}
	
	/**
	 * Ispeziona la password del cliente.
	 * @return	La password
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * Ispeziona i biglietti prenotati dal cliente.
	 * @return	lista dei biglietti prenotati
	 */
	public ArrayList<Biglietto> getBigliettiPrenotatti()
	{
		return listaBigliettiPrenotati;
	}
	
	/**
	 * Ispeziona i biglietti acquistati dal clinte.
	 * @return	lista dei biglietti acquistati
	 */
	public ArrayList<Biglietto> getBigliettiAcquistati()
	{
		return listaBigliettiAcquistati;
	}
	
	/**
	 * Aggiunge alla lista dei biglietti prenotati un nuovo biglietto.
	 * @param statoPosto	indica lo stato del posto in tribuna(disponibile o meno)
	 * @param biglietto		biglietto da aggiungere alla lista dei prenotati
	 */
	public void prenotaBiglietto(Color statoPosto,Biglietto biglietto)
	{
		if(statoPosto.equals(Color.GREEN))
		{
			listaBigliettiPrenotati.add(biglietto);
		}
		else
		{
			throw new PostoIndisponibileException();
		}
	}
	
	/**
	 * Aggiunge alla lista dei biglietti acquistati un nuovo biglietto
	 * @param statoPosto	indica lo stato del posto in tribuna(disponibile o meno)
	 * @param biglietto		biglietto da aggiungere allalista degli acquisti
	 */
	public void AcquistaBiglietti(Color statoPosto,Biglietto biglietto)
	{
		if(statoPosto.equals(Color.GREEN))
		{
			listaBigliettiAcquistati.add(biglietto);
		}
		else
		{
			throw new PostoIndisponibileException();
		}
		
	}
	
	
	/**
	 * Rimuove dalla lista dei biglietti prenotati un biglietto.
	 * @param bigliettoDaEliminare biglietto da eliminare dai prenotati
	 */
	public void eliminaBigliettoDallePrenotazioni(Biglietto bigliettoDaEliminare)
	{
		listaBigliettiPrenotati.remove(bigliettoDaEliminare);
	}
	
}