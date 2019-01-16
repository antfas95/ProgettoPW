import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 * Frame che visualizza le partite in un determinato interallo o in base agli stadi
 */
public class FrameBottClienteVisualizzaPartite extends JFrame 
{
	private Struttura struttura;
	private Cliente cliente;
	private JList listbox;
	private DefaultListModel listModel;
	private JLabel label, inizioSettLabel, fineSettLabel, ggLabel, mmLabel, aaLabel, ggLabelF, mmLabelF, aaLabelF, partiteStadioLabel;
	private JTextField ggField, mmField, aaField, ggFieldF, mmFieldF, aaFieldF;
	private JButton bottoneIndietro, bottoneVisualizzaPartite;
	private JPanel topPanel;
	private JScrollPane scroll;
	private JComboBox stadioComboBox;
	private ArrayList<Stadio> listaStadi;
	private GregorianCalendar dataInizio;
	private GregorianCalendar dataFine;
	private ActionListener listener;
	private ArrayList<Partita> listaPartite;
	
	public FrameBottClienteVisualizzaPartite(Cliente cliente, final Struttura struttura)
	{
		this.struttura = struttura;
		this.cliente = cliente;
		listModel = new DefaultListModel();
		
		
		setTitle("Vsualizzazione Partite in un intervallo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-250, f.height/2-300, 500, 600);
		
		
		class ChoiceListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				Stadio stadio1 = listaStadi.get(stadioComboBox.getSelectedIndex());
				
				System.out.println(stadio1.getNomeStadio());
				
				listaPartite = struttura.getListaPartite();
				
				listModel.clear();
				
				for(Partita p1 : listaPartite)
				{
					if(p1.getNomeStadio().equals(stadio1.getNomeStadio()))
					{	
						listModel.addElement(p1.toString());
					}
					
				}
			}
			
		}
		
		listener = new ChoiceListener();
		
		
		
		createLabel();
		createComboBox();
		createButton();
		createJList();
		createPanel();
		
		setVisible(true);
	}
	
	public void createLabel()
	{
		label = new JLabel("Selezionare il metodo di ordinamento delle partite: ");
		inizioSettLabel = new JLabel("Inizio Intervallo:  ");
		fineSettLabel = new JLabel("Fine Intervallo:   ");
		partiteStadioLabel = new JLabel("Visualizza le partite tenute in uno stadio   ");
		
		ggLabel = new JLabel("giorno");
		ggField = new JTextField(5);
		mmLabel = new JLabel(" mese");
		mmField = new JTextField(5);
		aaLabel = new JLabel("anno");
		aaField = new JTextField(5);
		
		ggLabelF = new JLabel("giorno");
		ggFieldF = new JTextField(5);
		mmLabelF = new JLabel(" mese");
		mmFieldF = new JTextField(5);
		aaLabelF = new JLabel("anno");
		aaFieldF = new JTextField(5);
		
		
		
	}
	
	public void createComboBox()
	{
		stadioComboBox = new JComboBox();
		
		listaStadi = struttura.getListaStadi();
		
		for(Stadio s : listaStadi)
		{
			stadioComboBox.addItem(s.getNomeStadio());
		}
		
		stadioComboBox.setEditable(true);
		
		stadioComboBox.addActionListener(listener);
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
		
		
		bottoneVisualizzaPartite = new JButton("Visualizza Partite Nell\'intervallo");
		
		class AddbottoneVisualizzaPartiteListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				listModel.clear();
				
				int ggI = Integer.parseInt(ggField.getText());
				int mmI = Integer.parseInt(mmField.getText());
				int aaI = Integer.parseInt(aaField.getText());
				int ggF = Integer.parseInt(ggFieldF.getText());
				int mmF = Integer.parseInt(mmFieldF.getText());
				int aaF = Integer.parseInt(aaFieldF.getText());
				
				dataInizio = new GregorianCalendar(aaI,mmI,ggI);
				dataFine = new GregorianCalendar(aaF,mmF,ggF);
				
				listaPartite = struttura.getListaPartite();
				
				for(Partita p: listaPartite)
				{
					if(p.getData().after(dataInizio) && p.getData().before(dataFine))
						listModel.addElement(p.toString());
						
				}
				
			}
		}
			
		ActionListener listenerVisualizzaPartite = new AddbottoneVisualizzaPartiteListener();
		bottoneVisualizzaPartite.addActionListener(listenerVisualizzaPartite);
	}
	
	
	public void createJList()
	{

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add( topPanel );

		// Create some items to add to the list
				
		listModel.addElement(" ");
		// Create a new listbox control
		listbox = new JList( listModel);
		topPanel.add( listbox, BorderLayout.CENTER);
		scroll = new JScrollPane(listbox);
		listbox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		topPanel.add(scroll);
		
	}
	
	
	public void createPanel()
	{
		JPanel pannello = new JPanel();
		
		pannello.setLayout(new GridLayout(4, 1));
	
		JPanel pannelloInizio = new JPanel(); 
		
		pannelloInizio.add(inizioSettLabel);
		pannelloInizio.add(ggLabel);
		pannelloInizio.add(ggField);
		pannelloInizio.add(mmLabel);	
		pannelloInizio.add(mmField);
		pannelloInizio.add(aaLabel);
		pannelloInizio.add(aaField);
		
		pannelloInizio.setBackground(Color.ORANGE);
		
		pannello.add(pannelloInizio);
		
		JPanel pannelloFine = new JPanel();
		
		pannelloFine.add(fineSettLabel);
		pannelloFine.add(ggLabelF);
		pannelloFine.add(ggFieldF);
		pannelloFine.add(mmLabelF);	
		pannelloFine.add(mmFieldF);
		pannelloFine.add(aaLabelF);
		pannelloFine.add(aaFieldF);
		pannelloFine.add(bottoneVisualizzaPartite);

		pannelloFine.setBackground(Color.ORANGE);
		
		pannello.add(pannelloFine);
		
		
		JPanel pannelloComboBox = new JPanel();
				
		pannelloComboBox.add(partiteStadioLabel, BorderLayout.NORTH);
		pannelloComboBox.add(stadioComboBox, BorderLayout.CENTER);
			
		pannelloComboBox.setBackground(Color.ORANGE);
		
		pannello.add(pannelloComboBox);
		
		
		pannello.add(topPanel);
		
		
		JPanel pannelloBottone = new JPanel();
		
		pannelloBottone.add(bottoneIndietro);
		
		pannelloBottone.setBackground(Color.ORANGE);
		
		add(pannelloBottone, BorderLayout.SOUTH);
		
		
		
		add(pannello, BorderLayout.CENTER);
		
		
	}
	
	
}