import java.awt.Button;
import java.awt.Color;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;



/**
 * Un partita presenta un codice identificativo, una squadra che gioca in casa , una squadra ospite
 * una data, un orario di inizio partita, uno stadio in cui è disputata la partita e un prezzo.
 */
public class Partita implements Serializable
{
	private String squadraCasa, squadraOspite;
	private double prezzoBiglietto, percentualeSconto1, percentualeSconto2, percentualeSconto3;
	private int codiceP, ora, mese, anno, minuti;
	private Stadio stadio;
	private GregorianCalendar data;
	private PostiTribuna[] vettorePosti;

	/**
	 * Costruttore della classe Partita
	 * @param codiceP	codice identificatio della partita
	 * @param squadraCasa	squadra che gioca in casa
	 * @param squadraOspite	squadra che è ospistata
	 * @param giorno	giorno dell\'incontro
	 * @param mese	  mese dell\'incontro		
	 * @param anno	  anno dell'incontro
	 * @param ora	  ora inizio partita
	 * @param min  	  minuti inizio partita
	 * @param stadio  stadio in cui è disputata la partita
	 * @param prezzo  prezzo da pagare per accedere alla partita
	 */
	public Partita(int codiceP, String squadraCasa, String squadraOspite,int giorno, int mese,int anno, int ora, int min, Stadio stadio, double prezzo)
	{
		this.codiceP = codiceP;
		this.squadraCasa = squadraCasa;
		this.squadraOspite = squadraOspite;
		this.ora = ora;
		this.minuti = min;
		this.data = new GregorianCalendar(anno, mese, giorno, ora, min);
		this.stadio = stadio;
		this.prezzoBiglietto = prezzo;
		this.percentualeSconto1 = 0;
		this.percentualeSconto2 = 0;
		this.percentualeSconto3 = 0;
		
		vettorePosti = new PostiTribuna[stadio.getPostiASedere()];
		
		for( int i=0; i < stadio.getPostiASedere(); i++)
		{
			vettorePosti[i] = new PostiTribuna();
		}
	}
	
	/**
	 * Ispeziona il codice identificativo della partita
	 * @return codice della partita
	 */
	public int getCodice()
	{
		return this.codiceP;
		
	}
	
	/**
	 * Ispeziona la squadra che gioca in casa
	 * @return	squadraCasa
	 */
	public String getCasa()
	{
		return this.squadraCasa;
	}
	
	/**
	 * Ispeziona la squadra ospite
	 * @return	squadraOspite
	 */
	public String getOspite()
	{
		return this.squadraOspite;
	}
	
	/**
	 * Ispeziona la data dell'incontro 
	 * @return data della partita
	 */
	public GregorianCalendar getData()
	{
		return this.data;
	}
	
