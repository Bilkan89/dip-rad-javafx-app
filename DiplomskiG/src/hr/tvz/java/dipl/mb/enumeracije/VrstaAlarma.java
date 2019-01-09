package hr.tvz.java.dipl.mb.enumeracije;

import java.util.ArrayList;
import java.util.List;

public enum VrstaAlarma {

	Critical(1),
	Major(2),
	Minor(3);
	
	private int vrstaAlarmaId;
	
	private VrstaAlarma(int vrstaAlarmaId) {
		this.vrstaAlarmaId = vrstaAlarmaId;
	}
	
	public int getVrstaAlarmaId() {
		return vrstaAlarmaId;
	}
	
	public void setVrstaAlarmaId(int vrstaAlarmaId) {
		this.vrstaAlarmaId = vrstaAlarmaId;
	}
	
	public static List<VrstaAlarma> dohvatiVrijednosti(){			
		List<VrstaAlarma> lista = new ArrayList<>();			
		for(VrstaAlarma elementi : VrstaAlarma.values()){
			lista.add(elementi);
		}			
		return lista;
	}

	public static VrstaAlarma vratiAlarm(int vrsta_id) {
		if (vrsta_id == 1) {
			return VrstaAlarma.Critical;
		}else if (vrsta_id == 2){
			return VrstaAlarma.Major;
			}else {
				return VrstaAlarma.Minor;}
	}
	
	public static int vratiIntAlarm(VrstaAlarma vrstA) {
		if (vrstA.equals(Critical)) {
			return 1;
		}else if (vrstA.equals(Major)){
			return 2;
			}else {
				return 3;}
		
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
	

