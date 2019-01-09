package hr.tvz.java.dipl.mb.sucelja;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public interface TrajanjeIncidenta {

		
	default String izracunTrajanjaDana(LocalDate pocetakDatum, LocalTime pocetakVrijeme, LocalDate krajDatum, LocalTime krajVrijeme) {
		
		String trajanje = "";
		LocalDateTime pocetakIncidenta = LocalDateTime.of(pocetakDatum, pocetakVrijeme );
		LocalDateTime krajIncidenta = LocalDateTime.of(krajDatum, krajVrijeme );
		
		Duration razlikaSati = Duration.between(pocetakIncidenta, krajIncidenta);
		//-----------------------------------------------------
		long daniUkupno = Math.abs(razlikaSati.toDays());
		trajanje = String.valueOf(daniUkupno);
		
		return trajanje;
	}
	
	default String izracunTrajanjaSati(LocalDate pocetakDatum, LocalTime pocetakVrijeme, LocalDate krajDatum, LocalTime krajVrijeme) {
		
		String trajanje = "";
		LocalDateTime pocetakIncidenta = LocalDateTime.of(pocetakDatum, pocetakVrijeme );
		LocalDateTime krajIncidenta = LocalDateTime.of(krajDatum, krajVrijeme );
		
		Duration razlikaSati = Duration.between(pocetakIncidenta, krajIncidenta);
		//-----------------------------------------------------
		long daniUkupno = Math.abs(razlikaSati.toDays());
		long satiUkupno = Math.abs(razlikaSati.toHours());
		long minUkupno = Math.abs(razlikaSati.toMinutes());
		//-----------------------------------------------------
		long ostatakSati = satiUkupno - (daniUkupno * 24);
		long ostatakMinuta = minUkupno - (satiUkupno * 60);
		//-----------------------------------------------------	
		
		trajanje = String.valueOf(ostatakSati)+":"+String.valueOf(ostatakMinuta);
		//System.out.println(trajanje);
		
		return trajanje;
	}
	/**
	 * 
	 * DateTimeFormatter timeFormater = DateTimeFormatter.ofPattern("HH:mm");
		LocalDate pocetniDat = pocetniDatum.getValue();		
		LocalTime pocetnoVrijeme = LocalTime.parse(vrijemePocet.getText(), timeFormater);
		
		LocalDateTime pocetak = LocalDateTime.of(pocetniDat, pocetnoVrijeme);
				
				
		LocalDate krajnjiDatum = krajDatum.getValue();
		LocalTime krajnjeVrijeme = LocalTime.parse(vrijemeKraj.getText(), timeFormater);
		LocalDateTime kraj = LocalDateTime.of(krajnjiDatum, krajnjeVrijeme);
		
		Duration trajanje = Duration.between(pocetak, kraj);
		long satiUkupno = Math.abs(trajanje.toHours());
		long daNAUkupno = Math.abs(trajanje.toDays());
		long minUkupno = Math.abs(trajanje.toMinutes());
		
		long ostatakSati = satiUkupno - (daNAUkupno * 24);
		
		long ostatakMinuta = minUkupno - (satiUkupno * 60);
		
		labelIzracun.setText("ukupno sati:"+satiUkupno+" Dana: "+daNAUkupno+" Sati: "+ostatakSati+"Min: "+ostatakMinuta);
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
	
//	
//	public default List<String> Izracun(LocalDateTime pocetak, LocalDateTime kraj){
////ALGORITAM PREUZET SA http://stackoverflow.com/questions/25747499/java-8-calculate-difference-between-two-localdatetime 
////ALOGIRATM JE MODIFICIRAN ZA POTREBE PROJEKTA
//		
//		LocalDateTime tempDateTime = LocalDateTime.from( pocetak );
////		Calculates the amount of time until another date-time in terms of the specified unit. 
////		.until() - This calculates the amount of time between two LocalDateTime objects in terms of a single TemporalUnit. 
////		The start and end points are this and the specified date-time.
//
//		long years = tempDateTime.until( kraj, ChronoUnit.YEARS); 
//		tempDateTime = tempDateTime.plusYears( years );
//
//		long months = tempDateTime.until( kraj, ChronoUnit.MONTHS);
//		tempDateTime = tempDateTime.plusMonths( months );
//
//		long days = tempDateTime.until( kraj, ChronoUnit.DAYS);
//		tempDateTime = tempDateTime.plusDays( days );
//
//
//		long hours = tempDateTime.until( kraj, ChronoUnit.HOURS);
//		tempDateTime = tempDateTime.plusHours( hours );
//
//		long minutes = tempDateTime.until( kraj, ChronoUnit.MINUTES);
//		tempDateTime = tempDateTime.plusMinutes( minutes );
//
//		System.out.println( years + " years " + 
//		        months + " months " + 
//		        days + " days " +
//		        hours + " hours " +
//		        minutes + " minutes ");
//		
//		long[] izracun = {years,months,days,hours,minutes,};
//		List<String> filtrirano = new ArrayList<String>();
//						
////		for (long vijednost : izracun ) {
////			if(vijednost != 0){
////				filtrirano.add(String.valueOf(vijednost)); 
////			}				
////		}	
//		
//		for (int i = 0; i < izracun.length; i++) {
//			if(i == 0 && izracun[i] > 0)
//				filtrirano.add(izracun[0]+" godina ");
//			if(i == 1 && izracun[i] > 0)
//				filtrirano.add(izracun[1]+" mjesec ");
//			if(i == 2 && izracun[i] > 0)
//				filtrirano.add(izracun[2]+" dana ");
//			if(i == 3 && izracun[i] > 0)
//				filtrirano.add(izracun[3]+" sati ");
//			if(i == 4 && izracun[i] > 0)
//				filtrirano.add(izracun[4]+" minuta ");
//		}
//		
//		
//	return filtrirano;
//	}
//	
	
	
}
