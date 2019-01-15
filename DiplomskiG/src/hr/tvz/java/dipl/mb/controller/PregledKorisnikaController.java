package hr.tvz.java.dipl.mb.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.tvz.java.dipl.mb.entitet.Adresa;
import hr.tvz.java.dipl.mb.entitet.Korisnik;
import hr.tvz.java.dipl.mb.sucelja.MojPopUp;
import hr.tvz.java.dipl.mb.sucelja.PodatciKorisnika;


public class PregledKorisnikaController implements MojPopUp{

	@FXML
	private TextField pretraziTextF;
	
	@FXML
	private TableView<Korisnik> viewKorisnika;

	@FXML
	private TableColumn<Korisnik, String> imeK;

	@FXML
	private TableColumn<Korisnik, String> prezimeK;

	@FXML
	private TableColumn<Korisnik, Adresa> drzavaK;

	@FXML
	private TableColumn<Korisnik, Adresa> gradK;

	@FXML
	private TableColumn<Korisnik, Adresa> ulicaK;

	@FXML
	private TableColumn<Korisnik, Adresa> kucniBrojK;

	@FXML
	private TableColumn<Korisnik, Integer> kontaktBrojK;

	@FXML
	private TableColumn<Korisnik, String> korisnickoImeK;

	@FXML
	private TableColumn<Korisnik, String> napomenaK;

	@FXML
	private TableColumn<Korisnik, Date> datumKreiranja;

	@FXML
	private Label statusSkriveni;
	
	@FXML
	private Tooltip toolTip;
	
	@FXML
	private Button pretraziButton;
	
