import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/*
 * Frame che mette in evidenza le partite che possono essere acquistate o prenotate
 */
public class FrameBottClienteListaPartite extends JFrame
{
	private Struttura struttura;
	private Cliente cliente;
	private JLabel partitaLabel;
	private JRadioButton cronologicoRadioB, crescenteRadioB, crescenteSquadraRadioB;
	private ActionListener listener;
	private JButton bottoneIndietro, bottonePrenota, bottoneAcquista;
	private JPanel topPanel;
	private DefaultListModel listModel;
	private JList listbox;
	private JScrollPane scroll;
	private ArrayList<Partita> listaPartite;
	
	public FrameBottClienteListaPartite(Cliente cliente, Struttura struttura)
	{
		this.struttura = struttura;
		this.cliente = cliente;
		
		listModel = new DefaultListModel();
		
		setTitle("Menu lista partite ancora non iniziate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-550, f.height/2-200, 1100, 400);
		
		listaPartite = struttura.getListaPartite();
		
		class ChoiceListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				setChoice();
			}
		}
		
		listener = new ChoiceListener();
		
		
		
		createLabel();
		createControlPanel();
		createButton();
		createJList();
		createPanel();
		
		setVisible(true);
	}
	
	public void createLabel()
	{
		partitaLabel = new JLabel("Visualizzare la lista delle partite ancora non iniziate :");
	}
	
	public void createControlPanel()
	{
		JPanel buttonRadioPanel = createPulsantiRadio();
		
		JPanel controlPanel = new JPanel();
		controlPanel.add(buttonRadioPanel);
		
		this.add(controlPanel, BorderLayout.NORTH);
	}
	
	public JPanel createPulsantiRadio()
	{
		cronologicoRadioB = new JRadioButton("Ordinamento temporale");
		cronologicoRadioB.addActionListener(listener);
		
		crescenteRadioB = new JRadioButton("Ordinamento nome stadio");
		crescenteRadioB.addActionListener(listener);
		
		crescenteSquadraRadioB = new JRadioButton("Ordinamento nome squadra casa");
		crescenteSquadraRadioB.addActionListener(listener);
		
		
		
		//Il ButtonGroup è necessario per far si che solo uno dei bottoni può essere selezionao
		
		ButtonGroup gruppoBR = new ButtonGroup();
		gruppoBR.add(cronologicoRadioB);
		gruppoBR.add(crescenteRadioB);
		gruppoBR.add(crescenteSquadraRadioB);
		
		
		JPanel panel = new JPanel();
		panel.add(cronologicoRadioB);
		panel.add(crescenteRadioB);
		panel.add(crescenteSquadraRadioB);
		
		//viene racchiuso il pannello tra bordi in rilievo
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Odinamento:"));
		
		return panel;
		
	}
	
	public void createJList()
	{
		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create some items to add to the list
		
		listModel.addElement(" ");
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
		
		bottonePrenota = new JButton("Prenota Biglietto");
		
		class AddBottonePrenotaListener implements ActionListener
		{

			public void actionPerformed(ActionEvent event) 
			{
				//Prenotazione biglietto
				
				Partita partita = listaPartite.get(listbox.getSelectedIndex()); 
				
					JFrame disegnoStadioViewer = new DisegnoStadioViewer(cliente, partita, struttura, false);
					dispose();
				
				
			}
		}
		
		ActionListener listenerPrenota = new AddBottonePrenotaListener();
		bottonePrenota.addActionListener(listenerPrenota);
		
		
		bottoneAcquista = new JButton("Acquista Biglietto");
		
		class AddBottoneAcquistaListener implements ActionListener
		{

			public void actionPerformed(ActionEvent event) 
			{
				//acquisto biglietto
				
				Partita partita1 = listaPartite.get(listbox.getSelectedIndex()); 
				
				JFrame disegnoStadioViewer = new DisegnoStadioViewer(cliente, partita1, struttura, true);
				dispose();
			}
		}
		
		ActionListener listenerAcquista = new AddBottoneAcquistaListener();
		bottoneAcquista.addActionListener(listenerAcquista);
	}
	
	
	public void setChoice()
	{
		
		class ordinamentoCronologicoPartite implements  Comparator <Partita>
		{

			public int compare(Partita event, Partita event1) {
				
				return event.getData().compareTo(event1.getData());
			}	
		}
		
		
		class ordinamentoCrescenteStadio implements  Comparator <Stadio>
		{

			public int compare(Stadio event, Stadio event1) {
				
				return event.getNomeStadio().compareTo(event1.getNomeStadio());
			}
		}
		
		
		class ordinamentoLessicograficoSquadre implements Comparator <Partita>
		{
			
			public int compare(Partita event, Partita event1) {
				
				return event.getCasa().compareTo(event1.getCasa());
			}
		}
		
		if (cronologicoRadioB.isSelected())
		{
			listModel.clear();
			
			listaPartite = struttura.getListaPartite();
			
			
			Collections.sort(listaPartite, new ordinamentoCronologicoPartite());
			
			GregorianCalendar dataOdierna = new GregorianCalendar();
					
				for(Partita p: listaPartite)
				{
					if(p.getData().after(dataOdierna))
					{
						listModel.addElement(p.toString());	
					}
				}
			
			
		}
		
		else if (crescenteRadioB.isSelected())
		{
			listModel.clear();
			
			ArrayList<Stadio> listaStadi = struttura.getListaStadi();
			listaPartite = struttura.getListaPartite();
			
			Collections.sort(listaStadi, new ordinamentoCrescenteStadio());
			
			GregorianCalendar dataOdierna = new GregorianCalendar();
			
			for (Stadio s: listaStadi)
			{
				for(Partita p: listaPartite)
				{
					if(p.getData().after(dataOdierna))
					{
						if(p.getNomeStadio().equals(s.getNomeStadio()))
							listModel.addElement(p.toString());
					}
					
				}
			}
		}
		
		else if (crescenteSquadraRadioB.isSelected())
		{
			listModel.clear();
			
			Collections.sort(listaPartite, new ordinamentoLessicograficoSquadre());
			
			GregorianCalendar dataOdierna = new GregorianCalendar();
			
			for(Partita p: listaPartite)
			{
				if(p.getData().after(dataOdierna))
				{
					listModel.addElement(p.toString());	
				}
			}
		}
		
	}
	
	public void createPanel()
	{
		JPanel pannelloNord = new JPanel();
		JPanel pannelloCentro = new JPanel();
		JPanel pannelloSud = new JPanel();
		
		pannelloNord.add(partitaLabel);
		pannelloNord.add(cronologicoRadioB);
		pannelloNord.add(crescenteRadioB);
		pannelloNord.add(crescenteSquadraRadioB);
		
		
		
		pannelloSud.add(bottoneIndietro);
		pannelloSud.add(bottonePrenota);
		pannelloSud.add(bottoneAcquista);
		
		add(pannelloNord, BorderLayout.NORTH);
		add(topPanel, BorderLayout.CENTER);
		add(pannelloSud, BorderLayout.SOUTH);
	}
}