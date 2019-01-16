import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Frame per aggiornare i prezzi delle partite di un determinato stadio
 */
public class FrameBott4Gestore extends JFrame
{
	private Struttura struttura;
	private JButton bottoneIndietro, bottoneAssegnaPrezzo;
	private JLabel label, prezzoLabel;
	private JComboBox stadioComboBox;
	private ArrayList<Stadio> listaStadi;
	private JTextField prezzoField;
	
	
	public FrameBott4Gestore(Struttura struttura)
	{
		this.struttura = struttura;
		
		setTitle("Menu Assegnazione prezzo alle partite di uno stadio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-190, f.height/2-86, 380, 171);
		
		createLabel();
		createComboBox();
		createButton();
		createPanel();
		
		setVisible(true);
	}
	
	public void createLabel()
	{
		label = new JLabel("Selezionare lo stadio:");
		
		prezzoLabel = new JLabel("Prezzo da assegnare: ");
		prezzoField = new JTextField(10);
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
		
		bottoneAssegnaPrezzo = new JButton("Assegna prezzo alle partite");
		
		class AddBottoneAssegnaPrezzoListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				try 
				{
					boolean done = false;
					
					double prezzo = Double.parseDouble(prezzoField.getText());
					
					Stadio stadio = listaStadi.get(stadioComboBox.getSelectedIndex());
					ArrayList<Partita> listaPartite = struttura.getListaPartite();
					
					for(Partita p : listaPartite)
					{
						if(p.getNomeStadio().equals(stadio.getNomeStadio()))
						{
							p.setPrezzoBiglietto(prezzo);
							
							done = true;
						}
					}
					
					if(done)
					{
						JOptionPane.showMessageDialog(null, "Prezzi aggiornati");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Non esitono partite che si giocano in questo stadio");
					}
					
					struttura.salvaStruttura(struttura);
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
				catch (RuntimeException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
			
		ActionListener listenerAssegnaPrezzo = new AddBottoneAssegnaPrezzoListener();
		bottoneAssegnaPrezzo.addActionListener(listenerAssegnaPrezzo);
		
	}
	
	public void createPanel()
	{
		JPanel pannelloLabel = new JPanel();
		JPanel pannelloBottoni = new JPanel();
		
		pannelloLabel.add(label);
		pannelloLabel.add(stadioComboBox);
		pannelloLabel.add(prezzoLabel);
		pannelloLabel.add(prezzoField);
		
		pannelloBottoni.add(bottoneIndietro);
		pannelloBottoni.add(bottoneAssegnaPrezzo);
		
		pannelloLabel.setBackground(Color.GREEN);
		pannelloBottoni.setBackground(Color.GREEN);
		
		add(pannelloLabel, BorderLayout.CENTER);
		add(pannelloBottoni, BorderLayout.SOUTH);
	}
	
}