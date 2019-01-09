INSERT INTO EVIDENTIRANJE.KORISNIK(ime,prezime,kontakt_broj, napomena, datum_kreiranja) 
	VALUES ('Matej', 'Biliæ', 0911755590, 'Administrator aplikacije, uredno popunjava','2017-05-04');
	
INSERT INTO EVIDENTIRANJE.ADRESA(drzava,grad,ulica,kucni_broj, korisnik_id) 
	VALUES ('Hrvatska','Osijek','Brijestova',13,1);
	
INSERT INTO EVIDENTIRANJE.PRISTUP(korisnicko_ime,lozinka,korisnik_id)
	VALUES ('mbilic','c227114d',1);