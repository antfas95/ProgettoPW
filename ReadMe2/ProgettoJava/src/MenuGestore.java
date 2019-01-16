import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Frame menù gestore
 */
public class MenuGestore extends JFrame
{
	
	private Struttura struttura;
	
	public MenuGestore (Struttura struttura)
	{
		this.struttura = struttura;
		disegnaMenuGestore();
	}
	
	public void disegnaMenuGestore()
	{
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-338, f.height/2-150, 675, 300);
		
		setTitle("Menu Gestore");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		JButton bottone1 = new JButton("Inserisci una partita di calcio");
		class AddBottone1Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				
				JFrame frameBott1Gestore = new FrameBott1Gestore(struttura);
				dispose();
			}
				
		}
		
		ActionListener listener1 = new AddBottone1Listener();
		bottone1.addActionListener(listener1);
		
		
		JButton bottone2 = new JButton("Visualizza le partite in base alla campienza o in ordine cronologico");
		
		class AddBottone2Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBott2Gestore = new FrameBott2Gestore(struttura);
				dispose();
			}
		}
		
		ActionListener listener2 = new AddBottone2Listener();
		bottone2.addActionListener(listener2);
		
		
		JButton bottone3 = new JButton("Attivare le politiche di sconto sui biglietti");
		
		class AddBottone3Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBott3Gestore = new FrameBott3Gestore(struttura);
				dispose();
			}
		}
		
		ActionListener listener3 = new AddBottone3Listener();
		bottone3.addActionListener(listener3);
		
		
		JButton bottone4 = new JButton("Assegnare un prezzo alle partite che si svolgono in un determinato stadio");
		
		class AddBottone4Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBott4Gestore = new FrameBott4Gestore(struttura);
				dispose();
			}
		}
		
		ActionListener listener4 = new AddBottone4Listener();
		bottone4.addActionListener(listener4);
		
		
		JButton bottone5 = new JButton("Aumentare o ridurre la campienza degli stadi");
		
		class AddBottone5Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBott5Gestore = new FrameBott5Gestore(struttura);
				dispose();
			}
		}
		
		ActionListener listener5 = new AddBottone5Listener();
		bottone5.addActionListener(listener5);
		
		
		JButton bottone6 = new JButton("Visualizzare l'incasso totale per ogni stadio");
		
		class AddBottone6Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBott6Gestore = new FrameBott6Gestore(struttura);
				dispose();
			}
		}
		
		ActionListener listener6 = new AddBottone6Listener();
		bottone6.addActionListener(listener6);
		
		JButton bottoneInserisciStadio = new JButton("Inserisci Stadio");
		
		class AddBottoneInserisciStadioListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameStruttura = new FrameBottInserisciStadio(struttura);
            	dispose();
			}
		}
		
		ActionListener listenerInserisciStadio = new AddBottoneInserisciStadioListener();
		bottoneInserisciStadio.addActionListener(listenerInserisciStadio);
		
		
		JButton bottone7 = new JButton("INDIETRO");
		
		class AddBottone7Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBottInserisciStadioGestore = new FrameStruttura(struttura);;
				dispose();
			}
		}
		
		ActionListener listener7 = new AddBottone7Listener();
		bottone7.addActionListener(listener7);

		
	
		JPanel pannello = new JPanel();
		
		
		pannello.add(bottone1);
		pannello.add(bottoneInserisciStadio);
		pannello.add(bottone2);
		pannello.add(bottone3);
		pannello.add(bottone4);
		pannello.add(bottone5);
		pannello.add(bottone6);
		
		pannello.setBackground(Color.GREEN);
		
		add(bottone7, BorderLayout.SOUTH);
		add(pannello, BorderLayout.CENTER);
	
		
		setVisible(true);
		
	}


}