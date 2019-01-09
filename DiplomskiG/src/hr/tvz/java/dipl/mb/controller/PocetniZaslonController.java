package hr.tvz.java.dipl.mb.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hr.tvz.java.dipl.mb.glavna.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PocetniZaslonController {
	// kontroler klasa za po�etni dashbord prikaz

	@FXML
	private Button closeButton1;
	@FXML
	private Pane panePocetni;
	@FXML
	private Label pozdravLabel;

	@FXML
	private BorderPane borderPaneGlavni;

	@FXML
	private void initialize() {
		pozdravMetoda();
	}

	

	@FXML
	private void pocetniZaslon() {
		borderPaneGlavni.setCenter(null);//da ne stavalj centar na centar, centar u centar
		borderPaneGlavni.setCenter(panePocetni);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		borderPaneGlavni.setMaxSize(width,height);
		
	}

	@FXML
	private void pregledKorisnika() {
		setCenterPane2("/fxml/fxml_PregledKorisnika.fxml");
	}

	@FXML
	private void dodavanjeKorisnika() {
		otvoriNoviProzor("/fxml/fxml_UnosKorisnika.fxml");
	}

	@FXML
	private void pregledIncidenata() {
		setCenterPane2("/fxml/fxml_PregledIncidenata.fxml");
	}

	@FXML
	private void unosIncidenta() {
		otvoriNoviProzor("/fxml/fxml_UnosIncidenta.fxml");
	}

	@FXML
	private void izvjescePoKateg() {
		setCenterPane2("/fxml/fxml_IzvjescePoKategoriji.fxml");
	}
	
	@FXML
	private void izvjesceUkupno() {
		setCenterPane2("/fxml/fxml_IzvjesceUkupno.fxml");
	}
	
	@FXML
	private void izvjescePoRjesavanju() {
		setCenterPane2("/fxml/fxml_IzvjescePoRjesavanju.fxml");
	}
	
	
	@FXML
	private void oAplikaciji() {
		otvoriNoviProzor("/fxml/fxml_Oaplikaciji.fxml");
	}
	
	@FXML
	private void pomocProzor(){
		otvoriNoviProzor("/fxml/fxml_Pomoc.fxml");
	}
	

	@FXML
	private void closeProgram() {
		// Platform.exit(); //odluciti koji je bolji na�in...
		Stage stage = (Stage) closeButton1.getScene().getWindow();
		stage.close();
	}

	static protected void otvoriNoviProzor(String lokacijaFXML) {
		try {
			BorderPane novi = FXMLLoader.load(Main.class.getResource(lokacijaFXML));
			Scene noviScene = new Scene(novi);
			Stage noviStage = new Stage();
			noviStage.setScene(noviScene);
			//System.out.println("test orvori novi prozor metoda");
			//noviStage.initStyle(StageStyle.UTILITY);
			if(lokacijaFXML.equals("/fxml/fxml_Pomoc.fxml") || lokacijaFXML.equals("/fxml/fxml_Oaplikaciji.fxml")){
				noviStage.initStyle(StageStyle.UTILITY);
				noviStage.setResizable(false);
				noviStage.setMaximized(false);
			}
			noviStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	
	}

	private void setCenterPane2(String lokacijaFXML) {
		try {
			borderPaneGlavni.setCenter(FXMLLoader.load(Main.class.getResource(lokacijaFXML)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void pozdravMetoda() {
		String username = "Matej Bilić";
		DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
		String dan_u_Tjedanu = "Pon";
		switch (dayOfWeek) {
		case MONDAY:
			dan_u_Tjedanu = "ponedjeljak";
			break;
		case TUESDAY:
			dan_u_Tjedanu = "utorak";
			break;
		case WEDNESDAY:
			dan_u_Tjedanu = "srijeda";
			break;
		case THURSDAY:
			dan_u_Tjedanu = "četvrtak";
			break;
		case FRIDAY:
			dan_u_Tjedanu = "petak";
			break;
		case SATURDAY:
			dan_u_Tjedanu = "subota";
			break;
		case SUNDAY:
			dan_u_Tjedanu = "nedjelja";
			break;		
		}
		String datum = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		
		pozdravLabel.setText(" Dobro došli, "+username+" danas je "+datum+", "+dan_u_Tjedanu);
		//System.out.println(" Dobro došli, "+username+" danas je "+LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+", "+dayOfWeek);
		//System.out.println(pozdravLabel.getText());
		
	}


}
