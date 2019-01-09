package hr.tvz.java.dipl.mb.entitet;

import java.time.LocalDate;
import java.time.LocalTime;
import hr.tvz.java.dipl.mb.enumeracije.KategorijeIncidenata;
import hr.tvz.java.dipl.mb.enumeracije.PrioritetiIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.VrstaAlarma;
import hr.tvz.java.dipl.mb.sucelja.TrajanjeIncidenta;

public class KrajIncidenta extends PocetakIncidenta implements TrajanjeIncidenta{
	
	private LocalDate krajDatum;
	private LocalTime krajVrijeme;
	//private List<String> trajanje;
	
	private String trajanjeDana;
	private String trajanjeVrijeme;
	
	public KrajIncidenta(LocalDate krajDatum, LocalTime krajVrijeme, int brojNaloga, KategorijeIncidenata katIncid,
			String zahUreðaj,VrstaAlarma vrstaAlarma,String napomena,String incidRjesa, PrioritetiIncidenta priotit,
			LocalDate pocetakDatum, LocalTime pocetakVrijeme) {
		
		super(brojNaloga, katIncid, zahUreðaj, vrstaAlarma, napomena, incidRjesa, priotit,pocetakDatum, pocetakVrijeme);
		this.krajDatum = krajDatum;		
		this.krajVrijeme = krajVrijeme;
		this.trajanjeDana = izracunTrajanjaDana(pocetakDatum, pocetakVrijeme, krajDatum, krajVrijeme);
		this.trajanjeVrijeme = izracunTrajanjaSati(pocetakDatum, pocetakVrijeme, krajDatum, krajVrijeme);
		setRijesen("DA");
	}
	
	public LocalDate getKrajDatum() {
		return krajDatum;
	}
	
	public LocalTime getKrajVrijeme() {
		return krajVrijeme;
	}
	
	public String getTrajanjeDana() {
		return trajanjeDana;
	}
	
	public String getTrajanjeVrijeme() {
		return trajanjeVrijeme;
	}
	
}
