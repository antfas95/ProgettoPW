import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * Frame per aumentare o ridurre la capienza degli stadi
 */
public class FrameBott5Gestore extends JFrame
{
	private Struttura struttura;
	private JLabel label;
	private JComboBox stadioComboBox;
	private ArrayList<Stadio> listaStadi;
	private JLabel capienzaLabel;
	private JTextField capienzaField;
	private JButton bottoneIndietro, bottoneAumentaC, bottoneDiminuisciC;
	private Stadio stadioScelto;
	private ActionListener listener;
	
	
	public FrameBott5Gestore(Struttura struttura)
	{
		this.struttura = struttura;
		
		setTitle("Menu aumentare e ridurre capienza stadi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-300, f.height/2-86, 600, 171);
		
		
		class ChoiceListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				stadioScelto = listaStadi.get(stadioComboBox.getSelectedIndex());
				
			}
		}
		
		listener = new ChoiceListener();
		
		
		createLabel();
		createComboBox();
		createButton();
		createPanel();
		
		setVisible(true);
	}
	
	public void createLabel()
	{
		label = new JLabel("Selezionare lo stadio:");
		capienzaLabel = new JLabel("Digitare il numero di posti per aumentare o diminuire la capienza dello stadio: ");
		capienzaField = new JTextField(10);
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
				JFrame frameMenuGestore = new MenuGestore(struttura);
				dispose();
			}
		}
		
		ActionListener listener = new AddBottoneIndietroListener();
		bottoneIndietro.addActionListener(listener);
		
		
		bottoneAumentaC = new JButton("Aumenta Capienza");
		
		class AddBottoneAumentaCListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				try
				{
					int capienza = Integer.parseInt(capienzaField.getText());
					ArrayList<Partita> listaPartite = struttura.getListaPartite();
				
				
					stadioScelto.aumentaCapienza(capienza, listaPartite);
					JOptionPane.showMessageDialog(null, "la capienza è stata aggiornata");
				}
				catch(RuntimeException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
		
		ActionListener listenerAumentaC = new AddBottoneAumentaCListener();
		bottoneAumentaC.addActionListener(listenerAumentaC);
		
		
		bottoneDiminuisciC = new JButton("Diminuisci Capienza");
		
		class AddBottoneDiminuisciCListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				try
				{
					int capienza = Integer.parseInt(capienzaField.getText());
					ArrayList<Partita> listaPartite = struttura.getListaPartite();
				
				
					stadioScelto.diminuisciCapienza(capienza, listaPartite);
					JOptionPane.showMessageDialog(null, "la capienza è stata aggiornata");
				}
				catch(RuntimeException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
		
		ActionListener listenerDiminuisciC = new AddBottoneDiminuisciCListener();
		bottoneDiminuisciC.addActionListener(listenerDiminuisciC);
		
		
		
	}
	
	public void createPanel()
	{
		JPanel pannelloLabel = new JPanel();
		
		pannelloLabel.add(label);
		pannelloLabel.add(stadioComboBox);
		pannelloLabel.add(capienzaLabel);
		pannelloLabel.add(capienzaField);
		
		JPanel pannelloBottoni = new JPanel();
		
		pannelloBottoni.add(bottoneIndietro);
		pannelloBottoni.add(bottoneAumentaC);
		pannelloBottoni.add(bottoneDiminuisciC);
		
		pannelloLabel.setBackground(Color.GREEN);
		pannelloBottoni.setBackground(Color.GREEN);
		
		add(pannelloLabel, BorderLayout.CENTER);
		add(pannelloBottoni, BorderLayout.SOUTH);
		
	}
	
	
}