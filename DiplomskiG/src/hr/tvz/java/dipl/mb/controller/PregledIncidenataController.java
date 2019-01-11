package hr.tvz.java.dipl.mb.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.dipl.mb.entitet.Incident;
import hr.tvz.java.dipl.mb.entitet.KrajIncidenta;
import hr.tvz.java.dipl.mb.entitet.PocetakIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.KategorijeIncidenata;
import hr.tvz.java.dipl.mb.enumeracije.PrioritetiIncidenta;
import hr.tvz.java.dipl.mb.enumeracije.VrstaAlarma;
import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciIncident;



public class PregledIncidenataController {

	@FXML
	private TableView<Incident> tabIncidenti;
	
	@FXML
    private TableColumn<Incident, Integer> brojNaloga;
	
	@FXML
	private TableColumn<PocetakIncidenta, LocalDate> datumPocetak;

	@FXML
	private TableColumn<PocetakIncidenta, LocalTime> pocetakSat;
	
	@FXML
	private TableColumn<KrajIncidenta, LocalDate> krajDatum;

	@FXML
	private TableColumn<KrajIncidenta, LocalTime> krajVrijeme;
	
	@FXML
	private TableColumn<KrajIncidenta, String> trajanjeDatum;

	@FXML
	private TableColumn<KrajIncidenta, String> trajanjeVrijeme;
	
    @FXML
    private TableColumn<Incident, KategorijeIncidenata> vrstaIncidenta;	
   
    @FXML
    private TableColumn<Incident, String> zahvacenaOprema;

    @FXML
    private TableColumn<Incident, VrstaAlarma> vrstaAlarma;
    
    @FXML
    private TableColumn<Incident, String> napomena;
    
    @FXML
    private TableColumn<Incident, String> incidentRjesava;
    
    @FXML
    private TableColumn<Incident, String> rijesenIncident;
    
    @FXML
    private TableColumn<Incident, PrioritetiIncidenta> prioritet;
    
    //------------------------------------------------------------------
//    @FXML
//    private TableColumn<?, ?> trajanjeIncidenta;
    
//    @FXML
//    private TableColumn<?, ?> pocetakIncidenta;        

//    @FXML
//    private TableColumn<?, ?> krajIncidenta;
   
    @FXML
    private CheckBox rijeseniCheckBox;
    
    @FXML
    private CheckBox nerijeseniCheckBox;

    @FXML
    private CheckBox tkCheckBox;
    
    @FXML
    private CheckBox mrezniCheckBox;

    @FXML
    private CheckBox serverCheckBox;
    
    Incident odabrani = null;
	
