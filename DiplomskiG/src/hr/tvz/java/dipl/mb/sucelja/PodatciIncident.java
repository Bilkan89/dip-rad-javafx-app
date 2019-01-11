package hr.tvz.java.dipl.mb.sucelja;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hr.tvz.java.dipl.mb.baza.KomunikacijaSaBazom;
import hr.tvz.java.dipl.mb.entitet.Incident;
import hr.tvz.java.dipl.mb.entitet.KrajIncidenta;
import hr.tvz.java.dipl.mb.entitet.PocetakIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.KategorijeIncidenata;
import hr.tvz.java.dipl.mb.enumeracije.PrioritetiIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.VrstaAlarma;

public interface PodatciIncident {

	
	static List<Incident> dohvatiIncidente() throws Exception{
		
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		
		List<Incident> incLista = new ArrayList<Incident>();
		
		String dohvatiRješeneIncidente = "SELECT incident_pocetak.broj_naloga,pocetak_d,pocetak_t, zahvaceni_uredaji, "
				+ "napomena,incident_rjesava,rjesen,korisnik_id,vrsta_id,prioritet_id,kategorija_id,kraj_d,kraj_t "
				+ "FROM EVIDENTIRANJE.INCIDENT_POCETAK INNER JOIN EVIDENTIRANJE.INCIDENT_KRAJ ON "
				+ "EVIDENTIRANJE.INCIDENT_POCETAK.broj_naloga = EVIDENTIRANJE.INCIDENT_KRAJ.broj_naloga;";
		
		PreparedStatement prepStat = vezaSaBazom.prepareStatement(dohvatiRješeneIncidente);
		ResultSet dohvatiPodatke = prepStat.executeQuery();
		//---------------------------------------------------
		
		KrajIncidenta krajI = null;
		//LocalDate datum = null;
		
		while(dohvatiPodatke.next()) {
			
			//Date sqlDate = dohvatiPodatke.getDate("kraj_d"); //metoda (sucelje..konverzija)promjena 	sql date to localdate.. i obratno..
			//datum = sqlDate.toLocalDate();
			
			krajI = new KrajIncidenta(KonverzijaVremena.sqlDateToLocalDate(dohvatiPodatke.getDate("kraj_d")),
									  KonverzijaVremena.sqlTimeToLocalTime(dohvatiPodatke.getTime("kraj_t")),
									  dohvatiPodatke.getInt("broj_naloga"), 
									  KategorijeIncidenata.vratiKategoriju(dohvatiPodatke.getInt("kategorija_id")),  //dohvatiPodatke.getInt("kategorija_id"),
									  dohvatiPodatke.getString("zahvaceni_uredaji"), 
									  VrstaAlarma.vratiAlarm(dohvatiPodatke.getInt("vrsta_id")),
									  dohvatiPodatke.getString("napomena"), 
									  dohvatiPodatke.getString("incident_rjesava"), 
									  PrioritetiIncidenta.vratiPrioritet(dohvatiPodatke.getInt("prioritet_id")), 
									  KonverzijaVremena.sqlDateToLocalDate(dohvatiPodatke.getDate("pocetak_d")),
									  KonverzijaVremena.sqlTimeToLocalTime(dohvatiPodatke.getTime("pocetak_t")));
			
			incLista.add(krajI);
			
		}
		
		//---------------------------------------------------------------------------------------------------------------
		
		String dohvatiNeRješeneIncidente = "SELECT broj_naloga,pocetak_d,pocetak_t,zahvaceni_uredaji,napomena,incident_rjesava,"
				+ "vrsta_id,prioritet_id,kategorija_id FROM EVIDENTIRANJE.INCIDENT_POCETAK WHERE rjesen = false;";
		
		prepStat = vezaSaBazom.prepareStatement(dohvatiNeRješeneIncidente);
		dohvatiPodatke = prepStat.executeQuery();
		
		PocetakIncidenta pocetakI = null;
		
		while(dohvatiPodatke.next()) {
			
			pocetakI = new PocetakIncidenta(dohvatiPodatke.getInt("broj_naloga"),
											KategorijeIncidenata.vratiKategoriju(dohvatiPodatke.getInt("kategorija_id")),  
											dohvatiPodatke.getString("zahvaceni_uredaji"), 
											VrstaAlarma.vratiAlarm(dohvatiPodatke.getInt("vrsta_id")),
											dohvatiPodatke.getString("napomena"), 
											dohvatiPodatke.getString("incident_rjesava"), 
											PrioritetiIncidenta.vratiPrioritet(dohvatiPodatke.getInt("prioritet_id")), 
											KonverzijaVremena.sqlDateToLocalDate(dohvatiPodatke.getDate("pocetak_d")),
											KonverzijaVremena.sqlTimeToLocalTime(dohvatiPodatke.getTime("pocetak_t")));
			incLista.add(0,pocetakI);
			
		}
		
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
		
		return incLista;
	}
	
