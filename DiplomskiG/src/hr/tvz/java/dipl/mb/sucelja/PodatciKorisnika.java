package hr.tvz.java.dipl.mb.sucelja;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hr.tvz.java.dipl.mb.baza.KomunikacijaSaBazom;
import hr.tvz.java.dipl.mb.entitet.Adresa;
import hr.tvz.java.dipl.mb.entitet.Korisnik;




public interface PodatciKorisnika {
	
	static List<Korisnik> dohvatiKorisnike() throws Exception{
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		
		List<Korisnik> lista = new ArrayList<Korisnik>();		
		
		String dohvatiKorisnikeSQL = "SELECT korisnik.korisnik_id,ime,prezime,kontakt_broj,napomena,datum_kreiranja,drzava,grad,ulica,kucni_broj,korisnicko_ime,lozinka FROM "
				+ "EVIDENTIRANJE.KORISNIK INNER JOIN "
				+ "EVIDENTIRANJE.ADRESA ON EVIDENTIRANJE.KORISNIK.korisnik_id = EVIDENTIRANJE.ADRESA.korisnik_id "
				+ "INNER JOIN EVIDENTIRANJE.PRISTUP ON EVIDENTIRANJE.ADRESA.korisnik_id = EVIDENTIRANJE.PRISTUP.korisnik_id"; 
		
		PreparedStatement prepStatment = vezaSaBazom.prepareStatement(dohvatiKorisnikeSQL);
		ResultSet resultSet = prepStatment.executeQuery();
		
		Adresa novaA = null;
		Korisnik noviK = null;
		
		while(resultSet.next()){
		
		novaA = new Adresa(resultSet.getString("drzava"),
						   resultSet.getString("grad"),
							resultSet.getString("ulica"),
							 resultSet.getInt("kucni_broj"));	
		
		noviK = new Korisnik(resultSet.getInt("korisnik_id"),
							 resultSet.getString("ime"),
							 resultSet.getString("prezime"),
							 novaA,
							 resultSet.getInt("kontakt_broj"),
							 resultSet.getString("korisnicko_ime"),
							 resultSet.getString("lozinka"),
							 resultSet.getString("napomena"),
							 resultSet.getDate("datum_kreiranja"));		
			lista.add(noviK);			
		}		
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
		return lista;
	};
	
	static void obrisiKorisnika(Korisnik korisnik) throws Exception{
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		int idBrojKor = korisnik.getIdKorisnika();
		System.out.println(idBrojKor);
		String upitSQL = "DELETE FROM EVIDENTIRANJE.ADRESA WHERE KORISNIK_ID="+idBrojKor+"; DELETE FROM EVIDENTIRANJE.PRISTUP WHERE KORISNIK_ID="+idBrojKor+"; DELETE FROM EVIDENTIRANJE.KORISNIK WHERE KORISNIK_ID="+idBrojKor+";";
		PreparedStatement stmt = vezaSaBazom.prepareStatement(upitSQL);
	//	stmt.setInt(1, korisnik.getIdKorisnika());
//		stmt.setInt(2, korisnik.getAdresaKorisnika().getadresa.getIdKorisnika());
//		stmt.setInt(3, korisnik.getIdKorisnika());
		stmt.executeUpdate();
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
		
		
		
	}
	
	static void spremiKorisnika(Korisnik korisnik) throws Exception{
		Connection vezaSaBazom = KomunikacijaSaBazom.konekcijaDB();
		String upitSqlkorisnik = "INSERT INTO EVIDENTIRANJE.KORISNIK"
				+ "(ime,prezime,kontakt_broj,napomena,datum_kreiranja,korisnik_id) values (?,?,?,?,?,?);";
		
		String upitSqlAdresa = "INSERT INTO EVIDENTIRANJE.ADRESA"
				+ "(drzava,grad,ulica,kucni_broj,korisnik_id) VALUES (?,?,?,?,?);";
		
		String upitSqlPristup = "INSERT INTO EVIDENTIRANJE.PRISTUP"
				+ "(korisnicko_ime,lozinka,korisnik_id) VALUES (?,?,?);";
		
		PreparedStatement stmt = vezaSaBazom.prepareStatement(upitSqlkorisnik);
		stmt.setString(1,korisnik.getIme());
		stmt.setString(2,korisnik.getPrezime());
		stmt.setInt(3,korisnik.getKontaktBroj());
		stmt.setString(4,korisnik.getNapomena());
		stmt.setDate(5, korisnik.getDatumKreiranja());
		stmt.setInt(6,korisnik.getIdKorisnika());
		stmt.executeUpdate();
		stmt = vezaSaBazom.prepareStatement(upitSqlAdresa);
		stmt.setString(1,korisnik.getAdresaKorisnika().getDrzava());
		stmt.setString(2,korisnik.getAdresaKorisnika().getGrad());
		stmt.setString(3,korisnik.getAdresaKorisnika().getUlica());
		stmt.setInt(4,korisnik.getAdresaKorisnika().getKucniBroj());
		stmt.setInt(5, korisnik.getIdKorisnika());
		stmt.executeUpdate();
		stmt = vezaSaBazom.prepareStatement(upitSqlPristup);
		stmt.setString(1,korisnik.getKorisnickoIme());
		stmt.setString(2,korisnik.getKorisnickaLozinka());
		stmt.setInt(3, korisnik.getIdKorisnika());
		stmt.executeUpdate();
		KomunikacijaSaBazom.zatvoriKonekciju(vezaSaBazom);
		System.out.println("Unos u bazu završio.. ili završila metoda unosKorisnika..");
	}
	
	
	
/*
 * public static void spremiClanaUBazu(Clan clan) throws Exception {

		Connection vezaSaBazom = KomunikacijaSaBazom.connectingToDataBase();
		String sqlUpit = "INSERT INTO RAZVOJ.CLAN (oib, ime, prezime ) VALUES (?,?,?)";
		PreparedStatement stmt = vezaSaBazom.prepareStatement(sqlUpit);
		stmt.setInt(1, clan.getOib());
		stmt.setString(2, clan.getIme());
		stmt.setString(3, clan.getPrezime());
		stmt.executeUpdate();

		KomunikacijaSaBazom.closingConnectionToDataBase(vezaSaBazom);
		
		
		INSERT INTO EVIDENTIRANJE.KORISNIK(ime,prezime,kontakt_broj,napomena,datum_kreiranja,korisnik_id) values 
	('Pero','Peric',0911234567,'Operator aplikacije','2018-10-25',2);

		INSERT INTO EVIDENTIRANJE.ADRESA(drzava,grad,ulica,kucni_broj, korisnik_id)
			VALUES ('Hrvatska','Zagreb','Gruska',22,2);
	
		INSERT INTO EVIDENTIRANJE.PRISTUP(korisnicko_ime,lozinka,korisnik_id)
			VALUES ('pperic','hdklaid',2);

	} 
 * 
 */
	
}