	@FXML
	private void initialize() {
		imeK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, String>("ime"));		
		prezimeK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, String>("prezime"));
		
		
		drzavaK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, Adresa>("adresaKorisnika"));
		//ako nema set cell factory onda ispise ugurubo...
		
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
		
		
		gradK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, Adresa>("adresaKorisnika"));
		gradK.setCellFactory(new Callback<TableColumn<Korisnik,Adresa>, TableCell<Korisnik,Adresa>>() {
				
				@Override
				public TableCell<Korisnik, Adresa> call(TableColumn<Korisnik, Adresa> param) {
					
					final TableCell<Korisnik, Adresa> cell = new TableCell<Korisnik, Adresa>(){
						
						@Override
						public void updateItem(final Adresa item, boolean empty){
							super.updateItem(item, empty);
							if(empty){
								this.setText("");
							}else{
								this.setText(item.getGrad());
							}						
						};					
					};				
					return cell;
				}
			});
		
		ulicaK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, Adresa>("adresaKorisnika"));
		ulicaK.setCellFactory(new Callback<TableColumn<Korisnik,Adresa>, TableCell<Korisnik,Adresa>>() {
			
			@Override
			public TableCell<Korisnik, Adresa> call(TableColumn<Korisnik, Adresa> param) {
				
				final TableCell<Korisnik, Adresa> cell = new TableCell<Korisnik, Adresa>(){
					
					@Override
					public void updateItem(final Adresa item, boolean empty){
						super.updateItem(item, empty);
						if(empty){
							this.setText("");
						}else{
							this.setText(item.getUlica());
						}						
					};					
				};				
				return cell;
			}
		});
		
		
		kucniBrojK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, Adresa>("adresaKorisnika"));
		kucniBrojK.setCellFactory(new Callback<TableColumn<Korisnik,Adresa>, TableCell<Korisnik,Adresa>>() {
					
					@Override
					public TableCell<Korisnik, Adresa> call(TableColumn<Korisnik, Adresa> param) {
						
						final TableCell<Korisnik, Adresa> cell = new TableCell<Korisnik, Adresa>(){
							
							@Override
							public void updateItem(final Adresa item, boolean empty){
								super.updateItem(item, empty);
								if(empty){
									this.setText("");
								}else{
									this.setText(String.valueOf(item.getKucniBroj()));
								}						
							};					
						};				
						return cell;
					}
				});
		
		
		
		kontaktBrojK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, Integer>("kontaktBroj"));
		
		korisnickoImeK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, String>("korisnickoIme"));
		
		napomenaK.setCellValueFactory(
				new PropertyValueFactory<Korisnik, String>("napomena"));
		datumKreiranja.setCellValueFactory(new PropertyValueFactory<Korisnik, Date>("datumKreiranja"));	
		
	}

	
	public void obrisiKorisnika(){
		Korisnik obrKorisnik = viewKorisnika.getSelectionModel().getSelectedItem();
		viewKorisnika.getItems().remove(obrKorisnik);
		//System.out.println(obrKorisnik.getIdKorisnika());
		try {
			
			PodatciKorisnika.obrisiKorisnika(obrKorisnik);
			MojPopUp.porukaPopUp(AlertType.WARNING,"Upozorenje!", "Korisnik " + obrKorisnik.getIme()+" "+ obrKorisnik.getPrezime()
								+" je uspješno obrisan!");
			
//			Alert alert = new Alert(AlertType.WARNING);
//			alert.setTitle("Upozorenje!!");
//			alert.setContentText("Korisnik " + obrKorisnik.getIme()+" "
//					+ obrKorisnik.getPrezime()+" je uspješno obrisan!");
//			alert.showAndWait();
			
		} catch (Exception e) {
			//popUP(e.getMessage());	
			System.out.println("GREŠKA!! ERROR!! /n"
						  	  +"DOGODILA SE GREŠKA: "+e.getMessage()+" n/ "
						  	  +"UZROK: "+e.getCause()+" /n "
						  	  +"PORUKA: "+e.toString());
			e.printStackTrace();
		}
		
		
		
//		public void obrisi() {
//			Clan clan = ViewClan.getSelectionModel().getSelectedItem();
//			ViewClan.getItems().remove(clan);
//			try {
//				PodaciIzBazeClan.obrisiClana(clan);
//				Alert alert = new Alert(AlertType.INFORMATION);
//				alert.setTitle("Informativno");
//				alert.setContentText("ÈLAN " + clan.getIme() + " "
//						+ clan.getPrezime() + " JE USPJEŠNO OBRISAN!!");
//				alert.showAndWait();
//			} catch (Exception e2) {
//				prikazDialogaGreske("GREŠKA!! ERROR!! ",
//						"DOGODILA SE GREŠKA: " + e2.getMessage() + "  UZROK: "
//								+ e2.getCause() + " PORUKA: " + e2.toString());
//			}
//		}
		
	};
	
	

	@FXML
	private void pretraziBtn(){
		try {
			List<Korisnik> ListaKorisnika = PodatciKorisnika.dohvatiKorisnike();
			List<Korisnik> FiltriranjeKor = new ArrayList<Korisnik>();
			if (pretraziTextF.getText().isEmpty() == false) {
				FiltriranjeKor = ListaKorisnika.stream().filter(
						e -> e.getIme().contains(pretraziTextF.getText()) || 
							 e.getPrezime().contains(pretraziTextF.getText())).collect(Collectors.toList());
			}else {
				FiltriranjeKor = ListaKorisnika;
			}
			
			
			ObservableList<Korisnik> listaZaPrikaz = FXCollections.observableArrayList(FiltriranjeKor);			
			viewKorisnika.setItems(listaZaPrikaz);		
			
			}catch (Exception e) {			
//			popUP(e.getMessage());	
			System.out.println("GREŠKA!! ERROR!! /n"+
								"DOGODILA SE GREŠKA: "+e.getMessage()+" n/ "+
								 "UZROK: "+e.getCause());
			//+" /n PORUKA: "+e.toString());
			e.printStackTrace();
		} 
	}
	
	


	@FXML
	private void ukljuciToolTip() {
		toolTip. setText("Po imenu ili prezimenu..");
		pretraziButton.setTooltip(toolTip);
		//System.out.println("mouseOver");
		
	}
}
