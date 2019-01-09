package hr.tvz.java.dipl.mb.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopUpController {

	@FXML
	private Label greskaLabel;
	
	@FXML
	private void GreskaBtn(ActionEvent event){
		Stage prozor = (Stage) greskaLabel.getScene().getWindow();
		prozor.close();		
	}
	
	
	
}
