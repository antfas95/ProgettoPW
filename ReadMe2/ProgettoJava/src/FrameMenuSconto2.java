import java.awt.BorderLayout;
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
 * Frame del menù dello sconto riguardante le partite di uno stadio
 */
public class FrameMenuSconto2 extends JFrame
{
	private Struttura struttura;
	private JButton bottoneIndietro, bottoneAppSconto;
	private JLabel scontoLabel;
	private JTextField scontoField;
	private JComboBox stadioComboBox;
	private ArrayList<Stadio> listaStadi;
	
	public FrameMenuSconto2(Struttura struttura)
	{
		this.struttura = struttura;
		
		setTitle("Menu Sconto Partite di uno stadio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-175, f.height/2-75, 350, 150);
		
		createLabel();
		createComboBox();
		createButton();
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
				boolean done = false;
				try 
				{
					double percSconto = Double.parseDouble(scontoField.getText());
					
					Stadio stadio = listaStadi.get(stadioComboBox.getSelectedIndex());
					ArrayList<Partita> listaPartite = struttura.getListaPartite();
					
					for(Partita p: listaPartite)
					{
						if(stadio.getNomeStadio().equals(p.getNomeStadio()))
						{
							p.aggiornaPercentualeSconto2(percSconto);
							done = true;
						}
					}
					
					if(done)
					{
						struttura.salvaStruttura(struttura);
						JOptionPane.showMessageDialog(null,"Sconto Applicato con successo alle partite");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Non ci sono partite per questo stadio");
					}
					
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
	
	public void createPanel()
	{
		JPanel pannelloLabel = new JPanel();
		
		pannelloLabel.add(scontoLabel);
		pannelloLabel.add(scontoField);
		
		add(pannelloLabel, BorderLayout.CENTER);
		add(stadioComboBox, BorderLayout.NORTH);
		
		JPanel pannelloBottoni = new JPanel();
		
		pannelloBottoni.add(bottoneIndietro);
		pannelloBottoni.add(bottoneAppSconto);
		
		add(pannelloBottoni, BorderLayout.SOUTH);
	}
}