package hr.tvz.java.dipl.mb.enumeracije;

import java.util.ArrayList;
import java.util.List;

public enum PrioritetiIncidenta {

	Visoki(1),
	Srednji(2),
	Niski(3);
	
	private int prioritetiIncidentaId;
	
	private PrioritetiIncidenta(int jacinaIncidentaId) {
		this.prioritetiIncidentaId = jacinaIncidentaId;
	}
	
	public int getprioritetiIncidentaId() {
		return prioritetiIncidentaId;
	}
	
	public void setprioritetiIncidentaId(int jacinaIncidentaId) {
		this.prioritetiIncidentaId = jacinaIncidentaId;
	}
	
	
	public static List<PrioritetiIncidenta> listaVrijednosti() {
		
		List<PrioritetiIncidenta> lista = new ArrayList<PrioritetiIncidenta>();
		for(PrioritetiIncidenta elementi : PrioritetiIncidenta.values()){
			lista.add(elementi);
		}		
		return lista;
	}

	public static PrioritetiIncidenta vratiPrioritet(int prioritet_id) {
		if(prioritet_id == 1) {
			return PrioritetiIncidenta.Visoki;
		}else if (prioritet_id == 2) {
				return	PrioritetiIncidenta.Srednji;
			}else {
				return PrioritetiIncidenta.Niski;
			}		
	}
	
	public static int vratiIntPrioriteta(PrioritetiIncidenta prior) {
		if(prior.equals(Visoki)) {
			return 1;
		}else if (prior.equals(Srednji)) {
				return 2;
			}else {
				return 3;
			}		
	}
	
	/*
	 * 
	 * 
	 * public static KategorijeIncidenata vratiKategoriju(int kategorija_id) {
		if(kategorija_id == 1) {
			return KategorijeIncidenata.MREŽNI;
		}else if(kategorija_id == 2) {
			return KategorijeIncidenata.POSLUŽITELJSKI;
			}else {
				return KategorijeIncidenata.TELEKOMUNIKACIJSKI;
			}		
	}
	
	public static int vratiIntKategorije(KategorijeIncidenata kat) {
		if(kat.equals(MREŽNI)) {
			return 1;
		}else if(kat.equals(POSLUŽITELJSKI)) {
			return 2;
			}else {
				return 3;
			}
	}
	 */
}
