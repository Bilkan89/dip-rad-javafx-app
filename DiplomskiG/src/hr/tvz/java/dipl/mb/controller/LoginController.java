package hr.tvz.java.dipl.mb.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import hr.tvz.java.dipl.mb.glavna.Main;
//import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
	String user = "M";
	String pass = "A";

	//TODO: potrebno je provjeri u bazi podataka korisnièko ime i lozinku.
	
	@FXML
	private void initialize(){		
		enterPritisnut(paneNode);
		enterPritisnut(passField);
	}

	@FXML
	private void loginMetoda(ActionEvent event) throws InterruptedException{
			
		if(tfUsername.getText().equals("") && passField.getText().equals("")){

			hiddenLabel.setText("Login sucess!");
			try {
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				double width = screenSize.getWidth();
				double height = screenSize.getHeight();
				//Dužina ekrana: 1366.0, visina ekrana: 768.0
				//System.out.println("Dužina ekrana: "+width+", visina ekrana: " + height);
				BorderPane layoutMoj = FXMLLoader.load(Main.class.getResource("/fxml/fxml_PocetniZaslon.fxml"));
				Stage secStage = new Stage();
				Scene secScene = new Scene(layoutMoj,width,height);
				secStage.setScene(secScene);
				secStage.setFullScreen(true);//dodatPodIzbornikFullscreen
				//secStage.setMaximized(true);
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

			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("POGREŠAN UNOS");
			alert.setContentText("Unijeli ste krive podatke!!  Provjerite korisnièko ime i lozinku!");
			alert.showAndWait();
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