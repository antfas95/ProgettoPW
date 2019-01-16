import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Una struttura comprende un insieme di partite , di clienti e di stadi.
 * Per la struttura si individua un incasso totale rispetto alle partite giocate nei vari stadi.
 */
public class Struttura implements Serializable
{
	private double incassoTotale;
	
	private ArrayList<Partita> listaPartite;
	private ArrayList<Cliente> listaClienti;
	private ArrayList<Stadio> listaStadi;
	
	/**
	 * Costruttore della classe Struttura
	 */
	public Struttura()
	{	
		listaPartite = new ArrayList<>();
		listaClienti = new ArrayList<>();
		listaStadi = new ArrayList<>();
		
		incassoTotale= 0;
	}
	
	/**
	 * Ispeziona i clienti iscritti nella struttura.
	 * @return listaClienti		lista dei clienti
	 */
	public ArrayList<Cliente> getListaClienti()
	{
		return listaClienti;
	}
	
	/**
	 * Ispeziona le partite degli stadi.
	 * @return listaPartite		lista delle partite
	 */
	public ArrayList<Partita> getListaPartite()
	{
		return listaPartite;
	}
	
	/**
	 * Ispeziona gli stadi della struttura.
	 * @return listaStadi	lista degli stadi
	 */
	public ArrayList<Stadio> getListaStadi()
	{
		return listaStadi;
	}
	
	/**
	 * Aggiunge un nuovo cliente alla struttura
	 * @param nome 	NickName del nuovo cliente
	 * @param password 	password del nuovo cliente
	 */
	public void addCliente(String nome, String password)
	{
		boolean done = false;
		
		for(Cliente c : listaClienti)
		{
			
			if(c.getNome().equals(nome))
			{
				done = true;
			}
		}
		
		if(done)
		{
			throw new RuntimeException("Nickname già utilizzato");
		}
		else
		{
			listaClienti.add(new Cliente(nome, password));
		}
		
		
	}
	
	/**
	 * Aggiunge una partita alla struttura
	 * @param codiceP codice della partita
	 * @param squadraCasa	nome della squadra che gioca in casa
	 * @param squadraOspite nome della squadra ospite
	 * @param giorno	giorno della partita
	 * @param mese	mese della partita
	 * @param anno	anno della partita
	 * @param ora	ora di inizio
	 * @param min	minuti di inizio
	 * @param stadio	stadio in cui si disputa la partita
	 * @param prezzoBiglietto	prezzo biglietto della partita
	 */
	public void addPartita(int codiceP, String squadraCasa, String squadraOspite,int giorno, int mese,int anno, int ore, int min, Stadio stadio, double prezzoBiglietto)
	{
		boolean done = false;
		
		for(Partita p : listaPartite)
		{
			if(p.getCodice() == codiceP)
			{
				done = true;
			}
		}
		if(done)
		{
			throw new RuntimeException("Partita già inserita");
		}
		
		listaPartite.add(new Partita(codiceP, squadraCasa,squadraOspite,giorno,mese,anno, ore,  min, stadio, prezzoBiglietto));
	}
	
	
	/**
	 * Aggiunge uno stadio alla struttura
	 * @param nomeStadio   nome dello stadio
	 * @param postiASedere	capienza dello stadio
	 */
	public void addStadio(String nomeStadio, int postiASedere)
	{
		boolean done = false;
		
		for(Stadio s: listaStadi)
		{
			if(nomeStadio.equals(s.getNomeStadio()))
			{
				done = true;
			}
		}
		
		if(done)
		{
			throw new RuntimeException("Nome stadio già utilizzato");
		}
		else
		{
			listaStadi.add(new Stadio(nomeStadio, postiASedere));
		}
		
		
	}
	
	/**
	 * Aggiorna l\'incasso totale
	 * @param x	 quantità di denaro da aggiugere all\'incasso
	 */
	public void setIncassoTotale(double x)
	{
		incassoTotale = incassoTotale + x;
	}
	
	public double getIncassoTotale()
	{
		double sommaIncassoStadi = 0;
		
		for(Stadio s : listaStadi)
		{
			sommaIncassoStadi = sommaIncassoStadi + s.getIncassoStadio();
		}
		
		return incassoTotale = sommaIncassoStadi;
	}
	
	
	/**
	 * Carica dal file la struttura
	 * @return	struttura 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Struttura caricaStruttura() throws IOException, ClassNotFoundException
	{
		 ObjectInputStream in = new ObjectInputStream(new FileInputStream("/home/simox/Desktop/ProgettoJava2015-2016/FileStruttura.dat"));
	     Struttura struttura = (Struttura) in.readObject();
	     in.close();
	     return struttura;
	     
	}
	
	/**
	 * Salva la struttura nel file
	 * @param struttura
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void salvaStruttura(Struttura struttura) throws IOException, ClassNotFoundException
	{
		 ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/home/simox/Desktop/ProgettoJava2015-2016/FileStruttura.dat"));
	     out.writeObject(struttura);
	     out.close();
	}
}