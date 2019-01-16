import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Frame per l inserimento di un nuovo stadio
 */
public class FrameBottInserisciStadio extends JFrame
{
	private Struttura struttura;
	private JLabel nomeStadiolabel, capienzaStadioLabel;
	private JTextField nomeStadioField, capienzaStadioField;
	private JButton bottoneIndietro, bottoneAggiungiStadio;
	
	public FrameBottInserisciStadio(Struttura struttura)
	{
		this.struttura = struttura;
		
		setTitle("Menu Inserisci Stadio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-130, f.height/2-85, 260, 170);
		
		
		
		createLabel();
		createButton();
		createPanel();
		
		setVisible(true);
		
	}
	
	public void createLabel()
	{
		nomeStadiolabel = new JLabel("Inserisci il nome dello stadio ");
		capienzaStadioLabel = new JLabel("Inserisci la capienza dello stadio ");
		nomeStadioField = new JTextField(10);
		capienzaStadioField = new JTextField(10);
		
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
		
		bottoneAggiungiStadio = new JButton("Aggiungi Stadio");
		
		class AddBottoneAggiungiStadioListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				try 
				{
					String nomeStadio = nomeStadioField.getText();
					int numeroPosti = Integer.parseInt(capienzaStadioField.getText());
				
					struttura.addStadio(nomeStadio, numeroPosti);
					
					struttura.salvaStruttura(struttura);
					
					JOptionPane.showMessageDialog(null, "Stadio Aggiunto");
				} 
				catch (ClassNotFoundException | IOException e) 
				{
					e.printStackTrace();
				}
				catch(RuntimeException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		}
		
		ActionListener listenerAggiungiStadio = new AddBottoneAggiungiStadioListener();
		bottoneAggiungiStadio.addActionListener(listenerAggiungiStadio);
	}
	
	public void createPanel()
	{
		JPanel pannelloLabel = new JPanel();
		JPanel pannelloBottoni = new JPanel();
		
		pannelloLabel.add(nomeStadiolabel);
		pannelloLabel.add(nomeStadioField);
		pannelloLabel.add(capienzaStadioLabel);
		pannelloLabel.add(capienzaStadioField);
		
		pannelloBottoni.add(bottoneIndietro);
		pannelloBottoni.add(bottoneAggiungiStadio);
		
		pannelloLabel.setBackground(Color.GREEN);
		pannelloBottoni.setBackground(Color.GREEN);
		
		add(pannelloLabel, BorderLayout.CENTER);
		add(pannelloBottoni, BorderLayout.SOUTH);
		
		
		
	}
}