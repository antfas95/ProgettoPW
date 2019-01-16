import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Uno stadio è costituito da un nome, dalla capienza e da un proprio incasso
 */
public class Stadio implements Serializable
{
	private String nomeStadio;
	private int postiASedere;
	private double incassoStadio;
	
	/**
	 * Costruttore della classe Stadio
	 * @param nomeStadio	nome dello stadio
	 * @param postiASedere	capienza dello stadio
	 */
	public Stadio(String nomeStadio, int postiASedere)
	{
		this.nomeStadio = nomeStadio;
		this.postiASedere = postiASedere;
		this.incassoStadio = 0;
	}
	
	/**
	 * Ispeziona il nome dello stadio.
	 * @return nomeStadio
	 */
	public String getNomeStadio()
	{
		return this.nomeStadio;
	}
	
	/**
	 * Ispeziona la capienza dello stadio.
	 * @return
	 */
	public int getPostiASedere()
	{
		return this.postiASedere;
	}
	
	
	/**
	 * Ispeziona l\'incasso dello stadio
	 * @return incasso totale
	 */
	public double getIncassoStadio()
	{
		return this.incassoStadio;
	}
	
	/**
	 * Aumenta la capienza dello stadio
	 * @param capienza
	 */
	public void aumentaCapienza(int capienza, ArrayList<Partita> listaPartite)
	{
		boolean done = false;
		
		for(Partita p : listaPartite)
		{
			if(p.getNomeStadio().equals(this.getNomeStadio()))
			{
				done = true;
			}
		}
		
		if(done)
		{
			throw new RuntimeException("la capienza non puo essere aggiornata");
		}
		else
		{
			this.postiASedere = postiASedere + capienza;
		}
		
	}
	
	
	/**
	 * Diminuisce la capienza dello stadio
	 * @param capienza
	 */
	public void diminuisciCapienza(int capienza, ArrayList<Partita> listaPartite)
	{
		boolean done = false;
		
		if(capienza > postiASedere)
		{
			throw new RuntimeException("la capienza non puo essere aggiornata: posti da eliminare maggiori di quelli esistenti");
		}
		else
		{
			for(Partita p : listaPartite)
			{
				if(p.getNomeStadio().equals(this.getNomeStadio()))
				{
					done = true;
				}
			}
			
			if(done)
			{
				throw new RuntimeException("la capienza non puo essere aggiornata: partita/e da giocare in corso");
			}
			else
			{
				this.postiASedere = postiASedere - capienza;
			}
		}
	}
		
	/**
	 * Aumenta l\'incasso dello stadio in base al biglietto acquistato
	 * @param prezzo del biglietto acquistato
	 */
	public void setIncassoStadio(double prezzoBigliettoAcquistato)
	{
		this.incassoStadio = incassoStadio + prezzoBigliettoAcquistato;
	}
	
	/**
	 * Verifica se la capienza di uno stadio se è maggiore, minore o uguale di un altro stadio
	 * @param stadio con ciu fare il confronto
	 * @return un valore intero compreso tra -1 e 1
	 */
	public int compareTo(Stadio stadio1)
	{
		if (postiASedere < stadio1.getPostiASedere())
		{
			return -1;
		}
		else if (postiASedere > stadio1.getPostiASedere())
		{
			return 1;
		}
		else
			return 0;
	}
	
	/**
	 * Sovrascrittura del metodo toString() della classe Object
	 */
	public String toString()
	{
		return "NomeStadio =  " + nomeStadio + "  CapienzaStadio= " + postiASedere;
	}
	
	
}