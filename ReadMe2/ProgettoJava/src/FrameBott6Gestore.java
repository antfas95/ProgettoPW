import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Frame per visualizzare li incassi dei singoli stadi e dell\'intera strutura
 */
public class FrameBott6Gestore extends JFrame
{
	private Struttura struttura;
	private JLabel stadioLabel, stadioIncassoLabel, strutturaLabel;
	private JComboBox stadioComboBox;
	private ArrayList<Stadio> listaStadi;
	private JButton bottoneIndietro;
	private ActionListener listener;
	
	public FrameBott6Gestore(Struttura struttura)
	{
		this.struttura = struttura;
		
		setTitle("Menu Visualizzazione incassi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-148, f.height/2-104, 296, 209);
		
		
		class ChoiceListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
				setText();
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
		stadioLabel = new JLabel("Selezionare uno stadio");
		stadioIncassoLabel = new JLabel("Incasso Dello Stadio: ");
		
		strutturaLabel = new JLabel("Incasso della struttura: " + struttura.getIncassoTotale());
		
		System.out.println("Incasso della struttura: " + struttura.getIncassoTotale());
		
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
		
	}
	
	public void createPanel()
	{
		JPanel pannelloLabel = new JPanel();
		
		pannelloLabel.add(stadioLabel);
		pannelloLabel.add(stadioComboBox);
		pannelloLabel.add(stadioIncassoLabel);
		pannelloLabel.add(strutturaLabel);
	
		
		JPanel pannelloBottoni = new JPanel();
		
		pannelloBottoni.add(bottoneIndietro);
		
		pannelloLabel.setBackground(Color.GREEN);
		pannelloBottoni.setBackground(Color.GREEN);
		
		add(pannelloLabel, BorderLayout.CENTER);
		add(pannelloBottoni, BorderLayout.SOUTH);
	}
	
	public void setText()
	{
		
		Stadio stadio = listaStadi.get(stadioComboBox.getSelectedIndex());
		
		stadioIncassoLabel.setText("Incasso dello stadio:" + stadio.getIncassoStadio());
	}
}