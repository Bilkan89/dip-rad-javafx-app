package hr.tvz.java.dipl.mb.controller;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class IzvjesceUkupnoController {

	@FXML
	private LineChart<String, Number> dijagram;
	@FXML
	private DatePicker datumOd;
	@FXML
	private DatePicker datumDo;	
	@FXML
	private Label skriveni;

	@FXML
	private void prikaziBtn(ActionEvent event) {
		dijagram.getData().clear();
		Random rand = new Random();
		int broj1 = rand.nextInt(50);
		int broj2 = rand.nextInt(50);
		int broj3 = rand.nextInt(50);
		int broj4 = rand.nextInt(50);

		XYChart.Series<String, Number> serija = new XYChart.Series<String, Number>();
		// povezati dane sa brojem incidenata.. a kad se obabere više od jednog
		// mjeseca onda prikazati po mjesecu??
		serija.getData().add(new XYChart.Data<String, Number>("Sijeèanj", broj1));
		serija.getData().add(new XYChart.Data<String, Number>("Veljaèa", broj2));
		serija.getData().add(new XYChart.Data<String, Number>("Ožuljak", broj3));
		serija.getData().add(new XYChart.Data<String, Number>("Travanj", broj4));
		serija.getData().add(new XYChart.Data<String, Number>("Svibanj", 0));
		serija.getData().add(new XYChart.Data<String, Number>("Lipanj", 0));
		serija.getData().add(new XYChart.Data<String, Number>("Srpanj", 0));
		serija.getData().add(new XYChart.Data<String, Number>("Kolovoz", 0));
		serija.getData().add(new XYChart.Data<String, Number>("Rujan", 0));
		serija.getData().add(new XYChart.Data<String, Number>("Listopad", 0));
		serija.getData().add(new XYChart.Data<String, Number>("Studeni", 0));
		serija.getData().add(new XYChart.Data<String, Number>("Prosinac", 0));
		dijagram.getData().add(serija);

		for (final XYChart.Data<String, Number> podatak : serija.getData()) {
			podatak.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					Tooltip.install(podatak.getNode(),
							new Tooltip(podatak.getXValue() + " - " + String.valueOf(podatak.getYValue())));
					skriveni.setText(podatak.getXValue() + " - " + String.valueOf(podatak.getYValue()));
					// System.out.println("a");
				}
			});

			podatak.getNode().addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					skriveni.setText(" ");
				}
			});

		}

	}

}
