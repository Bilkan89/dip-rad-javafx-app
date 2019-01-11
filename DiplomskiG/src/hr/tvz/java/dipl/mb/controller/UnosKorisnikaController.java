package hr.tvz.java.dipl.mb.controller;


import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;



import hr.tvz.java.dipl.mb.entitet.Adresa;
import hr.tvz.java.dipl.mb.entitet.Korisnik;
import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciIncident;
import hr.tvz.java.dipl.mb.sucelja.PodatciKorisnika;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class UnosKorisnikaController {

	
	@FXML
	private TextField imeKorisnika;
	
	@FXML
	private TextField prezimeKorisnika;
	
	@FXML
	private TextField drzavaKorisnika;
	
	@FXML
	private TextField gradKorisnika;
		
	@FXML
	private TextField ulicaKorisnika;
	
	@FXML
	private TextField kucniBrojKorisnika;
	
	@FXML
	private TextField kontaktBrojKorisnika;
	
	@FXML
	private TextField korisnickoImeKorisnika;
	
	@FXML
	private TextField korisnicki_id;
	
	@FXML
	private PasswordField lozinkaKorisnika;
	
	@FXML
	private PasswordField potvrdaLozinkeKorisnika;
	
	@FXML
	private TextArea napomenaKorisnika;
	
	
	
	
	@FXML
	private void unesiPodatke(ActionEvent event){
		List<TextField> provjeraTextFielda = new ArrayList<TextField>();
		provjeraTextFielda.add(korisnicki_id);
		provjeraTextFielda.add(imeKorisnika);
		provjeraTextFielda.add(prezimeKorisnika);
		provjeraTextFielda.add(drzavaKorisnika);
		provjeraTextFielda.add(gradKorisnika);
		provjeraTextFielda.add(ulicaKorisnika);
		provjeraTextFielda.add(kucniBrojKorisnika);
		provjeraTextFielda.add(kontaktBrojKorisnika);
		provjeraTextFielda.add(korisnickoImeKorisnika);
		for(TextField textF : provjeraTextFielda) {
			if(textF.getText().isEmpty()) {
				MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nisu unešeni svi potrebni podatci!");
				//alarmPraznoPolje();
				return;
			}
		}
		if (lozinkaKorisnika.getText().isEmpty() || potvrdaLozinkeKorisnika.getText().isEmpty()) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Nisu unešeni svi potrebni podatci!");
			return;
		}
		if (!lozinkaKorisnika.getText().equals(potvrdaLozinkeKorisnika.getText())) {
			MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Ponovljena lozinka se ne podudara");
			return;
		}
		
		try {
			
			List<Integer> listaIdKorisnika =  PodatciIncident.dohvatiBrojeveKorisnika();
			for(Integer id : listaIdKorisnika) {
				if(id == Integer.valueOf(korisnicki_id.getText())) {
					MojPopUp.porukaPopUp(AlertType.WARNING,"UPOZORENJE!!", "Korisnik pod id brojem: "+korisnicki_id.getText()+" postoji! Molim unesite drugi broj!");
					korisnicki_id.clear();
					return;
				}
			}
			
			Stage pozornica = (Stage) imeKorisnika.getScene().getWindow();
			
			Date datumKreiranja = Date.valueOf(LocalDate.now());
			//System.out.println("Printanje sql date:" +datumKreiranja);
			
			Adresa zaUnosAdresa = new Adresa(drzavaKorisnika.getText(), gradKorisnika.getText(),
											 ulicaKorisnika.getText(), Integer.parseInt(kucniBrojKorisnika.getText()));
			
			Korisnik zaUnosBaza = new Korisnik(Integer.parseInt(korisnicki_id.getText()),
												imeKorisnika.getText(),
												prezimeKorisnika.getText(),
												zaUnosAdresa,
												Integer.parseInt(kontaktBrojKorisnika.getText()),
												korisnickoImeKorisnika.getText(),
												lozinkaKorisnika.getText(),
												napomenaKorisnika.getText(),
												datumKreiranja);
			
			PodatciKorisnika.spremiKorisnika(zaUnosBaza);
			
			MojPopUp.porukaPopUp(AlertType.INFORMATION,"Inforativna poruka", "Korisnik " + zaUnosBaza.getIme()+" "+ zaUnosBaza.getPrezime()
			+" je uspješno dodan u bazu podataka!");
			
			pozornica.close();
			
			
			
		} catch (Exception e) {
			System.out.println("GREŠKA!! ERROR!! /n"
				  	  +"DOGODILA SE GREŠKA: "+e.getMessage()+" n/ "
				  	  +"UZROK: "+e.getCause()+" /n "
				  	  +"PORUKA: "+e.toString());
			e.printStackTrace();
		}	
	};

	
	@FXML
	private void obrisiPodatke(ActionEvent event){
		korisnicki_id.clear();
		imeKorisnika.clear();
		prezimeKorisnika.clear();
		drzavaKorisnika.clear();
		gradKorisnika.clear();
		ulicaKorisnika.clear();
		kucniBrojKorisnika.clear();
		kontaktBrojKorisnika.clear();
		korisnickoImeKorisnika.clear();
		lozinkaKorisnika.clear();
		potvrdaLozinkeKorisnika.clear();
		napomenaKorisnika.clear();		
	};
	
	
	@FXML
	private void odustani(ActionEvent event){
		Stage pozornica = (Stage) imeKorisnika.getScene().getWindow();
		pozornica.close();
	};
	
	
	
}
