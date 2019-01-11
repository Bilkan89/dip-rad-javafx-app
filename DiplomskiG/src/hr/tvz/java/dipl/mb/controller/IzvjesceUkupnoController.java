package hr.tvz.java.dipl.mb.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciIncident;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class IzvjesceUkupnoController {

	@FXML
	private LineChart<String, Integer> dijagram;

	@FXML
	private Label skriveni;
	
	@FXML
	private NumberAxis numberAxis;

	@FXML
	private void prikaziBtn(ActionEvent event) {
		
		
		try {
			dijagram.getData().clear();
			Random rand = new Random();
			int broj1 = rand.nextInt(50);
			int broj2 = rand.nextInt(50);
			int broj3 = rand.nextInt(50);
			int broj4 = rand.nextInt(50);
			
			
			List<LocalDate> datumiIncidenata = PodatciIncident.dohvatiDatumIncidenata();
			
			int sijecanj = 0;
			int veljaca = 0;
			int ozujak = 0;
			int travanj = 0;
			int svibanj = 0;
			int lipanj = 0;
			int srpanj = 0;
			int kolovz = 0;
			int rujan = 0;
			int listopad = 0;
			int studeni = 0;
			int prosinac = 0;

			for (LocalDate datumI : datumiIncidenata) {
				if(datumI.getMonthValue() == 1) {
					sijecanj++;
				}
				if(datumI.getMonthValue() == 2) {
					veljaca++;
				}
				if(datumI.getMonthValue() == 3) {
					ozujak++;
				}
				if(datumI.getMonthValue() == 4) {
					travanj++;
				}
				if(datumI.getMonthValue() == 5) {
					svibanj++;
				}
				if(datumI.getMonthValue() == 6) {
					lipanj++;
				}
				if(datumI.getMonthValue() == 7) {
					srpanj++;
				}
				if(datumI.getMonthValue() == 8) {
					kolovz++;
				}
				if(datumI.getMonthValue() == 9) {
					rujan++;
				}
				if(datumI.getMonthValue() == 10) {
					listopad++;
				}
				if(datumI.getMonthValue() == 11) {
					studeni++;
				}
				if(datumI.getMonthValue() == 12) {
					prosinac++;
				}
			}
			
			System.out.println("incidneni po mje:"+sijecanj+veljaca+ozujak+travanj+svibanj+lipanj+srpanj+kolovz+rujan+listopad+studeni+prosinac);
			System.out.println("svibanj:"+sijecanj);
			System.out.println("svibanj:"+svibanj);
			
			List<Integer> maxBroj = new ArrayList<Integer>();
			maxBroj.add(sijecanj);
			maxBroj.add(veljaca);
			maxBroj.add(ozujak);
			maxBroj.add(travanj);
			maxBroj.add(svibanj);
			maxBroj.add(lipanj);
			maxBroj.add(srpanj);
			maxBroj.add(kolovz);
			maxBroj.add(rujan);
			maxBroj.add(listopad);
			maxBroj.add(studeni);
			maxBroj.add(prosinac);
			int najveciMjesec=Collections.max(maxBroj);
			
			
			
			XYChart.Series<String, Integer> serija = new XYChart.Series<String, Integer>();
			// povezati dane sa brojem incidenata.. a kad se obabere vi�e od jednog
			// mjeseca onda prikazati po mjesecu??
			serija.getData().add(new XYChart.Data<String, Integer>("Sije�anj", sijecanj));
			serija.getData().add(new XYChart.Data<String, Integer>("Velja�a", veljaca));
			serija.getData().add(new XYChart.Data<String, Integer>("O�uljak", ozujak));
			serija.getData().add(new XYChart.Data<String, Integer>("Travanj", travanj));
			serija.getData().add(new XYChart.Data<String, Integer>("Svibanj", svibanj));
			serija.getData().add(new XYChart.Data<String, Integer>("Lipanj", lipanj));
			serija.getData().add(new XYChart.Data<String, Integer>("Srpanj", srpanj));
			serija.getData().add(new XYChart.Data<String, Integer>("Kolovoz", kolovz));
			serija.getData().add(new XYChart.Data<String, Integer>("Rujan", rujan));
			serija.getData().add(new XYChart.Data<String, Integer>("Listopad", listopad));
			serija.getData().add(new XYChart.Data<String, Integer>("Studeni", studeni));
			serija.getData().add(new XYChart.Data<String, Integer>("Prosinac", prosinac));
			dijagram.getData().add(serija);
			//dijagram.getXAxis().set
			//numberAxis.setMinorTickVisible(false);
			//numberAxis.setTickLabelFormatter(value);
			//stavi ispred broja i/ili iza
			//numberAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(numberAxis,"da","dB"));
			
			
			//numberAxis.setAutoRanging(false);
			//numberAxis.setLowerBound(1000);
			//System.out.println("najveci mj:"+najveciMjesec);
			numberAxis.setUpperBound(najveciMjesec+1);
			numberAxis.setTickUnit(1);
			numberAxis.setMinorTickVisible(false);
			
			
			
			for (final XYChart.Data<String, Integer> podatak : serija.getData()) {
				podatak.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						Tooltip.install(podatak.getNode(),
								new Tooltip(podatak.getXValue() + " = " + String.valueOf(podatak.getYValue())));
						skriveni.setText(podatak.getXValue() + " = " + String.valueOf(podatak.getYValue()));
						// System.out.println("a");
					}
				});

//				podatak.getNode().addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
//
//					@Override
//					public void handle(MouseEvent event) {
//						skriveni.setText(" ");
//					}
//				});

			}
		
		} catch (Exception e) {
			MojPopUp.porukaPopUp(AlertType.ERROR, "GRE�KA!", "Poruka:"+e.getMessage()+" Uzrok:"+e.getCause());
			e.printStackTrace();
		}

	}

}
