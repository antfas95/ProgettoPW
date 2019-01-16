import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/*
 * Frame che visualizza le prenotazioni effettuate dal cliente
 */
public class FrameBottClientePrenotazioniEff extends JFrame
{
	private Cliente cliente;
	private Struttura struttura;
	private DefaultListModel listModel;
	private JLabel  prenotazioniLabel;
	private JPanel topPanel;
	private JList listbox;
	private JScrollPane scroll;
	private JButton bottoneIndietro, bottoneAcquistaPrenotazione, bottoneEliminaPrenotazione;
	private ArrayList<Biglietto>bigliettiPrenotati;
	
	public FrameBottClientePrenotazioniEff(Cliente cliente, Struttura struttura)
	{
		this.cliente = cliente;
		this.struttura = struttura;
		
		listModel = new DefaultListModel();
		
		setTitle("Vsualizzazione Partite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-350, f.height/2-200, 700, 400);
		
		
		
		
		createLabel();
		createJList();
		createButton();
		createPanel();
		
		setVisible(true);
	}
	
	public void createLabel()
	{
		prenotazioniLabel = new JLabel("Elenco delle partite prenotate dal cliente " + cliente.getNome());
	}
	
	public void createJList()
	{
		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create some items to add to the list
		
		bigliettiPrenotati = cliente.getBigliettiPrenotatti();
		
		for(Biglietto b : bigliettiPrenotati)
		{
			listModel.addElement(b.toString());
		}
		
		// Create a new listbox control
		listbox = new JList( listModel );
		topPanel.add( listbox, BorderLayout.CENTER );
		scroll = new JScrollPane(listbox);
		listbox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		topPanel.add(scroll);
	}
	
	public void createButton()
	{
		bottoneIndietro = new JButton("INDIETRO");
		
		class AddBottoneIndietroListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameMenuCliente = new menuCliente(cliente, struttura);
				dispose();
			}
		}
		
		ActionListener listener = new AddBottoneIndietroListener();
		bottoneIndietro.addActionListener(listener);
		
		
		bottoneAcquistaPrenotazione = new JButton("Acquista Prenotazione");
		
		class AddBottoneAcquistaPrenotazioneListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
			
				try 
				{
					//Estraiamo il biglietto k vogliamo acquistare
					Biglietto bigliettoDaAcquistare = bigliettiPrenotati.get(listbox.getSelectedIndex()); 
					
					//Estraiamo la partita 
					Partita partitaBigliettoDaAcquistare = bigliettoDaAcquistare.getPartita();
					
					//Aggiorniamo i postiTribuna relativi al biglietto: lo settiamo ad acquistato
					PostiTribuna[] posti = partitaBigliettoDaAcquistare.getPostiInTribuna();
					
					
					//Aggiornare l array in cliente degli acquisti
					cliente.AcquistaBiglietti(Color.GREEN, new Biglietto(partitaBigliettoDaAcquistare, bigliettoDaAcquistare.getRiga()));
					
					posti[bigliettoDaAcquistare.getRiga()].setAcquistato();
					
					//Eliminare dall'array delle prenotazioni in biglietto
					cliente.eliminaBigliettoDallePrenotazioni(bigliettoDaAcquistare);
					
					//Aggiorno l'incasso dello stadio
					
					Stadio stadioPartita = partitaBigliettoDaAcquistare.getStadio();
					
					stadioPartita.setIncassoStadio(partitaBigliettoDaAcquistare.getPrezzoBigliettoScontato());
					
					
					struttura.salvaStruttura(struttura);
					JOptionPane.showMessageDialog(null, "Acquisto Effettuato al prezo di " + partitaBigliettoDaAcquistare.getPrezzoBigliettoScontato());
							
					listModel.clear();

					bigliettiPrenotati = cliente.getBigliettiPrenotatti();
							
					for(Biglietto b : bigliettiPrenotati)
					{
						listModel.addElement(b.toString());
					}
				
				} 
				catch (ClassNotFoundException | IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
		
		ActionListener listenerAcquistaOrenotazione = new AddBottoneAcquistaPrenotazioneListener();
		bottoneAcquistaPrenotazione.addActionListener(listenerAcquistaOrenotazione);
		
		
		
		bottoneEliminaPrenotazione = new JButton("Elimina Prenotazione");
		
		class AddBottoneEliminaPrenotazioneListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//Estraiamo il biglietto k vogliamo acquistare
				Biglietto bigliettoDaEliminare = bigliettiPrenotati.get(listbox.getSelectedIndex()); 
				
				//Estraiamo la partita 
				Partita partitaBigliettoDaEliminare = bigliettoDaEliminare.getPartita();
				
				//Aggiorniamo i postiTribuna relativi al biglietto: lo settiamo a disponibile
				PostiTribuna[] posti = partitaBigliettoDaEliminare.getPostiInTribuna();
				
				posti[bigliettoDaEliminare.getRiga()].setDisponibile();
				
				//Eliminare dall'array delle prenotazioni in biglietto
				cliente.eliminaBigliettoDallePrenotazioni(bigliettoDaEliminare);
				
				//salvo la struttura
				try 
				{
						struttura.salvaStruttura(struttura);
						JOptionPane.showMessageDialog(null, "Eliminazione Effettuata");
						
						listModel.clear();
						
						bigliettiPrenotati = cliente.getBigliettiPrenotatti();
						
						for(Biglietto b : bigliettiPrenotati)
						{
							listModel.addElement(b.toString());
						}
				} 
				catch (ClassNotFoundException | IOException e) 
				{
					e.printStackTrace();
				}
				
			}
		}
		
		ActionListener listenerEliminaPrenotazione = new AddBottoneEliminaPrenotazioneListener();
		bottoneEliminaPrenotazione.addActionListener(listenerEliminaPrenotazione);
		
		
		
	}
	
	public void createPanel()
	{
		JPanel pannelloNord = new JPanel();
		
		pannelloNord.add(prenotazioniLabel);
		
		JPanel pannelloSud = new JPanel();
		
		pannelloSud.add(bottoneIndietro);
		pannelloSud.add(bottoneAcquistaPrenotazione);
		pannelloSud.add(bottoneEliminaPrenotazione);
		
		add(pannelloNord, BorderLayout.NORTH);
		add(topPanel);
		add(pannelloSud, BorderLayout.SOUTH);
		
		
	}
}