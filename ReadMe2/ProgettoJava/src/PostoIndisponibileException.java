
/**
 * Gestisce le eccezioni che si possono verificare quando il posto è indisponibile sia per acquisti che per prenotazioni.
 */
public class PostoIndisponibileException extends RuntimeException
{
	public PostoIndisponibileException(){};
	
	public PostoIndisponibileException(String message)
	{
		super(message);
	}
	
}