package hr.tvz.java.dipl.mb.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import hr.tvz.java.dipl.mb.entitet.Incident;
import hr.tvz.java.dipl.mb.entitet.KrajIncidenta;
import hr.tvz.java.dipl.mb.entitet.PocetakIncidenta;
import hr.tvz.java.dipl.mb.sucelja.KonverzijaVremena;
import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciIncident;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ZatvoriIncidentController {

	
	@FXML
	DatePicker datumZavrsetka;
	
	@FXML
	TextField satZavrsetka;
	
	@FXML
	TextField minZavrsetka;
	
//	LocalDate zavDatum;
//	
//	LocalTime zavVrijeme;
	
	PocetakIncidenta pocetniI = null;
	
	@FXML
	private void initialize(){
		datumZavrsetka.setValue(LocalDate.now());
		satZavrsetka.setText(String.valueOf(LocalTime.now().getHour()));
		minZavrsetka.setText(String.valueOf(LocalTime.now().getMinute()));
	}
	
	
	@FXML
	public void unesiZavrsetakIncidenta() {
//		zavDatum = datumZavrsetka.getValue();
		if(satZavrsetka.getText().isEmpty()) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nisu unešeni svi potrebni podatci!");
			//alarmPraznoPolje();
			return;
		}
		
		if (minZavrsetka.getText().isEmpty()) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nisu unešeni svi potrebni podatci!");
			//alarmPraznoPolje();
			return;
		}
		
		if (datumZavrsetka.getValue() == null) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nisu unešeni svi potrebni podatci!");
			//alarmPraznoPolje();
			return;
		}
		
		

		try {
			Stage pozornica = (Stage) satZavrsetka.getScene().getWindow();
			
			//System.out.println("Zavrsi kontroler dohvati metoda"+pocetniI.getBrojNaloga());
			LocalTime zavVrijeme = KonverzijaVremena.stringToLocalTime(satZavrsetka.getText(), minZavrsetka.getText());
			KrajIncidenta iKraj = new KrajIncidenta(datumZavrsetka.getValue(),
													zavVrijeme,
													pocetniI.getBrojNaloga(),
													pocetniI.getKategIncidenta(), 
													pocetniI.getZahvacenUredaji(), 
													pocetniI.getTipAlarma(), 
													pocetniI.getNapomena(), 
													pocetniI.getIncidentRjesava(), 
													pocetniI.getPrioriteti(), 
													pocetniI.getPocetakDatum(), 
													pocetniI.getPocetakVrijeme());
			
			PodatciIncident.zatvoriIncident(iKraj);
			
			MojPopUp.porukaPopUp(AlertType.INFORMATION,"Inforativna poruka", "Incident sa brojem naloga: "+iKraj.getBrojNaloga()+
					"je uspješno zatvoren i dodan u bazu podataka!!");

			
			pozornica.close();
		} catch (Exception e) {
			MojPopUp.porukaPopUp(AlertType.WARNING, "GREŠKA!!","Poruka: "+ e.getMessage()+"Uzrok: "+e.getCause());
		}
		
		
//		try {
//			//Incident iPocetni = PregledIncidenataController.
//			
//			/**
//			 * 
//			 * UnosClanaController cont = loadFXML
//						.<UnosClanaController>getController();
//				
//				cont.urediParametreClana(
//						ViewClan.getSelectionModel().getSelectedItem());
//			 * 
//			 */
//			FXMLLoader loadFXML = new FXMLLoader(
//					getClass().getResource("/fxml/fxml_PregledIncidenata.fxml"));
//			
//			
//					
//			
//			BorderPane sve = (BorderPane) loadFXML.load();
//					//Parent root = (Parent) loader.load();
//			 Scene newScene = new Scene(sve);
//	         Stage newStage = new Stage();
//	         newStage.setScene(newScene);
//			
//			PregledIncidenataController cont = loadFXML
//					.<PregledIncidenataController>getController();
//			
//			Incident pocetakI = cont.dohvatiOdabraniIncident().getSelectionModel().getSelectedItem();
//			
//			System.out.println(pocetakI.getBrojNaloga());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void dohvatiOdabraniIncident(Incident odabrani) {
		pocetniI = (PocetakIncidenta) odabrani;
		//System.out.println("Zavrsi kontroler dohvati metoda"+pocetniI.getBrojNaloga());
	}

	
	@FXML
	public void odustaniUnos() {
	
		Stage pozornica = (Stage) satZavrsetka.getScene().getWindow();
		pozornica.close();
	}

//	public LocalDate getZavDatum() {
//		return zavDatum;
//	}
//
//	public LocalTime getZavVrijeme() {
//		return zavVrijeme;
//	};
	
	
	
}
