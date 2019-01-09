package hr.tvz.java.dipl.mb.enumeracije;

import java.util.ArrayList;
import java.util.List;

public enum KategorijeIncidenata {

	MRE�NI(1),
	POSLU�ITELJSKI(2),
	TELEKOMUNIKACIJSKI(3);

	private int kategIncid;
		
	private KategorijeIncidenata(int katIncId){
		this.kategIncid = katIncId;
	}
	
	public int getKategIncid() {
		return kategIncid;
	}
	
	public void setKategIncid(int kategIncid) {
		this.kategIncid = kategIncid;
	}

	public static List<KategorijeIncidenata> dohvatiVrijednosti(){
		
		List<KategorijeIncidenata> listaVrsteIncidenata = new ArrayList<>();
		
		for(KategorijeIncidenata elementi : KategorijeIncidenata.values()){
			listaVrsteIncidenata.add(elementi);
		}
		
		return listaVrsteIncidenata;
	}
	
	public static KategorijeIncidenata vratiKategoriju(int kategorija_id) {
		if(kategorija_id == 1) {
			return KategorijeIncidenata.MRE�NI;
		}else if(kategorija_id == 2) {
			return KategorijeIncidenata.POSLU�ITELJSKI;
			}else {
				return KategorijeIncidenata.TELEKOMUNIKACIJSKI;
			}		
	}
	
	public static int vratiIntKategorije(KategorijeIncidenata kat) {
		if(kat.equals(MRE�NI)) {
			return 1;
		}else if(kat.equals(POSLU�ITELJSKI)) {
			return 2;
			}else {
				return 3;
			}
	}
}
