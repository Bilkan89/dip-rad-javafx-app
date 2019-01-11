package hr.tvz.java.dipl.mb.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import hr.tvz.java.dipl.mb.entitet.PocetakIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.KategorijeIncidenata;
import hr.tvz.java.dipl.mb.enumeracije.PrioritetiIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.VrstaAlarma;
import hr.tvz.java.dipl.mb.sucelja.KonverzijaVremena;
import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciIncident;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UnosIncidentaController {

	
	
	@FXML
	private TextField brNaloga;
	
	@FXML
    private DatePicker datumPocetka;
	 
	@FXML
    private TextField satPocetka;

    @FXML
    private TextField minPocetka;
    
    @FXML
    private ChoiceBox<KategorijeIncidenata> katIncidentaChoiceBox;
    
    @FXML
    private TextField zahvacenaOprema;
    
    @FXML
    private ChoiceBox<VrstaAlarma> vrstaAlarmaChoiceBox;
    
    @FXML
    private TextField napomena;    

    @FXML
    private TextField incidentRjesava;

    @FXML
    private ChoiceBox<PrioritetiIncidenta> prioritetChoiceBox; 

   
    @FXML
	public void initialize() {
    	katIncidentaChoiceBox.getItems().addAll(KategorijeIncidenata.dohvatiVrijednosti());
    	vrstaAlarmaChoiceBox.getItems().addAll(VrstaAlarma.dohvatiVrijednosti());
    	prioritetChoiceBox.getItems().addAll(PrioritetiIncidenta.listaVrijednosti());
    	datumPocetka.setValue(LocalDate.now());
    	satPocetka.setText(String.valueOf(LocalTime.now().getHour()));
    	minPocetka.setText(String.valueOf(LocalTime.now().getMinute()));
	}
   

    

    @FXML
    private void unesiIncident() {
    	List<TextField> provjeraTextFielda = new ArrayList<TextField>();
		provjeraTextFielda.add(brNaloga);
		provjeraTextFielda.add(satPocetka);
		provjeraTextFielda.add(minPocetka);
		provjeraTextFielda.add(zahvacenaOprema);
		provjeraTextFielda.add(incidentRjesava);
		for(TextField textF : provjeraTextFielda) {
			if(textF.getText().isEmpty()) {
				MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nisu unešeni svi potrebni podatci!");
				return;
			}
		}
		
		if(prioritetChoiceBox.getValue().equals(null)) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nije odabran prioritet!!");
			return;
		}
	
		if(vrstaAlarmaChoiceBox.getValue().equals(null)) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nije odabrana vrsta alarma!!");
			return;
		}
		
		if(katIncidentaChoiceBox.getValue().equals(null)) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nije odabrana kategorija incidenta!!");
			return;
		}
		if (datumPocetka.getValue() == null) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nisu unešeni svi potrebni podatci!");
			return;
		}
		
		
		
		//----------------------------------------------------------------------------------------------
		try {
			
			List<Integer> listaNaloga =  PodatciIncident.dohvatiBrojeveIncidenta();
			for(Integer brojN : listaNaloga) {
				if(brojN == Integer.valueOf(brNaloga.getText())) {
					MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nalog pod brojem: "+brNaloga.getText()+" postoji! Molim unesite drugi broj!");
					brNaloga.clear();
					return;
				}
			}
			
			
			Stage pozornica = (Stage) brNaloga.getScene().getWindow();
			
			
			
			PocetakIncidenta noviIn = new PocetakIncidenta(Integer.valueOf(brNaloga.getText()),
														   katIncidentaChoiceBox.getValue(), 
														   zahvacenaOprema.getText(), 
														   vrstaAlarmaChoiceBox.getValue(), 
														   napomena.getText(), 
														   incidentRjesava.getText(), 
														   prioritetChoiceBox.getValue(), 
														   datumPocetka.getValue(), 
														   KonverzijaVremena.stringToLocalTime(satPocetka.getText(),minPocetka.getText()));
			
			PodatciIncident.spremiPocetniIncident(noviIn);
			
			MojPopUp.porukaPopUp(AlertType.INFORMATION,"Inforativna poruka", "Novi incident pod brojem naloga: "
					+brNaloga.getText()+" je uspješno dodan u bazu podataka!");
			
			pozornica.close();
			
			
			
		} catch (Exception e) {
			System.out.println("GREŠKA!! ERROR!! /n"
				  	  +"DOGODILA SE GREŠKA: "+e.getMessage()+" n/ "
				  	  +"UZROK: "+e.getCause()+" /n "
				  	  +"PORUKA: "+e.toString());
			e.printStackTrace();
		}    	
    }

    @FXML
    private void obrisiPodatke(ActionEvent event) {
    	brNaloga.clear();
    	datumPocetka.setValue(null);
    	satPocetka.clear();
    	minPocetka.clear();
    	katIncidentaChoiceBox.setValue(null);
    	zahvacenaOprema.clear();
    	vrstaAlarmaChoiceBox.setValue(null);
    	napomena.clear();
    	incidentRjesava.clear();
    	prioritetChoiceBox.setValue(null);
    }
	
    @FXML    
    private void odustaniUnos() {
    	Stage pozornica = (Stage) brNaloga.getScene().getWindow();
		pozornica.close();
    }
}
