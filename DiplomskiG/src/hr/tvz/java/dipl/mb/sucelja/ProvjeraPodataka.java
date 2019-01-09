package hr.tvz.java.dipl.mb.sucelja;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import hr.tvz.java.dipl.mb.iznimke.RegexException;

public interface ProvjeraPodataka {
	
	public default void provjeraSlova(String zaProvjeru ) throws RegexException{
		//Default methods enable you to add new functionality to the interfaces
		//of your libraries and ensure binary compatibility with code written for older versions of those interfaces.
		
		//pronaði jedino mala ili velika slova na poèetku, koja se ponavljaju 4 ili više puta. 
		Pattern patternSlova = Pattern.compile("^[a-zA-Z]{4,}$");			
		Matcher matcherSl = patternSlova.matcher(zaProvjeru);	
					
		if(!matcherSl.find()){
			throw new RegexException();
		}		
	}
		
		
	public default void provjeraBrojevi(int zaProvjeru) throws RegexException {
		
		//pronaði jedino brojeve koji se pojavljuju na poèetku jedan ili više puta
		Pattern patternBrojevi = Pattern.compile("^(\\d+)$");  
		Matcher matcherBr = patternBrojevi.matcher(String.valueOf(zaProvjeru));
		
		if(!matcherBr.find()){
			throw new RegexException();
		}		
	}
	
	public default int samoPozitivniBr(int broj) throws NumberFormatException{
		if(broj < 0 ){
			throw new NumberFormatException();
		}else{
			return broj;
		}
	}
	
	public default void provjeraVremena(LocalDate pocetak, LocalDate kraj) throws DateTimeException{
		if(kraj.equals(pocetak) || kraj.isBefore(pocetak)){
			throw new DateTimeException("Vrijeme kraja je manji ili jednak vremenu poèetka");
		}
		
		
	}
	
	
	
	
	
	
	
//	//PITANJE ZA PROF. DA LI JE BOLJE UBACITI U KONSTRUKTOR BEZ POZIVANJA METODE
//	private void provjeraUnosaPodataka() throws RegexException{
//			//pronaði jedino mala ili velika slova na poèetku, koja se ponavljaju 4 ili više puta. 
//		Pattern patternSlova = Pattern.compile("^[a-zA-Z]{4,}$"); 		
//			//pronaði jedino brojeve koji se pojavljuju na poèetku jedan ili više puta
//		Pattern patternBrojevi = Pattern.compile("^(\\d+)$");  
//		
//		Matcher matcherDr = patternSlova.matcher(drzava);
//		Matcher matcherGr = patternSlova.matcher(grad);
//		Matcher matcherUl = patternSlova.matcher(ulica);
//				
//		if(matcherDr.find() && matcherGr.find() && matcherUl.find()){
//			
//		}else {
//			throw new RegexException();
//		}
//		
//		Matcher matcherBr = patternBrojevi.matcher(kucniBroj);
//		if(matcherBr.find()){
//			
//		}else {
//			throw new RegexException();
//		}		
//	}
	
}
