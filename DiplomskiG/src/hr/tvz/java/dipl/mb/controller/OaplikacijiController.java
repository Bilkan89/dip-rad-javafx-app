package hr.tvz.java.dipl.mb.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class OaplikacijiController {
	
	@FXML
	private Button okBtn;	
	
	@FXML
	private void initialize(){		
				enterPritisnut(okBtn);
	}
	
	@FXML
    private void okBtnMetoda(ActionEvent event) {
		Stage prozor = (Stage) okBtn.getScene().getWindow();
		prozor.close();
    }

	private void enterPritisnut(Node node){
		node.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent tipka) {
					if(tipka.getCode() == KeyCode.ENTER){
						okBtn.fire();
						tipka.consume();
					}					
			}});
	}
}