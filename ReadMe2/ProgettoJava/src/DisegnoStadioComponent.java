import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;

import javax.swing.JComponent;
import javax.swing.JPanel;

/*
 * Viene disegnato il componente campo
 */
public class DisegnoStadioComponent extends JComponent
{
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		DisegnoCampo campo1 = new DisegnoCampo();
		
		campo1.draw(g2);
		
	}
}