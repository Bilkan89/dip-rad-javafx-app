package hr.tvz.java.dipl.mb.baza;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import javafx.scene.control.Alert.AlertType;

public class KomunikacijaSaBazom {

		private static final String DATABASE_PROPERTIES_FILE = "Resursi/DataBaseProperties/app.properties";

		public static Connection konekcijaDB() {
			Connection vezaSaBazom = null;
			try {
				Properties svojstva = new Properties();	
				final FileReader citacDatoteke = new FileReader(DATABASE_PROPERTIES_FILE);
				svojstva.load(citacDatoteke);	
				String urlBaze = svojstva.getProperty("bazaPodatakaURL");
				String korisnickoIme = svojstva.getProperty("korisnickoIme");
				String lozinka = svojstva.getProperty("lozinka");				
				vezaSaBazom = DriverManager.getConnection(urlBaze, korisnickoIme, lozinka);
				return vezaSaBazom;				
			}catch (Exception e) {
				MojPopUp.porukaPopUp(AlertType.WARNING,"Upozorenje!", 
						"Konekcija sa bazom podataka nije moguæa! "+e.getMessage());
				return vezaSaBazom;
			}
		}
		public static void zatvoriKonekciju(Connection vezaSaBazom) throws SQLException{
			vezaSaBazom.close();			
		}		
}

