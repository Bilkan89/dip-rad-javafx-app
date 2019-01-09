package hr.tvz.java.dipl.mb.entitet;

import java.time.LocalDate;
import java.time.LocalTime;

import hr.tvz.java.dipl.mb.enumeracije.KategorijeIncidenata;
import hr.tvz.java.dipl.mb.enumeracije.PrioritetiIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.VrstaAlarma;

public class PocetakIncidenta extends Incident{

	private LocalDate pocetakDatum;
	private LocalTime pocetakVrijeme;
	
	public PocetakIncidenta(int brojNaloga, KategorijeIncidenata katIncid,String zahUreðaj,VrstaAlarma vrstaAlarma,
			String napomena,String incidRjesa, PrioritetiIncidenta priotit, LocalDate pocetakDatum, LocalTime pocetakVrijeme) {
		
		super(brojNaloga, katIncid, zahUreðaj, vrstaAlarma, napomena, incidRjesa, priotit);
		this.pocetakDatum = pocetakDatum;		
		this.pocetakVrijeme = pocetakVrijeme;
	}

	public LocalDate getPocetakDatum() {
		return pocetakDatum;
	}

	public LocalTime getPocetakVrijeme() {
		return pocetakVrijeme;
	}
	

	
	
	
}