	static void zatvoriIncident(KrajIncidenta krajInc) throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		
		String sqlUpitZatvoriIncident = "INSERT INTO EVIDENTIRANJE.INCIDENT_KRAJ(kraj_d,kraj_t,broj_naloga) "
				+ "VALUES(?,?,?);";
		
		PreparedStatement prepSt = vezaSaBazom.prepareStatement(sqlUpitZatvoriIncident);
		prepSt.setDate(1, KonverzijaVremena.localDateToSqlDate(krajInc.getKrajDatum()));
		prepSt.setTime(2, KonverzijaVremena.localTimeToSqlTime(krajInc.getKrajVrijeme()));
		prepSt.setInt(3, krajInc.getBrojNaloga());
		prepSt.executeUpdate();
		sqlUpitZatvoriIncident = "UPDATE EVIDENTIRANJE.INCIDENT_POCETAK SET rjesen = ? WHERE broj_naloga = ?;";
		prepSt = vezaSaBazom.prepareStatement(sqlUpitZatvoriIncident);
		prepSt.setBoolean(1, vratiBoolean(krajInc.getRijesen()));
		prepSt.setInt(2, krajInc.getBrojNaloga());
		prepSt.executeUpdate();
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
		
		
	}
	
	static void spremiPocetniIncident(PocetakIncidenta pocIncident) throws Exception{
		
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		
		String sqlUpitPocetakIncident = "INSERT INTO EVIDENTIRANJE.INCIDENT_POCETAK(broj_naloga,pocetak_d,pocetak_t,"
				+ " zahvaceni_uredaji, napomena,incident_rjesava,rjesen,vrsta_id,korisnik_id,prioritet_id,kategorija_id) \r\n" + 
				"	VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement stmtPrep = vezaSaBazom.prepareStatement(sqlUpitPocetakIncident);
		stmtPrep.setInt(1, pocIncident.getBrojNaloga());
		stmtPrep.setDate(2, KonverzijaVremena.localDateToSqlDate(pocIncident.getPocetakDatum()));
		stmtPrep.setTime(3,KonverzijaVremena.localTimeToSqlTime(pocIncident.getPocetakVrijeme()));
		stmtPrep.setString(4, pocIncident.getZahvacenUredaji());
		stmtPrep.setString(5, pocIncident.getNapomena());
		stmtPrep.setString(6, pocIncident.getIncidentRjesava());
		stmtPrep.setBoolean(7, vratiBoolean(pocIncident.getRijesen()));//dodat metodu
		stmtPrep.setInt(8, VrstaAlarma.vratiIntAlarm(pocIncident.getTipAlarma()));
		stmtPrep.setInt(9, 1);
		stmtPrep.setInt(10, PrioritetiIncidenta.vratiIntPrioriteta(pocIncident.getPrioriteti()));
		stmtPrep.setInt(11, KategorijeIncidenata.vratiIntKategorije(pocIncident.getKategIncidenta()));
		stmtPrep.executeUpdate();
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
	}
	
	
	static Boolean vratiBoolean(String rijesen) {
		if(rijesen.equals("DA")) {
			return true;
		}else {
			return false;
		}		
	}
	
	static List<Integer> dohvatiBrojeveIncidenta() throws Exception{
		
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		
		List<Integer> brojeviIncidenta = new ArrayList<Integer>();
		
		String dohvatiBrojeve = "SELECT broj_naloga FROM EVIDENTIRANJE.INCIDENT_POCETAK";
		
		PreparedStatement prepS = vezaSaBazom.prepareStatement(dohvatiBrojeve);
		ResultSet dohvati = prepS.executeQuery();
		
		while(dohvati.next()) {
			
			brojeviIncidenta.add(dohvati.getInt("broj_naloga"));
			
		}
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
		return brojeviIncidenta;
	}

	static List<Integer> dohvatiBrojeveKorisnika() throws Exception {
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		
		List<Integer> idKorisnika = new ArrayList<Integer>();
		
		String dohvatiBrojeve = "SELECT korisnik_id FROM EVIDENTIRANJE.KORISNIK";
		
		PreparedStatement prepS = vezaSaBazom.prepareStatement(dohvatiBrojeve);
		ResultSet dohvati = prepS.executeQuery();
		
		while(dohvati.next()) {
			
			idKorisnika.add(dohvati.getInt("korisnik_id"));
			
		}
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
		return idKorisnika;
	}

	static int dohvatiMrezneIncidente() throws Exception{
				
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		String dohvatiUkupno = "SELECT COUNT(kategorija_id) FROM EVIDENTIRANJE.INCIDENT_POCETAK WHERE kategorija_id = 1;";
		
		PreparedStatement prepS = vezaSaBazom.prepareStatement(dohvatiUkupno);
		ResultSet dohvati = prepS.executeQuery();
		int ukupno = 0;
		while(dohvati.next()) {
			
			ukupno = dohvati.getInt(1);
			
		}
		
		return ukupno;
	}

	
	
	static int dohvatiTelekomIncidente() throws Exception{
					
			Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
			String dohvatiUkupno = "SELECT COUNT(kategorija_id) FROM EVIDENTIRANJE.INCIDENT_POCETAK WHERE kategorija_id = 3;";
			
			PreparedStatement prepS = vezaSaBazom.prepareStatement(dohvatiUkupno);
			ResultSet dohvati = prepS.executeQuery();
			int ukupno = 0;
			while(dohvati.next()) {				
				ukupno = dohvati.getInt(1);				
			}			
			return ukupno;
		
	}

	static int dohvatiPosluzIncidente() throws Exception{
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		String dohvatiUkupno = "SELECT COUNT(kategorija_id) FROM EVIDENTIRANJE.INCIDENT_POCETAK WHERE kategorija_id = 2;";
		
		PreparedStatement prepS = vezaSaBazom.prepareStatement(dohvatiUkupno);
		ResultSet dohvati = prepS.executeQuery();
		int ukupno = 0;
		while(dohvati.next()) {				
			ukupno = dohvati.getInt(1);				
		}			
		return ukupno;
	}

	static List<LocalDate> dohvatiDatumIncidenata() throws Exception{
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		String dohvatiUkupno = "SELECT pocetak_d FROM EVIDENTIRANJE.INCIDENT_POCETAK;";
		
		PreparedStatement prepS = vezaSaBazom.prepareStatement(dohvatiUkupno);
		ResultSet dohvati = prepS.executeQuery();
		List<LocalDate> ukupno = new ArrayList<LocalDate>();
		while(dohvati.next()) {				
			ukupno.add(KonverzijaVremena.sqlDateToLocalDate(dohvati.getDate("pocetak_d")));				
		}			
		return ukupno;
	}
	
	
}
