import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/*
 * Disegno del campo da calcio
 */
public class DisegnoCampo 
{
	public DisegnoCampo()
	{
		
	}
	
	public void draw(Graphics2D g2)
	{
		final int origineCampoX = 350;
		final int origineCampoY = 100;
		
		Rectangle campo = new Rectangle(origineCampoX, origineCampoY, 600, 300);
		
		Rectangle s1 = new Rectangle(origineCampoX - 1, origineCampoY +  75, 75, 150);
		Rectangle s2 = new Rectangle(origineCampoX - 1, origineCampoY + 113, 38, 75);
	
		Point2D.Double p1 = new Point2D.Double(origineCampoX + 300, origineCampoY);
		Point2D.Double p2 = new Point2D.Double(origineCampoX + 300, origineCampoY + 300);
		Line2D.Double lineaDiMezzo = new Line2D.Double(p1, p2);
		
		Ellipse2D.Double anelloGrandeCentro = new Ellipse2D.Double(origineCampoX + 262, origineCampoY + 113, 75, 75);
		Ellipse2D.Double anelloPienoCentro = new Ellipse2D.Double(origineCampoX + 296 , origineCampoY + 147 , 7, 7);
		
		Rectangle d1 = new Rectangle(origineCampoX + 525, origineCampoY + 75, 75, 150);
		Rectangle d2 = new Rectangle(origineCampoX + 562, origineCampoY + 113, 38, 75);
		
		
		g2.setColor(Color.WHITE);
		g2.draw(campo);
		
		g2.setColor(Color.GREEN);
		g2.fill(campo);
	    
	    g2.setColor(Color.WHITE);
	    g2.draw(s1);
	    g2.draw(s2);
	    g2.draw(lineaDiMezzo);
	    g2.draw(anelloGrandeCentro);
	    g2.draw(anelloPienoCentro);
	    g2.fill(anelloPienoCentro);
	    g2.draw(d1);
	    g2.draw(d2);
	    
	
	}
}