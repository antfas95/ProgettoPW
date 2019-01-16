import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * Frame per visualizzare le partite in base a più ordinamenti
 */
public class FrameBott2Gestore extends JFrame
{
	private Struttura struttura;
	private JLabel label;
	private JPanel pannello;
	private JRadioButton capienzaRadioB, cronologicoRadioB;
	private ActionListener listener;
	private JButton bottoneIndietro;
	private	JPanel		topPanel;
	private	JList		listbox;
	private JScrollPane scroll;
	private DefaultListModel listModel;
	
	public FrameBott2Gestore(Struttura struttura)
	{
		this.struttura = struttura;
		
		listModel = new DefaultListModel();
		
		
		setTitle("Visualizzazione Partite");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-225, f.height/2-200, 550, 400);
		
		label = new JLabel("Selezionare il metodo di ordinamento delle partite: ");
		
		pannello = new JPanel();
		pannello.add(label, BorderLayout.NORTH);
		add(pannello, BorderLayout.NORTH);
		
		
		
		class ChoiceListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				setChoice();
			}
		}
		
		listener = new ChoiceListener();
		
		createControlPanel();
		createButton();
		createJList();
		createPanel();
		
		setVisible(true);
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
		capienzaRadioB = new JRadioButton("Capienza Stadi");
		capienzaRadioB.addActionListener(listener);
		
		cronologicoRadioB = new JRadioButton("Ordine Cronologico");
		cronologicoRadioB.addActionListener(listener);
		
		
		//Il ButtonGroup è necessario per far si che solo uno dei bottoni può essere selezionao
		
		ButtonGroup gruppoBR = new ButtonGroup();
		gruppoBR.add(capienzaRadioB);
		gruppoBR.add(cronologicoRadioB);
		
		JPanel panel = new JPanel();
		panel.add(capienzaRadioB);
		panel.add(cronologicoRadioB);
		
		//viene racchiuso il pannello tra bordi in rilievo
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Odinamento:"));
		
		
		return panel;
		
	}
	
	public void createButton()
	{
		bottoneIndietro = new JButton("INDIETRO");
		
		class AddBottoneIndietroListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameMenuGestore = new MenuGestore(struttura);
				dispose();
			}
		}
			
		ActionListener listener = new AddBottoneIndietroListener();
		bottoneIndietro.addActionListener(listener);
	}
	
	
	
	
	public void createPanel()
	{
		JPanel pannello = new JPanel();
		
		pannello.add(bottoneIndietro);
		
		
		add(pannello, BorderLayout.SOUTH);
		
		
		add(topPanel, BorderLayout.CENTER);	
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
	
	
	public void setChoice()
	{
		class ordinamentoPerCapienzaStadio implements  Comparator <Stadio>
		{

			public int compare(Stadio event, Stadio event1) {
				
				return event.compareTo(event1);
			}
			
		}
		
		class ordinamentoCronologico implements  Comparator <Partita>
		{

			public int compare(Partita event, Partita event1) {
				
				return event.getData().compareTo(event1.getData());
			}

			
			
		}
		
		if (capienzaRadioB.isSelected())
		{
			listModel.clear();
			
			ArrayList<Partita> listaPartite = struttura.getListaPartite();
			ArrayList<Stadio> listaStadi = struttura.getListaStadi();
			
			Collections.sort(listaStadi, new ordinamentoPerCapienzaStadio());
			
			for (Stadio s: listaStadi)
			{
				listModel.addElement(s.toString());
				for(Partita p: listaPartite)
				{
					if(p.getNomeStadio().equals(s.getNomeStadio()))
					listModel.addElement(p.toString());
					
					
				}
			}
			
			
		}
		else if (cronologicoRadioB.isSelected())
		{
			listModel.clear();
			
			ArrayList<Partita> listaPartite = struttura.getListaPartite();
			
			Collections.sort(listaPartite, new ordinamentoCronologico());
			
			for (Partita p: listaPartite)
			{
				listModel.addElement(p.toString());
			}
		}
		
	}
}