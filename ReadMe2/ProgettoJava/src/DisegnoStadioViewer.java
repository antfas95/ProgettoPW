import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * La componente campo e i bottoni che vanno a formare la tribuna vengono inseriti in un frame
 */
public class DisegnoStadioViewer extends JFrame
{
	private static final int FRAME_WIDTH = 1300;
	private static final int FRAME_HEIGHT = 900;
	private JPanel pannelloTribuna;
	private JButton bottoneIndietro;
	private JButton[] vettoreBottoni;
	private ActionListener[] listeners;
	private Cliente cliente;
	private Partita partita;
	private Struttura struttura;
	private Stadio stadio;
	private boolean sceltaBottone;
	
	
	public DisegnoStadioViewer(Cliente cliente, Partita partita, Struttura struttura, boolean sceltaBottone) 
	{
		this.cliente = cliente;
		this.partita = partita;
		this.struttura = struttura;
		
		this.sceltaBottone = sceltaBottone;
		
		
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Stadio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		stadio = partita.getStadio();
		
		aggiungiComponenteStadio();
		disegnaTribuna();
		add(pannelloTribuna, BorderLayout.NORTH);
		add(bottoneIndietro, BorderLayout.SOUTH);
		
		setVisible(true);
		
	}
	
	public void aggiungiComponenteStadio()
	{
		DisegnoStadioComponent componenteStadio = new DisegnoStadioComponent();
		add(componenteStadio,  BorderLayout.CENTER);
	}
	
	
	
	public void disegnaTribuna()
	{
		createPannelloTribuna();
		createButtons();
		createBottoneIndietro();

	}
	
	public void createBottoneIndietro()
	{
		bottoneIndietro = new JButton("INDIETRO");
		
		class AddBottoneIndietroListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				
				JFrame frameBottClienteListaPartite = new FrameBottClienteListaPartite(cliente, struttura);
				dispose();
			}
		}
			
		ActionListener listener = new AddBottoneIndietroListener();
		bottoneIndietro.addActionListener(listener);
	}
	
	
	public void createPannelloTribuna()
	{
		createButtons();
			
		pannelloTribuna = new JPanel();
			
		pannelloTribuna.setLayout( new GridLayout(stadio.getPostiASedere()/10, 10));
			
		for (int i = 0; i < stadio.getPostiASedere(); i++)
		{
				pannelloTribuna.add(vettoreBottoni[i]);
			
		}
			
		
	}
	
	public void createButtons()
	{
		
		
		
		vettoreBottoni = new JButton[stadio.getPostiASedere()];
		listeners = new ActionListener[stadio.getPostiASedere()];
		
		for (int i = 0; i < stadio.getPostiASedere()  ; i++)
		{
				vettoreBottoni[i]= new JButton("" + i);
		}
		
		//Caricato lo stato dei posti in tribuna della partita
		PostiTribuna[] posti = partita.getPostiInTribuna();
		
		
		for (int i = 0; i < stadio.getPostiASedere()  ; i++)
		{
				vettoreBottoni[i].setBackground(posti[i].getStatoPosto());
				
		}
		
	
		class AddButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				
				//Estraiamo il pulsante k ha generato l'evento
				JButton bottonePremuto = (JButton)event.getSource();
				
				if(sceltaBottone)
				{
					//SCELTA TRUE (ACQUISTO)
					try 
					{
					
						
							
						int numeroPosto = Integer.parseInt(bottonePremuto.getText().trim());
							
						PostiTribuna[] posti = partita.getPostiInTribuna();
							
						//acquisto del posto
							
						Color statoPosto = posti[numeroPosto].getStatoPosto();
						
						
						cliente.AcquistaBiglietti(statoPosto,new Biglietto(partita, numeroPosto));
						
						bottonePremuto.setBackground(Color.GRAY);
						
						//settimao ad acquistato il posto
						posti[numeroPosto].setAcquistato();
						
						//Aggiorno l'incasso dello stadio
							
						Stadio stadioPartita = partita.getStadio();
							
						stadioPartita.setIncassoStadio(partita.getPrezzoBigliettoScontato());
						
						JOptionPane.showMessageDialog(null, "Acquisto Effettuato al prezo di " + partita.getPrezzoBigliettoScontato() + "  scontato del " + partita.scontoMigliore() );
						
						//salvo la struttura
						
							struttura.salvaStruttura(struttura);
						} 
						catch (ClassNotFoundException | IOException e) 
						{
							e.printStackTrace();
						}
						catch(PostoIndisponibileException exception)
						{
							JOptionPane.showMessageDialog(null, "Acquisto Del Biglietto già effettuato");
						}

				}
				else
				{
					//SCELTA FALSE (PRENOTA)
					
					try 
					{
						
						if(partita.isPrenotabile())
						{
							JOptionPane.showMessageDialog(null, "Il posto non è più prenotabile: La partita Inizia tra meno di 12 ore");
						}
						else
						{
							
							
							int numeroPosto = Integer.parseInt(bottonePremuto.getText().trim());
							
							PostiTribuna[] posti = partita.getPostiInTribuna();
							
							//prenotazione del posto
							
							Color statoPosto = posti[numeroPosto].getStatoPosto();
							
							
							cliente.prenotaBiglietto(statoPosto,new Biglietto(partita, numeroPosto));
							
							bottonePremuto.setBackground(Color.CYAN);
							
							posti[numeroPosto].setPrenotato();
							
							//salvo la struttura
							struttura.salvaStruttura(struttura);
						}
					} 
					catch (ClassNotFoundException | IOException e) 
					{
						e.printStackTrace();
					}
					catch (PostoIndisponibileException e)
					{
						JOptionPane.showMessageDialog(null, "Prenotazione Della Partita già effettuata");
					}
				}
			}
		}
		
		for (int i = 0; i < stadio.getPostiASedere(); i++)
		{
				JButton bottone = new JButton();
				bottone = vettoreBottoni[i];
				listeners[i] = new AddButtonListener();
				bottone.addActionListener(listeners[i]);
		}
	}
}