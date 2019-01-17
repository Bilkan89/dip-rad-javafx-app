package hr.tvz.java.dipl.mb.controller;

import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciIncident;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class IzvjescePoKategorijiController {

	@FXML
	private BarChart<String, Number> dijagramBarChart;

	@FXML
	private Label skriveni;
	
	@FXML
	private NumberAxis yIncidentiAxis;

	@FXML
	private void initialize() {
	}

	@FXML
	private void PrikaziBtn() {

		try {
			int mre�niIncidentiUkupno = PodatciIncident.dohvatiMrezneIncidente();
			int telekomIncidentiUkupno = PodatciIncident.dohvatiTelekomIncidente();
			int posluziteljskiIncidentiUkupno = PodatciIncident.dohvatiPosluzIncidente();
			int sveZajedno = mre�niIncidentiUkupno + telekomIncidentiUkupno + posluziteljskiIncidentiUkupno;
			
			dijagramBarChart.getData().clear();

			XYChart.Series<String, Number> serija1 = new XYChart.Series<String, Number>();
			serija1.getData().add(new XYChart.Data<String, Number>("Mre�ni ure�aji", mre�niIncidentiUkupno));
			serija1.getData().add(new XYChart.Data<String, Number>("Telekomunikacijski ure�aji", telekomIncidentiUkupno));
			serija1.getData().add(new XYChart.Data<String, Number>("Poslu�itelji", posluziteljskiIncidentiUkupno));
			serija1.getData().add(new XYChart.Data<String, Number>("Ukupno incidenata", sveZajedno));
			
			dijagramBarChart.getData().add(serija1);

			dijagramBarChart.setBarGap(1);
			dijagramBarChart.setCategoryGap(199);
			
			yIncidentiAxis.setUpperBound(sveZajedno+1);
			yIncidentiAxis.setTickUnit(1);
			yIncidentiAxis.setMinorTickVisible(false);			

			for (final XYChart.Data<String, Number> vrijednost : serija1.getData()) {
				vrijednost.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent doga�aj) {
						Tooltip.install(vrijednost.getNode(),
								new Tooltip(vrijednost.getXValue() + " = " + Math.round((double) vrijednost.getYValue())));
						skriveni.setText(vrijednost.getXValue() + " = " + Math.round((double) vrijednost.getYValue()));

					}
				});

//				/Prilikom izlaska sa mi�em bri�e se tekst unutar Labela.
//				vrijednost.getNode().addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
//
//					@Override
//					public void handle(MouseEvent event) {
//						skriveni.setText("");
//
//					}
//				});
				
			}
		} catch (Exception e) {
			MojPopUp.porukaPopUp(AlertType.ERROR, "GRE�KA!", "Poruka:"+e.getMessage()+" Uzrok:"+e.getCause());
			e.printStackTrace();
		}

	}

}
