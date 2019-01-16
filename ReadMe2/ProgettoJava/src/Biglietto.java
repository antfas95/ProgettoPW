import java.io.Serializable;


/**
 * Un biglietto ha un codice, una partita a cui fa riferimento e un numero per il posto in Tribuna.
 */
public class Biglietto implements Serializable
{
	private Partita partita;
	private int posto, codice;
	private static int incrementaBiglietto = 32;
	
	
	/**
	 * Costruttore della classe Biglietto
	 * @param partita	partita a cui riferire il biglietto
	 * @param postoTribuna	posto in tribuna a cui si riferisce il biglietto 
	 */
	public Biglietto(Partita partita, int riga)
	{
		//Variabile che incrementa il codice del biglietto in modo automatico
		this.incrementaBiglietto = incrementaBiglietto +1;
		
		this.codice = incrementaBiglietto;
		this.partita = partita;
		this.posto = riga;
	}
	
	/**
	 * Ispeziona il codice del biglietto.
	 * @return il codice del biglietto
	 */
	public int getCodice()
	{
		return this.codice;
	}
	
	
	/**
	 * Ispeziona la partita associata al biglietto
	 * @return l'oggetto partita
	 */
	public Partita getPartita()
	{
		return this.partita;
	}
	
	
	/**
	 * Ispeziona il posto in tribuna
	 * @return	posto in tribuna
	 */
	public int getRiga()
	{
		return this.posto;
	}
	
	
	/**
	 * Sovrascrive il metodo toString() della classe Object
	 */
	public String toString()
	{
		return "CodiceBiglietto =    " + codice + "Partita = " + partita.toString() + "     Prezzo Scontato = "+ partita.getPrezzoBigliettoScontato() + "     %Sconto = " + partita.scontoMigliore() +"    Posto: " + posto; 
	}
}