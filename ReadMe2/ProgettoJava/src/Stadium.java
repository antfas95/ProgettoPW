/*
 * Classe che prende in considerazione lo stadio
 */
public class Stadium {
	//variabili di istanza
	private int rows;
	private int colws;
	private int [][] Number_seats;
	private int posti_sedere;
	/*metodo costruttore
	 * inizializzazione della matrrice di riferimento che si intende usare
	 * @param righe= numero di righe presenti nello stadio
	 * @param colonne= numero di colonne presenti nello stadio
	 */
	public Stadium(int righe, int colonne){
		rows=righe;
		colws=colonne;
		Number_seats= new int[rows][colws];
		posti_sedere= rows*colws;
	}
}
