package hr.tvz.java.dipl.mb.iznimke;

public class DuplikatException extends Exception{

	public DuplikatException(){
		super("Pogreška! Unijeli ste veæ postojeæe podatke! Provjerite unesene podatke.");
	}

	public DuplikatException(String poruka){
		super(poruka);
	}

	public DuplikatException(Throwable uzrok){
		super(uzrok);
	}

	public DuplikatException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}

}
	
	