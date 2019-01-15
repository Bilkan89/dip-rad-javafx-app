package hr.tvz.java.dipl.mb.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hr.tvz.java.dipl.mb.glavna.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
	private Label labelDoljeLijevo;

	@FXML
	private	MenuBar glavniMenu;
	@FXML
	private BorderPane borderPaneGlavni;
	
	@FXML
	private HBox testHboxTop;
	
	@FXML
	private VBox srednjiVBox;
	
	@FXML
	private HBox hboxBottom;
	
	//Stage stage = (Stage) pozdravLabel.getScene().getWindow();

	@FXML
	private void initialize() {
		pozdravMetoda();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//glavniMenu.prefWidthProperty().bind(borderPaneGlavni.prefWidthProperty());
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		//borderPaneGlavni.getTop().setMaxSize(width,height);
//		borderPaneGlavni.prefWidth(width);
//		borderPaneGlavni.prefHeight(height);
//		borderPaneGlavni.getTop().prefWidth(width);
		//System.out.println(stage.getWidth());
		//glavniMenu.prefWidth(stage.getWidth());
//		testHboxTop.prefWidth(width);
		
//		borderPaneGlavni.prefWidthProperty().bind(borderPaneGlavni.getScene().widthProperty());
//		borderPaneGlavni.prefHeightProperty().bind(borderPaneGlavni.getScene().heightProperty());
//		
//		glavniMenu.prefWidthProperty().bind(borderPaneGlavni.prefWidthProperty());
		//glavniMenu.prefHeightProperty().bind(borderPaneGlavni.getScene().heightProperty());
		//borderPaneGlavni.prefHeight(height);
		setWidthMetoda();
		System.out.println("width:"+width+"height:"+height);
		System.out.println("border width:"+borderPaneGlavni.getPrefWidth());
		System.out.println("Hbox top width:"+testHboxTop.getPrefWidth());
		System.out.println("Menu width:"+glavniMenu.getPrefWidth());
		
	}

	

	



	@FXML
	private void pocetniZaslon() {
		borderPaneGlavni.setCenter(null);//da ne stavalj centar na centar, centar u centar
		borderPaneGlavni.setCenter(panePocetni);
		//testHboxTop.prefWidthProperty().bind(borderPaneGlavni.prefWidthProperty());
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//glavniMenu.prefWidthProperty().bind(borderPaneGlavni.prefWidthProperty());
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		//borderPaneGlavni.getTop().setMaxSize(width,height);
		//borderPaneGlavni.prefWidthProperty().bind(borderPaneGlavni.getScene().widthProperty());
		//borderPaneGlavni.prefHeightProperty().bind(borderPaneGlavni.getScene().heightProperty());
		
		//glavniMenu.prefWidthProperty().bind(borderPaneGlavni.prefWidthProperty());
		//testHboxTop.prefWidthProperty().bind(borderPaneGlavni.prefWidthProperty());
		setWidthMetoda();
//		glavniMenu.prefWidthProperty().bind(borderPaneGlavni.getScene().widthProperty());
//		glavniMenu.prefHeightProperty().bind(borderPaneGlavni.getScene().heightProperty());
		//borderPaneGlavni.prefHeight(height);
		//borderPaneGlavni.getTop().prefWidth(width);
		//glavniMenu.prefWidth(width);
		//testHboxTop.prefWidth(width);
		
		//pc-width:1440.0height:900.0
		System.out.println("width:"+width+"height:"+height);
		System.out.println("Hbox top width:"+testHboxTop.getPrefWidth());
		System.out.println("Menu width:"+glavniMenu.getPrefWidth());
		System.out.println("borderPane width:"+borderPaneGlavni.getPrefWidth());
		System.out.println("borderPane height:"+borderPaneGlavni.getHeight());
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
		Stage stage = (Stage) borderPaneGlavni.getScene().getWindow();
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
		//String username = "Matej Bilić";
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
		
		pozdravLabel.setText(" Dobro došli, danas je "+datum+", "+dan_u_Tjedanu);
		//System.out.println(" Dobro došli, "+username+" danas je "+LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+", "+dayOfWeek);
		//System.out.println(pozdravLabel.getText());
		labelDoljeLijevo.setText("Danas je "+datum+", "+dan_u_Tjedanu);
	}

	private void setWidthMetoda() {
		
//		borderPaneGlavni.prefWidthProperty().bind(borderPaneGlavni.getScene().getWindow().widthProperty());
//		borderPaneGlavni.prefHeightProperty().bind(borderPaneGlavni.getScene().getWindow().heightProperty());
		
		//glavniMenu.prefWidth((borderPaneGlavni.getScene().getWidth()));
		//testHboxTop.prefWidthProperty().bind(borderPaneGlavni.widthProperty());
		glavniMenu.prefWidthProperty().bind(borderPaneGlavni.widthProperty());
		srednjiVBox.prefWidthProperty().bind(borderPaneGlavni.widthProperty());
		hboxBottom.prefWidthProperty().bind(borderPaneGlavni.widthProperty());
		
		
	}

	@FXML
	private void otvoriFullScreen() {
		Stage window = (Stage) borderPaneGlavni.getScene().getWindow();
		//System.out.println("test");
		window.setFullScreenExitHint("Za izlazak iz punog zaslona pritisnite tipku 'ESC'");
		window.setFullScreen(true);
	}

	@FXML
	private void izlazFullScreen() {
		Stage window = (Stage) borderPaneGlavni.getScene().getWindow();
		//System.out.println("test");
		//window.setFullScreenExitHint("Za izlazak iz punog zaslona pritisnite tipku 'ESC'");
		window.setFullScreen(false);
		window.setMaximized(true);
	}
	
}
