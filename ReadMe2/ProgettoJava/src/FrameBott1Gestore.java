import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
 * Frame per inserire una partita
 */
public class FrameBott1Gestore extends JFrame
{
	private Struttura struttura;
	private JTextField codicePartitaField, squadraCasaField, squadraOspiteField, giornoField, meseField, annoField, oraField, minField, nomeStadioField, prezzoField;
	private JLabel codicePartitaLabel, squadraCasaLabel, squadraOspiteLabel, giornoLabel, meseLabel, annoLabel, oraLabel, minLabel, nomeStadioLabel, prezzoLabel;
	private JButton bottone1, bottoneInserisciPartita;
	private JComboBox stadioComboBox;
	private ArrayList<Stadio> listaStadi;
	private Stadio stadioScelto;
	private ActionListener listener;
	
	public FrameBott1Gestore(Struttura struttura)
	{
		this.struttura = struttura;
		
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-225, f.height/2-200, 550, 400);
		this.setTitle("Inserisci partite di calcio");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		class ChoiceListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				stadioScelto = listaStadi.get(stadioComboBox.getSelectedIndex());
				
			}
		}
		
		listener = new ChoiceListener();
		
		
		createField();
		createComboBox();
		createButton();
		createPanel();
		
		
		this.setVisible(true);
		
		
	}
	
	public void createField()
	{
		codicePartitaLabel = new JLabel("Codice Parita");
		codicePartitaField = new JTextField(5);
		
		squadraCasaLabel = new JLabel("Squadra Casa");
		squadraCasaField = new JTextField(10);
		
		squadraOspiteLabel = new JLabel("Squadra Ospite");
		squadraOspiteField = new JTextField(10);
		
		giornoLabel = new JLabel("Giorno");
		giornoField = new JTextField(5);
		
		meseLabel = new JLabel("Mese");
		meseField = new JTextField(5);
		
		annoLabel = new JLabel("Anno");
		annoField = new JTextField(5);
		
		oraLabel = new JLabel("Ora");
		oraField = new JTextField(4);
		
		minLabel = new JLabel("Min");
		minField = new JTextField(4);
		
		nomeStadioLabel = new JLabel("Nome Stadio");
		nomeStadioField = new JTextField(10);
		
		prezzoLabel = new JLabel("Prezzo Biglietto");
		prezzoField = new JTextField(5);
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
		
		bottone1 = new JButton("INDIETRO");
		
		class AddBottone1Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				
				JFrame frameMenuGestore = new MenuGestore(struttura);
				dispose();
			}
				
		}
		
		ActionListener listener1 = new AddBottone1Listener();
		bottone1.addActionListener(listener1);
		
		
		bottoneInserisciPartita = new JButton("Inserisci Partita");
		
		
		class AddBottone2Listener implements ActionListener
		{

			public void actionPerformed(ActionEvent event) 
			{
				
				int codicePartita = Integer.parseInt(codicePartitaField.getText());
				String casa = squadraCasaField.getText();
				String ospite = squadraOspiteField.getText();
				int giorno = Integer.parseInt(giornoField.getText());
				int mese = Integer.parseInt(meseField.getText());
				int anno= Integer.parseInt(annoField.getText());
				int ora = Integer.parseInt(oraField.getText());
				int min= Integer.parseInt(minField.getText());
				double prezzo = Double.parseDouble(prezzoField.getText());
				
				try 
				{
					struttura.addPartita(codicePartita,casa, ospite, giorno, mese, anno, ora, min,  stadioScelto, prezzo);
					
						struttura.salvaStruttura(struttura);
						JOptionPane.showMessageDialog(null,"Partita Inserita Correttamente");
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
				catch(RuntimeException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				
			}
			
		}
			
		ActionListener listenerInserisciPartita = new AddBottone2Listener();
		bottoneInserisciPartita.addActionListener(listenerInserisciPartita);
		
		
	} 
	
	public void createPanel()
	{
		
		JPanel pannello = new JPanel();
		JPanel pannello1 = new JPanel();
		pannello.setLayout(new GridLayout(10, 2));
		
		pannello.add(codicePartitaLabel);
		pannello.add(codicePartitaField);
		pannello.add(squadraCasaLabel);
		pannello.add(squadraCasaField);
		pannello.add(squadraOspiteLabel);
		pannello.add(squadraOspiteField);
		pannello.add(giornoLabel);
		pannello.add(giornoField);
		pannello.add(meseLabel);
		pannello.add(meseField);
		pannello.add(annoLabel);
		pannello.add(annoField);
		pannello.add(oraLabel);
		pannello.add(oraField);
		pannello.add(minLabel);
		pannello.add(minField);
		
		pannello.add(nomeStadioLabel);
		pannello.add(stadioComboBox);
		
		pannello.setBackground(Color.GREEN);
		
		pannello.add(prezzoLabel);
		pannello.add(prezzoField);
		pannello1.add(bottone1);
		pannello1.add(bottoneInserisciPartita);
		
		pannello1.setBackground(Color.GREEN);
		
		add(pannello, BorderLayout.CENTER);
		add(pannello1, BorderLayout.SOUTH);
		
	}
}