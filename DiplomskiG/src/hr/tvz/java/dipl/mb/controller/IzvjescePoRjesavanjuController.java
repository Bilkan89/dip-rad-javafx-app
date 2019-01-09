package hr.tvz.java.dipl.mb.controller;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

public class IzvjescePoRjesavanjuController {

	@FXML
	private BarChart<String, Number> dijagram;
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
		int broj12 = rand.nextInt(50);

		skriveni.setText("");
		XYChart.Series<String, Number> serija = new XYChart.Series<String, Number>();
		serija.getData().add(new XYChart.Data<String, Number>("Unutar 1h", broj1));
		serija.getData().add(new XYChart.Data<String, Number>("Unutar 3h", broj2));
		serija.getData().add(new XYChart.Data<String, Number>("Unutar 12h", broj3));
		serija.getData().add(new XYChart.Data<String, Number>("Unutar 24h", broj4));
		serija.getData().add(new XYChart.Data<String, Number>("Unutar 24h+", broj12));
		dijagram.getData().add(serija);

		for (final XYChart.Data<String, Number> podatak : serija.getData()) {
			podatak.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					Tooltip.install(podatak.getNode(),
							new Tooltip(podatak.getXValue() + " - " + Math.round((double) podatak.getYValue())));
					skriveni.setText(podatak.getXValue() + " - " + Math.round((double) podatak.getYValue()));
				}
			});

			podatak.getNode().addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					skriveni.setText("");

				}
			});
		}
	}

}
