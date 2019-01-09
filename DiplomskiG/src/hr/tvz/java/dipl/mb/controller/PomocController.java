package hr.tvz.java.dipl.mb.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PomocController {
	
	@FXML
	Button btnNode;
	
	@FXML
	private void okBtn(){
		Stage prozor = (Stage) btnNode.getScene().getWindow();
		prozor.close();
	}
	
}
