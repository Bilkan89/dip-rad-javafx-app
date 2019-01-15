package hr.tvz.java.dipl.mb.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import hr.tvz.java.dipl.mb.glavna.Main;
import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciKorisnika;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {


	@FXML
	private Label hiddenLabel;
	@FXML
	private Pane paneNode;
	@FXML
	private PasswordField passField;
	@FXML
	private TextField tfUsername;
	@FXML
	private Button loginButton;


	//TODO: potrebno je provjeri u bazi podataka korisnièko ime i lozinku.
	
	@FXML
	private void initialize(){		
		enterPritisnut(paneNode);
		enterPritisnut(passField);	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		String vrijeme = LocalDateTime.now().format(formatter);
		hiddenLabel.setText(vrijeme+"h");
	}

	@FXML
	private void loginMetoda() {
		try {
				final Boolean pristup = PodatciKorisnika.provjeraPristupa(tfUsername.getText(),passField.getText());			
				
				//if(tfUsername.getText().equals("") && passField.getText().equals("")){
		
				if(pristup){
					
					hiddenLabel.setText("Login sucess!");
				
						Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
						double width = screenSize.getWidth();
						double height = screenSize.getHeight();
						//Dužina ekrana laptop: 1366.0, visina ekrana: 768.0
						//Dužina ekrana pc prvi monitor: 1440.0, visina ekrana: 900.0
						//System.out.println("Dužina ekrana: "+width+", visina ekrana: " + height);
						
						BorderPane layoutMoj = FXMLLoader.load(Main.class.getResource("/fxml/fxml_PocetniZaslon.fxml"));
						layoutMoj.setPrefWidth(width);
						layoutMoj.setPrefHeight(height);
						Stage secStage = new Stage();
						Scene secScene = new Scene(layoutMoj,width,height);
						secStage.setScene(secScene);
						//dodatPodIzbornikFullscreen
						//secStage.setMaximized(true);
						secStage.setTitle("Evidentiranje incidentata");
						secStage.setFullScreenExitHint("Za izlazak iz punog zaslona pritisnite tipku 'ESC'");
						secStage.setMinWidth(width/2);
						
						secStage.setMinHeight(height/2);
						secStage.setHeight(height);
						secStage.setWidth(width);
						secStage.setHeight(height);
						secStage.setMaxWidth(width);
						secStage.setMaxHeight(height);
						secStage.setFullScreen(true);
						secStage.show();
						//secScene.heightProperty().
						//secStage.
						//secStage.setResizable(false);
						//secStage.setMaximized(true);
						//secStage.initStyle(StageStyle.UNDECORATED);
						
						
		
		//				Stage glavni = (Stage) loginButton.getScene().getWindow();
		//				glavni.close();
						
						
						tfUsername.clear();
						passField.clear();
						closeLoginWindow();
		
					
					
				}else{
					hiddenLabel.setText("Login failed!");
					MojPopUp.porukaPopUp(AlertType.ERROR,"PRISTUP", "Pogrešno korisnièko ime ili lozinka! Pristup odbijen!");
		//			Alert alert = new Alert(AlertType.ERROR);
		//			alert.setTitle("POGREŠAN UNOS");
		//			alert.setContentText("Unijeli ste krive podatke!!  Provjerite korisnièko ime i lozinku!");
		//			alert.showAndWait();
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				MojPopUp.porukaPopUp(AlertType.ERROR,"PRISTUP", e.getMessage()+"/"+e.getCause());
								
			}
	}
	
	
	
	
	
	@FXML
	private void closeLoginWindow(){

		//Platform.exit();   //odluciti koji je bolji naæin...
		Stage stage =(Stage) loginButton.getScene().getWindow();
		stage.close();

	}
	
	private void enterPritisnut(Node node){
		node.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent tipka) {
					if(tipka.getCode() == KeyCode.ENTER){
						loginButton.fire();
						tipka.consume();
					}					
			}});	
	}
	
	
	
	
	
	
	
}