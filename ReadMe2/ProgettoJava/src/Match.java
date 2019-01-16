/*
 * Classe che serve ad esaminare le partite che devono essere effettuate 
 * con i vari ritorni definiti dalle get
 */

import java.util.ArrayList;

public class Match {
	// variabili di istanza
	private String date;
	private int match_start;
	private String home_team;
	private String away_team;
	private String stadium;
	private ArrayList<Match> matchies; 
	/*metodo costruttore
	 * @param indate= data della partita
	 * @param hour= ora di riferimento d'inizio
	 * @param home= nome della squadra di casa
	 * @param away= nome della squadra ospite
	 * @param inStadium= nome dello stadio dove si disputa la partita
	 */
	public Match(String indate, int hour, String home, String away, String inStadium){
		date=indate;
		match_start=hour;
		home_team=home;
		away_team=away;
		stadium= inStadium;
		matchies= new ArrayList<>();
	}
	/*metodo che ritorna la data di riferimento della partita di risalto
	 * @return data= cioè la data di riferimento
	 */
	public String date(){
		return date;
	}
	/*metodo che ritorna l'ora d'inizio di incontro della partita
	 * @return l'ora di partenza della partita
	 */
	public int getMatch_start(){
		return match_start;
	}
	/*metodo che ritorna il nome della squadra di casa di riferimento
	 * @return squadra casa= la squadra che disputa la partita in casa
	 */
	public String getHome_team(){
		return home_team;
	}
	/*meto che ritorna il nome della squadra ospite di riferimento
	 * @return squadra ospite= la squadra che disputa la partita fuori casa
	 */
	public String getAway_tram(){
		return away_team;
	}
	/*metodo che ritorna il nome dello stadio
	 * @return nome dello stadio di riferimento
	 */
	public String getstadium(){
		return stadium;
	}
}
