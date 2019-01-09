package hr.tvz.java.dipl.mb.iznimke;

public class RegexException extends RuntimeException{

	public RegexException(){
		super("Pogreška prilikom unosa! Provjerite unesene podatke.");
		//super("Neispravan rad pograma bacanje oznaèene iznimke na koju program ne utjeèe");
	}

	public RegexException(String poruka){
		super(poruka);
	}

	public RegexException(Throwable uzrok){
		super(uzrok);
	}

	public RegexException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}

}
	
	