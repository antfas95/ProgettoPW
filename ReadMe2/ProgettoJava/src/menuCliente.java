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
 * Frame menù cliente
 */
public class menuCliente extends JFrame
{
	private Cliente cliente;
	private Struttura struttura;
	
	public menuCliente(Cliente cliente, Struttura struttura)
	{
		this.cliente = cliente;
		this.struttura = struttura;
		disegnamenuCliente();
	}
	
	public void disegnamenuCliente()
	{
		
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-165, f.height/2-100, 330, 200);
		setTitle("MenuCliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JButton bottoneVisualizzaPartite = new JButton("Menu visualizzatore partite");
		
		class AddBottone1Listener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBottClienteVisualizzaParite = new FrameBottClienteVisualizzaPartite(cliente,struttura);
				dispose();
			}
				
		}
		
		ActionListener listener1 = new AddBottone1Listener();
		bottoneVisualizzaPartite.addActionListener(listener1);
		
		
		JButton bottoneListaPartite = new JButton("Menu lista partite non ancora iniziate");
		
		class AddBottoneListaPartiteListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBottClienteListaPartite = new FrameBottClienteListaPartite(cliente, struttura);
				dispose();
			}
		}
		
		ActionListener listenerListaPartite = new AddBottoneListaPartiteListener();
		bottoneListaPartite.addActionListener(listenerListaPartite);
		
		
		JButton bottonePrenotazioniEff = new JButton("Prenotazioni effettuate");
		
		class AddBottonePrenotazioniEffListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBottClientePrenotazioniEff = new FrameBottClientePrenotazioniEff(cliente, struttura);
				dispose();
			}
		}
		
		ActionListener listenerPrenotazioni = new AddBottonePrenotazioniEffListener();
		bottonePrenotazioniEff.addActionListener(listenerPrenotazioni);
		
		
		JButton bottoneAcquistiEff = new JButton("Acquisti effettuati");
		
		class AddBottoneAcquistiEffListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameBottClienteAcquistiEff = new FrameBottClienteAcquistiEff(cliente, struttura);
				dispose();
			}
		}
		
		ActionListener listenerAcquisti = new AddBottoneAcquistiEffListener();
		bottoneAcquistiEff.addActionListener(listenerAcquisti);
		
		
		JButton bottoneIndietro = new JButton("INDIETRO");
		
		class AddBottoneIndietroListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameStruttura = new FrameStruttura(struttura);
            	dispose();

			}
		}
		
		ActionListener listenerIndietro = new AddBottoneIndietroListener();
		bottoneIndietro.addActionListener(listenerIndietro);
		
		
		
	
		JPanel pannello = new JPanel();
		
		
		pannello.add(bottoneVisualizzaPartite);
		pannello.add(bottoneListaPartite);
		pannello.add(bottonePrenotazioniEff);
		pannello.add(bottoneAcquistiEff);
		add(bottoneIndietro, BorderLayout.SOUTH);
		
		pannello.setBackground(Color.ORANGE);
		
		add(pannello, BorderLayout.CENTER);
	
		
		setVisible(true);
		
	}


}