import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFrame;

public class Main 
{
	public static void main(String[] args) throws ClassNotFoundException, IOException
	{
		try
		{
			Struttura struttura = new Struttura();
			JFrame frameStruttura = new FrameStruttura(struttura.caricaStruttura());
			
			
		}
		catch(FileNotFoundException exception)
		{
			Struttura struttura = new Struttura();
			JFrame frameStruttura = new FrameStruttura(struttura);
		}
	}
	
	

}