	/**
	 * Ispeziona il giorno in cui si terrà la partita
	 * @return giorno della data
	 */
	public int getGiorno()
	{
		return this.data.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Ispeziona il mese in cui si terrà la partita
	 * @return mese della data
	 */
	public int getMese()
	{
		return this.data.get(Calendar.MONTH);
	}
	
	/**
	 * Ispeziona l'anno in cui si terrà la partita
	 * @return anno della data
	 */
	public int getAnno()
	{
		return this.data.get(Calendar.YEAR);
	}
	
	/**
	 * Ispeziona l\'ora di inizio partita
	 * @return ora inizio
	 */
	public int getOra()
	{
		return ora;
	}
	
	/**
	 * Ispeziona i minuti di inizio della partita
	 * @return minuti inizio
	 */
	public int getMin()
	{
		return minuti;
	}
	
	/**
	 * Ispeziona lo stadio in cui verrà disputata la partita
	 * @return stadio
	 */
	public Stadio getStadio()
	{
		return stadio;
	}
	
	/**
	 * Ispeziona il nome in cui verra disputata la partita
	 * @return nome dello stadio
	 */
	public String getNomeStadio()
	{
		return stadio.getNomeStadio();
	}
	
	/**
	 * Ispeziona il prezzo del biglietto
	 * @return prezzo biglietto per la partita
	 */
	public double getPrezzoBiglietto()
	{
		return prezzoBiglietto;
	}
	
	/**
	 * Setta il prezzo della partita
	 * @param prezzo
	 */
	public void setPrezzoBiglietto(double prezzo)
	{
		boolean done = false;
		
		for(int i=0; i<stadio.getPostiASedere(); i++)
		{
			if( vettorePosti[i].getStatoPosto().equals(Color.YELLOW) || vettorePosti[i].getStatoPosto().equals(Color.RED))
			{
				done = true;
			}
		}
		
		if(!done)
		{
			prezzoBiglietto = prezzo;
		}
		
	}
	
	/**
	 * Ispeziona gli stati devi posti in tribuna
	 * @return	lista dei posti in tribuna con i vari stati(disponibile, prenotato, acquistato)
	 */
	public PostiTribuna[] getPostiInTribuna()
	{
		return vettorePosti;
	}
	
	/**
	 * Ispeziona la percentuale dello sconto1
	 * @return percentuale di sconto
	 */
	public double getPercSconto1()
	{
		return percentualeSconto1 ;
	}
	
	
	/**
	 * Iseziona la percentuale dello sconto2
	 * @return percentuale di sconto
	 */
	public double getPercSconto2()
	{
		return percentualeSconto2;
	}
	
	/**
	 * Ispeziona la percemtuale dello sconto3
	 * @return percentuale di sconto
	 */
	public double getPercSconto3()
	{
		return percentualeSconto3;
	}
	
	
	/**
	 * Setta la percentuale dello sconto1
	 * @param percentuale
	 */
	public void aggiornaPercentualeSconto1(double perc)
	{
		
			this.percentualeSconto1 = perc;
	}
	
	/**
	 * Setta la percentuale dello sconto2
	 * @param percentuale
	 */
	public void aggiornaPercentualeSconto2(double perc)
	{
		
			this.percentualeSconto2 = perc;
	}
	
	/**
	 * Setta la percentuale dello sconto3
	 * @param percentuale
	 */
	public void aggiornaPercentualeSconto3(double perc)
	{
		
			this.percentualeSconto3 = perc;
	}
	
	public double scontoMigliore()
	{
		double percMax = percentualeSconto1;
		
		if(percentualeSconto2 > percMax )
		{
			percMax  = percentualeSconto2;
		}
		else if (percentualeSconto3 > percMax )
		{
			percMax  = percentualeSconto3;
		}
		
		return percMax;
	}
	
	/**
	 * Ispeziona il prezzo del biglietto valutando il migliore scnto disponibile
	 * @return prezzo scontato
	 */
	public double getPrezzoBigliettoScontato()
	{
		double scontoMigliore = this.scontoMigliore();
		
		return prezzoBiglietto - ((prezzoBiglietto * scontoMigliore)/100);
	}
	
	/**
	 * Verifica se un una partita è prenotabile
	 * @return un valore booleano
	 */
	public boolean isPrenotabile()
	{
		//Ricavo la data Ordierna e aggiungo 12 ore
		
		GregorianCalendar dataGiornaliera = new GregorianCalendar();
		
		dataGiornaliera.add(Calendar.HOUR, 12);
		
		if(dataGiornaliera.after(getData()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Sovrascrittura del metodo toString() della classe Object
	 */
	public String toString()
	{
		return "codicePartita = " + codiceP + "       squadraCasa = " + squadraCasa +
				"       squadraOspite = " + squadraOspite + "        Data = " + getGiorno()+ "/" + getMese() + "/" + getAnno() + "     "  +
				"ora inizio = " + this.getOra() + " : " + this.getMin() +
				"       NomeStadio = " + stadio.getNomeStadio() + "           PrezzoBiglietto = " + prezzoBiglietto;
	}
}