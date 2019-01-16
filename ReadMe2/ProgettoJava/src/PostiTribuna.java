import java.awt.Color;
import java.io.Serializable;

/**
 * Indica lo stato del posto in tribuna[disponibile(colore verde), prenotato(colore giallo), acquistato(colore rosso)]
 */
public class PostiTribuna implements Serializable
{
	//Variabili di istanza
	private Color stato;
	
	/**
	 * Costruttore della classe PostiTribuna settando lo stato del posto a disponibile.
	 */
	public PostiTribuna()
	{
		this.stato = Color.GREEN;
	}
	
	/**
	 * Setta lo stato del posto in tribuna ad acquistato
	 */
	public void setAcquistato()
	{
		this.stato = Color.RED;
	}
	
	/**
	 * Setta lo stato del posto in tribuna a prenotato
	 */
	public void setPrenotato()
	{
		this.stato = Color.YELLOW;
	}
	
	/**
	 * Setta lo stato del posto in tribna a disponibile
	 */
	public void setDisponibile()
	{
		this.stato = Color.GREEN;
	}
	
	/**
	 * Ispeziona lo stato del posto in tribuna
	 * @return stato del posto idicato da un colore
	 */
	public Color getStatoPosto()
	{
		return this.stato;
	}
	
	
}