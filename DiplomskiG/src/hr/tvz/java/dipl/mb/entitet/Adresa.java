package hr.tvz.java.dipl.mb.entitet;

import hr.tvz.java.dipl.mb.sucelja.ProvjeraPodataka;

public class Adresa implements ProvjeraPodataka{
	
	private String drzava;
	private String grad;
	private String ulica;
	private int kucniBroj; //TODO: implementirati regexe, kucnibroj moze biti 22a 22b..
	private int idAdresa;
	
	public Adresa(String drzava, String grad, String ulica, int kucniBroj) {
		this.drzava = drzava;
		this.grad = grad;
		this.ulica = ulica;
		this.kucniBroj = kucniBroj;		
//		provjeraSlova(drzava);
//		provjeraSlova(grad);
//		provjeraSlova(ulica);
//		provjeraBrojevi(kucniBroj);
	}	


	
	
	/**
	 * @return the idAdresa
	 */
	public int getIdAdresa() {
		return idAdresa;
	}


	/**
	 * @param idAdresa the idAdresa to set
	 */
	public void setIdAdresa(int idAdresa) {
		this.idAdresa = idAdresa;
	}


	/**
	 * @return the drzava
	 */
	public String getDrzava() {
		return drzava;
	}


	/**
	 * @return the grad
	 */
	public String getGrad() {
		return grad;
	}


	/**
	 * @return the ulica
	 */
	public String getUlica() {
		return ulica;
	}


	/**
	 * @return the kucniBroj
	 */
	public int getKucniBroj() {
		return kucniBroj;
	}
	
	
	
	
	
	
	
}
