import java.awt.BorderLayout;
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
 * Frame che visualizza tutti i biglietti acquistati
 */
public class FrameBottClienteAcquistiEff extends JFrame 
{
	private Cliente cliente;
	private Struttura struttura;
	private DefaultListModel listModel;
	private JLabel acquistiLabel;
	private JPanel topPanel;
	private ArrayList<Biglietto> bigliettiAcquistati;
	private JList listbox;
	private JScrollPane scroll;
	private JButton bottoneIndietro;
	
	public FrameBottClienteAcquistiEff(Cliente cliente, Struttura struttura)
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
		acquistiLabel = new JLabel("Elenco delle partite acquistate dal cliente " + cliente.getNome());
	}
	
	
	public void createJList()
	{
		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create some items to add to the list
		
		bigliettiAcquistati = cliente.getBigliettiAcquistati();
		
		for(Biglietto b : bigliettiAcquistati)
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
		
	}
	
	public void createPanel()
	{
		JPanel pannelloNord = new JPanel();
		
		pannelloNord.add(acquistiLabel);
		
		JPanel pannelloSud = new JPanel();
		
		pannelloSud.add(bottoneIndietro);
		
		add(pannelloNord, BorderLayout.NORTH);
		add(topPanel);
		add(pannelloSud, BorderLayout.SOUTH);
		
		
	}
}