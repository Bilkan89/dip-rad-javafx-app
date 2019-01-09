package hr.tvz.java.dipl.mb.entitet;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class VrijemeIncidenta {

	private LocalDate datumPocetka; // = LocalDate.now();
	private DateTimeFormatter formatDatuma = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
	private String formatDatumPocetka = datumPocetka.format(formatDatuma);

	private LocalTime satPocetka; // = LocalTime.now();
	private DateTimeFormatter formatSata = DateTimeFormatter.ofPattern("HH:mm"); 
	private String formatSataPocetka = satPocetka.format(formatSata);
	
	
	private LocalDate datumZavrsetka; // = LocalDate.now();
	private String formatDatumZavrsetka = datumZavrsetka.format(formatDatuma);
	
	private LocalTime satZavrsetka; // = LocalTime.now();
	private String formatSataZavrsetka = satZavrsetka.format(formatSata);
	
	
	
	public VrijemeIncidenta(LocalDate datumPocetka, LocalTime satPocetka) {
		//staviti localDate.now()
		this.datumPocetka = datumPocetka;
		this.satPocetka = satPocetka;
	}

	public VrijemeIncidenta(LocalTime satZavrsetka,LocalDate datumZavrsetka) {
		//staviti localTime.now()
		this.datumZavrsetka = datumZavrsetka;
		this.satZavrsetka = satZavrsetka;
	}
	
	public int TrajanjeIncidenta(){
		
		Period trajanje = Period.between(datumPocetka, datumZavrsetka) ;
		int trajanjeGodina = trajanje.getYears();
		int trajanjeMjeseci = trajanje.getMonths();
		int trajanjeDana = trajanje. getDays();
		
		Duration ns = Duration.between(satPocetka, satZavrsetka);
		
		long vrijemeMinuta = ns.toMinutes() / 60;
		
		
//		if (ns.toMinutes() > 60){
//			vrijemeMinuta = ns.toMinutes() - 60;
//		}else {
//			
//			vrijemeMinuta = ns.toMinutes();
//		}
		
		System.out.println("Razlika izmeðu "+satPocetka+" i "+satZavrsetka+" je "+ ns.toHours() +" sat i "+ vrijemeMinuta+" minuta" );
		
		
		return 0;
	}
	
	
	
	public String getFormatDatumPocetka() {
		return formatDatumPocetka;
	}

	public String getFormatSataPocetka() {
		return formatSataPocetka;
	}

	public String getFormatDatumZavrsetka() {
		return formatDatumZavrsetka;
	}

	public String getFormatSataZavrsetka() {
		return formatSataZavrsetka;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
