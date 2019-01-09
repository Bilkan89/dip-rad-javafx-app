package hr.tvz.java.dipl.mb.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class IzvjescePoKategorijiController {

	@FXML
	private BarChart<String, Number> dijagramBarChart;
	@FXML
	private DatePicker datumKraj;
	@FXML
	private DatePicker datumPocetak;
	@FXML
	private Label skriveni;

	@FXML
	private void initialize() {
	}

	@FXML
	private void PrikaziBtn(ActionEvent event) {

		dijagramBarChart.getData().clear();

		XYChart.Series<String, Number> serija1 = new XYChart.Series<String, Number>();
		serija1.getData().add(new XYChart.Data<String, Number>("Mrežni ureðaji", 15));
		serija1.getData().add(new XYChart.Data<String, Number>("Telekomunikacijski ureðaji", 16));
		serija1.getData().add(new XYChart.Data<String, Number>("Poslužitelji", 25));
		serija1.getData().add(new XYChart.Data<String, Number>("Ukupno incidenata", 80));

		dijagramBarChart.getData().add(serija1);

		for (final XYChart.Data<String, Number> vrijednost : serija1.getData()) {
			vrijednost.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent dogaðaj) {
					Tooltip.install(vrijednost.getNode(),
							new Tooltip(vrijednost.getXValue() + " - " + Math.round((double) vrijednost.getYValue())));
					skriveni.setText(vrijednost.getXValue() + " - " + Math.round((double) vrijednost.getYValue()));

				}
			});

			vrijednost.getNode().addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					skriveni.setText("");

				}
			});
		}

	}

}