	@FXML
	private void initialize(){
		
		brojNaloga.setCellValueFactory(
				new PropertyValueFactory<Incident, Integer>("brojNaloga"));
		
		datumPocetak.setCellValueFactory(
				new PropertyValueFactory<PocetakIncidenta, LocalDate>("pocetakDatum"));
				
		pocetakSat.setCellValueFactory(
				new PropertyValueFactory<PocetakIncidenta, LocalTime>("pocetakVrijeme"));	
		
		krajDatum.setCellValueFactory(
				new PropertyValueFactory<KrajIncidenta, LocalDate>("krajDatum"));
				
		krajVrijeme.setCellValueFactory(
				new PropertyValueFactory<KrajIncidenta, LocalTime>("krajVrijeme"));	
		
		trajanjeDatum.setCellValueFactory(
				new PropertyValueFactory<KrajIncidenta, String>("trajanjeDana"));
		
		trajanjeVrijeme.setCellValueFactory(
				new PropertyValueFactory<KrajIncidenta, String>("trajanjeVrijeme"));
		
		vrstaIncidenta.setCellValueFactory(
				new PropertyValueFactory<Incident, KategorijeIncidenata>("kategIncidenta"));
		
		zahvacenaOprema.setCellValueFactory(
				new PropertyValueFactory<Incident, String>("zahvacenUredaji"));
		
		vrstaAlarma.setCellValueFactory(
				new PropertyValueFactory<Incident, VrstaAlarma>("tipAlarma"));
		
		napomena.setCellValueFactory(
				new PropertyValueFactory<Incident, String>("napomena"));		
		
		incidentRjesava.setCellValueFactory(
				new PropertyValueFactory<Incident, String>("incidentRjesava"));
		
		rijesenIncident.setCellValueFactory(
				new PropertyValueFactory<Incident, String>("rijesen"));
		
		prioritet.setCellValueFactory(
				new PropertyValueFactory<Incident, PrioritetiIncidenta>("prioriteti"));
		
		
		/*
		 * 
		 * 	imeK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, String>("ime"));		
		prezimeK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, String>("prezime"));
		
		
		drzavaK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, Adresa>("adresaKorisnika"));
				
				
		drzavaK.setCellFactory(new Callback<TableColumn<Korisnik,Adresa>, TableCell<Korisnik,Adresa>>() {
			
			@Override
			public TableCell<Korisnik, Adresa> call(TableColumn<Korisnik, Adresa> param) {
				
				final TableCell<Korisnik, Adresa> cell = new TableCell<Korisnik, Adresa>(){
					
					@Override
					public void updateItem(final Adresa item, boolean empty){
						super.updateItem(item, empty);
						if(empty){
							this.setText("");
						}else{
							this.setText(item.getDrzava());
						}						
					};					
				};				
				return cell;
			}
		});
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
	}
	
	@FXML
	private void filtrirajBtn() {
		
		
		try {
			List<Incident> testListaPocetak = PodatciIncident.dohvatiIncidente();
			List<Incident> sortiraniIncidneti = new ArrayList<Incident>();
			
		//	for(Incident incident :  testListaPocetak) {
			
			//------------------------------------------------------------------------------------------
				if(mrezniCheckBox.isSelected()) {
					for(Incident incident :  testListaPocetak) {
						if(incident.getKategIncidenta() == KategorijeIncidenata.MREŽNI)
							sortiraniIncidneti.add(incident);
					}					
				}
				
				
				if(rijeseniCheckBox.isSelected()) {
					for(Incident incident :  testListaPocetak) {
						if(incident.getRijesen() == "DA")
							sortiraniIncidneti.add(incident);
					}					
				}
				
				
				if(nerijeseniCheckBox.isSelected()) {
					for(Incident incident :  testListaPocetak) {
						if(incident.getRijesen() == "NE")
							sortiraniIncidneti.add(incident);
					}					
				}
				
				if(tkCheckBox.isSelected()) {
					for(Incident incident :  testListaPocetak) {
						if(incident.getKategIncidenta() == KategorijeIncidenata.TELEKOMUNIKACIJSKI)
							sortiraniIncidneti.add(incident);
					}					
				}
				
				if(serverCheckBox.isSelected()) {
					for(Incident incident :  testListaPocetak) {
						if(incident.getKategIncidenta() == KategorijeIncidenata.POSLUŽITELJSKI)
							sortiraniIncidneti.add(incident);
					}					
				}
				
				sortiraniIncidneti = sortiraniIncidneti.stream().distinct().collect(Collectors.toList());
			//------------------------------------------------------------------------------------------
		
				
				
				
			
//			List<Casopis> DohvaceniCasopisi;
//			try {
//				DohvaceniCasopisi = PodaciIzBazeCasopisi.dohvatiCasopiseIzBaze();
//				List<Casopis> ListaCasopisaFilter = new ArrayList<Casopis>();
//				if (UnosCasopisaTextField.getText().isEmpty() == false) {
//					ListaCasopisaFilter = DohvaceniCasopisi.stream()
//							.filter(e -> e.getNazivPublikacije()
//									.contains(UnosCasopisaTextField.getText()))
//							.collect(Collectors.toList());
//				} else {
//					ListaCasopisaFilter = DohvaceniCasopisi;
//				}
			
			
			
			ObservableList<Incident> listaZaPrikaz = FXCollections.observableArrayList(sortiraniIncidneti);	
			
			tabIncidenti.setItems(listaZaPrikaz);
			
		} catch (Exception e) {
//			System.out.println("GREŠKA!! ERROR!! /n"+
//							   "DOGODILA SE GREŠKA: "+e.getMessage()+" n/ "+
//							   "UZROK: "+e.getCause());
			e.printStackTrace();
		}	
	}	
	
	@FXML
	protected void pozoviZatvaranjeIncidenta() {
		try {
			String lokacijaFxml = "/fxml/fxml_ZatvoriIncident.fxml";
			odabrani = tabIncidenti.getSelectionModel().getSelectedItem();
			System.out.println(odabrani.getBrojNaloga());
			if(odabrani instanceof KrajIncidenta) {
				MojPopUp.porukaPopUp(AlertType.WARNING,"Upozorenje!", "Incident sa brojem naloga:  "+odabrani.getBrojNaloga()+" je već zatvoren!" );
			}else {			
				//PocetniZaslonController.otvoriNoviProzor(lokacijaFxml);	
				
				FXMLLoader loadFxml = new FXMLLoader(getClass().getResource(lokacijaFxml));
			
				BorderPane novi = loadFxml.load();
				
				ZatvoriIncidentController controllerZ = loadFxml.<ZatvoriIncidentController>getController();
				
				controllerZ.dohvatiOdabraniIncident(odabrani);
				controllerZ.dohvatiTabView(tabIncidenti);
				
				
				Scene noviScene = new Scene(novi);
				Stage noviStage = new Stage();
				noviStage.setScene(noviScene);
				noviStage.show();
				
//				tabIncidenti.getItems().remove(
//						tabIncidenti.getSelectionModel().getSelectedItem());
//				
//				LocalDate datTest = controllerZ.getZavDatum();
//				LocalTime timeTest = controllerZ.getZavVrijeme();
//				
//				System.out.println(datTest+" "+timeTest);
				
				/*
				 * 
				 * 
				 * 
				 * FXMLLoader loadFXML = new FXMLLoader(
					getClass().getResource("/fxml/fxml_UnosClana.fxml"));
			
			BorderPane root = (BorderPane) loadFXML.load();
			
			UnosClanaController cont = loadFXML
					.<UnosClanaController>getController();
			
			cont.urediParametreClana(
					ViewClan.getSelectionModel().getSelectedItem());
			
			ViewClan.getItems()
					.remove(ViewClan.getSelectionModel().getSelectedItem());
			Main.setCenterPane(root);
				 * 
				 * */
				
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	public TableView<Incident> dohvatiOdabraniIncident() {
		//Incident odabraniIn = tabIncidenti.getSelectionModel().getSelectedItem();
//		System.out.println(odabrani.getBrojNaloga());
		TableView<Incident> table = tabIncidenti;
		return table;
	}
	
	
	
	
}
