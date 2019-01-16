import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * Frame del menù dello sconto
 */
public class FrameBott3Gestore extends JFrame
{
	private Struttura struttura;
	private JButton bottoneIndietro, bottoneSconto1, bottoneSconto2, bottoneSconto3;
	
	public FrameBott3Gestore(Struttura struttura)
	{
		
		this.struttura = struttura;
		
		setTitle("Menu Sconto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-225, f.height/2-80, 551, 160);
		
		
		createButton();
		
		createPanel();
		
		setVisible(true);
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
		
		
		bottoneSconto1 = new JButton("Sconto riguardante una singola partita");
		
		class AddBottoneSconto1Listener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameMenuSconto1 = new  FrameMenuSconto1(struttura);
				dispose();
			}
		}
			
		ActionListener listener1 = new AddBottoneSconto1Listener();
		bottoneSconto1.addActionListener(listener1);
		
		
		bottoneSconto2 = new JButton("Sconto partite di uno Stadio");
		
		class AddBottoneSconto2Listener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameMenuSconto2 = new  FrameMenuSconto2(struttura);
				dispose();
			}
		}
			
		ActionListener listener2 = new AddBottoneSconto2Listener();
		bottoneSconto2.addActionListener(listener2);
		
		
		bottoneSconto3 = new JButton("Sconto in base ad una fascia oraria giornaliera");
		
		class AddBottoneSconto3Listener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				JFrame frameMenuSconto3 = new  FrameMenuSconto3(struttura);
				dispose();
			}
		}
			
		ActionListener listener3 = new AddBottoneSconto3Listener();
		bottoneSconto3.addActionListener(listener3);
		
		
	}
	
	public void createPanel()
	{
		JPanel pannello = new JPanel();
		
		pannello.add(bottoneSconto1);
		pannello.add(bottoneSconto2);
		pannello.add(bottoneSconto3);
		pannello.setBackground(Color.GREEN);	
		
		
		this.add(bottoneIndietro, BorderLayout.SOUTH);
		this.add(pannello, BorderLayout.CENTER);
	}
}