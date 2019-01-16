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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Frame menù sconto riguardante la fascia giornaliera
 */
public class FrameMenuSconto3 extends JFrame
{
	private Struttura struttura;
	private JLabel label, scontoLabel, oreLabelInizio, minutiLabelInizio, oreLabelFine, minutiLabelFine;
	private JTextField scontoField, oreFieldInizio, minutiFieldInizio, oreFieldFine, minutiFieldFine;
	private JButton bottoneIndietro, bottoneAppSconto;
	private ArrayList<Partita> listaPartite;
	
	
	public FrameMenuSconto3(Struttura struttura)
	{
		this.struttura = struttura;
		
		setTitle("Menu Sconto fascia giornaliera");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-124, f.height/2-125, 248, 250);
		
		createLabel();
		createButton();
		createPanel();
		
		setVisible(true);
	}
	
	public void createLabel()
	{
		label = new JLabel("Inserisci l'intervallo");
		oreLabelInizio = new JLabel("Inizio Intervallo     Ora : ");
		oreFieldInizio = new JTextField(5);
		oreLabelFine = new JLabel("Fine intervallo     Ora: ");
		oreFieldFine = new JTextField(5);
		scontoLabel = new JLabel("Inserisci la percentuale di sconto: ");
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
					boolean done = false;
					
					int oraInizio = Integer.parseInt(oreFieldInizio.getText());
					int oraFine = Integer.parseInt(oreFieldFine.getText());
					
					
					double percSconto = Double.parseDouble(scontoField.getText());
					
					listaPartite = struttura.getListaPartite();
					
					for(Partita p: listaPartite)
					{
						System.out.println(p.getOra()+ "   " + oraInizio);
						if(p.getOra() >= oraInizio  && p.getOra() <= oraFine )
						{
							p.aggiornaPercentualeSconto3(percSconto);
							done = true;
						}
					}
					
					if(done)
					{
						JOptionPane.showMessageDialog(null,"Sconto Applicato con successo");
						struttura.salvaStruttura(struttura);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Nessuna partita è giocata in questo intervallo");
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
	
	
	public void createPanel()
	{
		JPanel pannelloLabel = new JPanel();
		JPanel pannelloBottoni = new JPanel();
		
		pannelloLabel.add(label);
		pannelloLabel.add(oreLabelInizio);
		pannelloLabel.add(oreFieldInizio);
		
		pannelloLabel.add(oreLabelFine);
		pannelloLabel.add(oreFieldFine);
		
		
		pannelloLabel.add(scontoLabel);
		pannelloLabel.add(scontoField);
		
		pannelloBottoni.add(bottoneIndietro);
		pannelloBottoni.add(bottoneAppSconto);
		
	
		
		add(pannelloLabel, BorderLayout.CENTER);
		add(pannelloBottoni, BorderLayout.SOUTH);
		
	}
}