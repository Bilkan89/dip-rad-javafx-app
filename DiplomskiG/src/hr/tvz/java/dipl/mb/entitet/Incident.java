package hr.tvz.java.dipl.mb.entitet;

import hr.tvz.java.dipl.mb.enumeracije.PrioritetiIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.VrstaAlarma;

import hr.tvz.java.dipl.mb.enumeracije.KategorijeIncidenata;
import hr.tvz.java.dipl.mb.sucelja.ProvjeraPodataka;;


public class Incident implements ProvjeraPodataka{
	
	//Incidente ne možeš brisati samo možeš urediti i zatvroriti!	
	
	private int brojNaloga; //samo pozitivni brojevi
	private KategorijeIncidenata kategIncidenta;
	private String zahvacenUredaji;
	private VrstaAlarma tipAlarma;
	private String napomena;
	private String incidentRjesava;
	private String rijesen;
	private PrioritetiIncidenta prioriteti;	
	
	public Incident(int brojNaloga, KategorijeIncidenata katIncid, String zahUreðaj, VrstaAlarma vrstaAlarma, String napomena,
			String incidRjesa, PrioritetiIncidenta priotit) {

		this.brojNaloga = samoPozitivniBr(brojNaloga);
		this.kategIncidenta = katIncid;
		this.zahvacenUredaji = zahUreðaj;
		this.tipAlarma = vrstaAlarma;
		this.napomena = napomena;
		this.incidentRjesava = incidRjesa;
		this.rijesen = "NE";
		this.prioriteti = priotit;		
	}	
	public int getBrojNaloga() {
		return brojNaloga;
	}
	public KategorijeIncidenata getKategIncidenta() {
		return kategIncidenta;
	}
	public String getZahvacenUredaji() {
		return zahvacenUredaji;
	}
	public VrstaAlarma getTipAlarma() {
		return tipAlarma;
	}
	public String getNapomena() {
		return napomena;
	}
	public String getIncidentRjesava() {
		return incidentRjesava;
	}
	public String getRijesen() {
		return rijesen;
	}	
	protected void setRijesen(String rijesen) {
		this.rijesen = rijesen;
	}
	public PrioritetiIncidenta getPrioriteti() {
		return prioriteti;
	}

	
	
	
	
}
