/*
 * Classe che mette in risalto il biglietto di riferimento tenendo traccia del
 * prezzo di risalta
 */
public class Ticket {
	//variabili di istanza
	private double price;
	private int number_reservations;
	private String section;
	private String number;
	/*metodo costruttore
	 * @parama price= il prezzo del biglietto di riferimento
	 * @param reservation= numero posti prenotati
	 * @param Asection= la sezione dove è allocato il biglietto
	 * @param anumber= numero del posto della fil di riferimento
	 */
	public Ticket(double aprice, int reservation, String Asection, String anumber){
		price=aprice;
		number_reservations= reservation;
		section=Asection;
		number=anumber;
	}
	/*metodo che ritorna il prezzo del biglietto
	 * @return il prezzo di riferimento per quella data partita
	 */
	public double getPrice(){
		return price;
	}
	/*metodo che ritorna il numero di posti prenotati nello stadio
	 * @return numeri posti prenotati
	 */
	public int getreservation(){
		return number_reservations;
	}
	/*metodo che ritorna la sezione di riferimento
	 * @return sezione di riferimento con il rispettivo numero e fila
	 */
	public String getSection(){
		String x= section.concat(number);
		return x;
	}
}
