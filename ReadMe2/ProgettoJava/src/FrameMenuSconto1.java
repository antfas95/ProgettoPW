import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/*
 * Frame del menù dello sconto riguardante una singola partita.
 */
public class FrameMenuSconto1 extends JFrame
{
	private Struttura struttura;
	private JLabel label1, scontoLabel;
	private JButton bottoneIndietro, bottoneAppSconto;
	private	JPanel		topPanel;
	private	JList		listbox;
	private JScrollPane scroll;
	private DefaultListModel listModel;
	private JTextField scontoField;
	private ArrayList<Partita> listaPartite;
	
	public FrameMenuSconto1(Struttura struttura)
	{
		this.struttura = struttura;
		listModel = new DefaultListModel();
		
		setTitle("Menu Sconto Singola Partita");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-225, f.height/2-200, 550, 400);
		
		label1 = new JLabel("Seleziona la partita: ");
		
		createLabel();
		createButton();
		createJList();
		createPanel();
		
		setVisible(true);
		
	}
	
	public void createLabel()
	{
		scontoLabel = new JLabel("Percentuale di sconto: ");
		scontoField = new JTextField(10);
	}
	
	public void createButton()
	{
		bottoneIndietro = new JButton("INDIETRO");
		
		class AddBottoneIndietroListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBottone3Gestore = new FrameBott3Gestore(struttura);
				dispose();
			}
		}
			
		ActionListener listener = new AddBottoneIndietroListener();
		bottoneIndietro.addActionListener(listener);
	
		bottoneAppSconto = new JButton("Applica Sconto");
	
		class AddBottoneAppScontoListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				try 
				{
					Partita partita = listaPartite.get(listbox.getSelectedIndex());
					
					double percSconto = Double.parseDouble(scontoField.getText());
					
					partita.aggiornaPercentualeSconto1(percSconto);
					
					struttura.salvaStruttura(struttura);
					JOptionPane.showMessageDialog(null,"Sconto Applicato con successo");
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (ClassNotFoundException e) 
				{
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
				
			}
		}
		
		ActionListener listenerSconto = new AddBottoneAppScontoListener();
		bottoneAppSconto.addActionListener(listenerSconto);
	}
	
	
	public void createJList()
	{
		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create some items to add to the list
		
		
		listaPartite = struttura.getListaPartite();
		
		for (Partita p: listaPartite)
		{
			listModel.addElement(p.toString());
		}
		
		// Create a new listbox control
		listbox = new JList( listModel );
		topPanel.add( listbox, BorderLayout.CENTER );
		scroll = new JScrollPane(listbox);
		listbox.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		topPanel.add(scroll);
	}
	
	public void createPanel()
	{
		JPanel pannelloBottoni = new JPanel();
		
		add(label1, BorderLayout.NORTH);
		pannelloBottoni.add(bottoneIndietro);
		pannelloBottoni.add(scontoLabel);
		pannelloBottoni.add(scontoField);
		pannelloBottoni.add(bottoneAppSconto);
		add(topPanel, BorderLayout.CENTER);
		add(pannelloBottoni, BorderLayout.SOUTH);
	}
}