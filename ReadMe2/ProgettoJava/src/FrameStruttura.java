import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Transparency;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/*
 * Frame iniziale 
 */
public class FrameStruttura extends JFrame
{
	private Struttura struttura;
	private JLabel nickLabel, passLabel;
	private JTextField nickField, passField;
	private JButton clienteButton, gestoreButton, loginButton;
	
	public FrameStruttura(Struttura struttura)
	{
		this.struttura = struttura;
		
		setTitle("Struttura Sportiva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension f = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(f.width/2-130, f.height/2-100, 260, 200);
		
		
		this.setForeground(Color.ORANGE);
		this.setLocation(520,350);
		
		createField();
		createButton();
		createPanel();
		
		this.setVisible(true);
	}
	
	
	public void createField()
	{ 
		nickLabel = new JLabel("NickName ");
		passLabel = new JLabel("Password ");
			
		nickField = new JTextField(10); //10 = lunghezza del rettangolo in cui inserire i dati
		passField = new JPasswordField(10); //10 = lunghezza del rettangolo in cui inserire i dati
			
		nickField.setText("");
		passField.setText("");
	}
	
	public void createButton()
	{
		clienteButton = new JButton("Cliente");
		
		class AddClienteButtonListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent event) 
			{
				String nickName = nickField.getText();
				String passName = passField.getText();
				
				ArrayList<Cliente> listaclienti = struttura.getListaClienti();
				for(Cliente record : listaclienti)
				{
					if(record.getNome().equals(nickName) && record.getPassword().equals(passName))
					{
						dispose();
						JFrame cliente = new menuCliente(record, struttura);
						
					}
				}
					
			}
		}
			
		ActionListener listener = new AddClienteButtonListener();
		clienteButton.addActionListener(listener);
			
			
		gestoreButton = new JButton("Gestore");
		
		class AddGestoreButtonListener implements ActionListener
			{
				public void actionPerformed(ActionEvent event) 
				{
					String nickName = nickField.getText();
					String passName = passField.getText();
					
					if (nickName.equals("Admin") && passName.equals("struttura"))
						{
							dispose();
							JFrame gestore = new MenuGestore(struttura);
							
						}
				}
			}
			
			ActionListener listener1 = new AddGestoreButtonListener();
			gestoreButton.addActionListener(listener1);
		
			
			
		loginButton = new JButton("Iscriviti");	
		
		class AddLoginButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event) 
			{
					try 
					{
						String nickName = nickField.getText();
						String passName = passField.getText();
					
						struttura.addCliente(nickName, passName);
	
						JOptionPane.showMessageDialog(null,"Registrazione Effettuata");
						
						
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
						JOptionPane.showMessageDialog(null,"NickName già utilizzato");
					}		
			}
		}
		
		ActionListener listener2 = new AddLoginButtonListener();
		loginButton.addActionListener(listener2);
	}
		
		
	public void createPanel()
	{
		JPanel panel = new JPanel();
		
		panel.setBackground(Color.ORANGE);
		panel.add(nickLabel);
		panel.add(nickField);
		panel.add(passLabel);
		panel.add(passField);
		panel.add(clienteButton);
		panel.add(gestoreButton);
		panel.add(loginButton);

		add(panel, BorderLayout.CENTER);
		
	}
}