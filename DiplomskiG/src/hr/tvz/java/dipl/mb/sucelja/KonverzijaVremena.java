package hr.tvz.java.dipl.mb.sucelja;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public interface KonverzijaVremena {

	
	static LocalDate sqlDateToLocalDate(Date sqlDate){
		LocalDate dateLocal = sqlDate.toLocalDate();
//		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//		dateLocal = dateFormater.//LocalDate.parse(dateLocal.toString(), dateFormater);
		return dateLocal;
	}
	
	static  LocalTime sqlTimeToLocalTime(Time sqlTime){
		LocalTime timeLocal = sqlTime.toLocalTime();
		DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("HH:mm");
		timeLocal = LocalTime.parse(timeLocal.toString(), dateFormater);
		return timeLocal;
	}	
	
	static  Date localDateToSqlDate(LocalDate datum) {
		//DateTimeFormatter dateFormater = DateTimeFormatter.ofPattern("yyyy.dd.MM");
		//datum = LocalDate.parse(datum.toString(), dateFormater);
		Date sqlDate = Date.valueOf(datum); 		
		return sqlDate;
	}
	
	static  Time localTimeToSqlTime(LocalTime vrijeme) {
		//DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm:ss");
		//vrijeme = LocalTime.parse(vrijeme.toString(), timeFormater);
		Time sqlTime = Time.valueOf(vrijeme); 		
		return sqlTime;
	}
	
	static LocalTime stringToLocalTime(String sati, String min) {
		String vrijeme = sati +":"+min+":00";
		DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime pocetnoVrijeme = LocalTime.parse(vrijeme, timeFormater);
		return pocetnoVrijeme;
	}
	
	
	
	/**
	 * 	DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm");
		LocalDate pocetniDat = pocetniDatum.getValue();		
		LocalTime pocetnoVrijeme = LocalTime.parse(vrijemePocet.getText(), timeFormater);
		LocalDateTime pocetak = LocalDateTime.of(pocetniDat, pocetnoVrijeme);
		
		
	 * Date sqlDate = dohvatiPodatke.getDate("kraj_d"); //metoda (sucelje..konverzija)promjena 	sql date to localdate.. i obratno..
			datum = sqlDate.toLocalDate();
	 * 
	 * 
	 * default String izracunTrajanjaDana(LocalDate pocetakDatum, LocalTime pocetakVrijeme, LocalDate krajDatum, LocalTime krajVrijeme) {
		
		String trajanje = "";
		LocalDateTime pocetakIncidenta = LocalDateTime.of(pocetakDatum, pocetakVrijeme );
		LocalDateTime krajIncidenta = LocalDateTime.of(krajDatum, krajVrijeme );
		
		Duration razlikaSati = Duration.between(pocetakIncidenta, krajIncidenta);
		//-----------------------------------------------------
		long daniUkupno = Math.abs(razlikaSati.toDays());
		trajanje = String.valueOf(daniUkupno);
		
		return trajanje;
	}
	 */
	
	
	
}
