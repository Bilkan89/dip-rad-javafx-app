ALTER TABLE EVIDENTIRANJE.ADRESA ALTER COLUMN grad VARCHAR(30) NOT NULL;
ALTER TABLE EVIDENTIRANJE.ADRESA ALTER COLUMN ulica VARCHAR(30) NOT NULL;
ALTER TABLE EVIDENTIRANJE.ADRESA ALTER COLUMN korisnik_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY;
ALTER TABLE EVIDENTIRANJE.PRISTUP ALTER COLUMN lozinka CHAR(8) NOT NULL;

SELECT ime,prezime,kontakt_broj,napomena,datum_kreiranja,drzava,grad,ulica,kucni_broj,korisnicko_ime,lozinka FROM EVIDENTIRANJE.KORISNIK INNER JOIN EVIDENTIRANJE.ADRESA ON EVIDENTIRANJE.KORISNIK.korisnik_id = EVIDENTIRANJE.ADRESA.korisnik_id INTER JOIN EVIDENTIRANJE.PRISTUP ON EVIDENTIRANJE.ADRESA.korisnik_id = EVIDENTIRANJE.PRISTUP.korisnik_id;

UPDATE EVIDENTIRANJE.KORISNIK SET prezime = 'Bilić' WHERE korisnik_ID = 1;

ALTER TABLE EVIDENTIRANJE.INCIDENT_POÈETAK RENAME TO INCIDENT_POCETAK;


INSERT INTO EVIDENTIRANJE.KORISNIK(ime,prezime,kontakt_broj, napomena, datum_kreiranja) 
	VALUES ('Matej', 'Biliæ', 0911755590, 'Administrator aplikacije, uredno popunjava','2017-05-04');

INSERT INTO EVIDENTIRANJE.KORISNIK(ime,prezime,kontakt_broj, napomena, datum_kreiranja) 
	VALUES ('Pero', 'Perić', 0911755590, 'Operator aplikacije, uredno popunjava','2017-05-04');
	
INSERT INTO EVIDENTIRANJE.ADRESA(drzava,grad,ulica,kucni_broj, korisnik_id) 
	VALUES ('Hrvatska','Osijek','Brijestova',13,1);
	
INSERT INTO EVIDENTIRANJE.PRISTUP(korisnicko_ime,lozinka,korisnik_id)
	VALUES ('mbilic','c227114d',1);
	
	
	
	INSERT INTO EVIDENTIRANJE.ADRESA(drzava,grad,ulica,kucni_broj, korisnik_id) 
	VALUES ('Hrvatska','Osijek','Brijestova',13,1);
	
INSERT INTO EVIDENTIRANJE.PRISTUP(korisnicko_ime,lozinka,korisnik_id)
	VALUES ('mbilic','c227114d',1);
	
	
	
	
INSERT INTO EVIDENTIRANJE.KORISNIK(ime,prezime,kontakt_broj,napomena,datum_kreiranja,korisnik_id) values 
	('Pero','Perić',0911234567,'Operator aplikacije','2018-10-25',2);

INSERT INTO EVIDENTIRANJE.ADRESA(drzava,grad,ulica,kucni_broj, korisnik_id)
	VALUES ('Hrvatska','Zagreb','Gruška',22,2);
	
INSERT INTO EVIDENTIRANJE.PRISTUP(korisnicko_ime,lozinka,korisnik_id)
	VALUES ('pperic','hdklaid',2);
	
	--Zasto uvijek nastavlja brojati iako su samo dva reda u tablici on prikazuje 1 i 4 pod korisnik id??
//-------------------------------------------------------------------------
SELECT * FROM EVIDENTIRANJE.INCIDENT_KRAJ;
SELECT * FROM EVIDENTIRANJE.INCIDENT_POCETAK;
SELECT * FROM EVIDENTIRANJE.INCIDENT_TRAJANJE;
SELECT * FROM EVIDENTIRANJE.KATEGORIJA;
SELECT * FROM EVIDENTIRANJE.PRIORITET;
SELECT * FROM EVIDENTIRANJE.VRSTA;

INSERT INTO EVIDENTIRANJE.INCIDENT_POCETAK(broj_naloga,pocetak_d,pocetak_t, zahvaceni_uredaji, napomena,incident_rjesava,rjesen,korisnik_id,vrsta_id,prioritet_id,kategorija_id) 
	VALUES (100, '2019-1-1', '14:30:00', 'Preklopnik', 'Kat 1. soba 26.','M.Bilic',false,1,2,3,1);
	
INSERT INTO EVIDENTIRANJE.INCIDENT_KRAJ (kraj_id,kraj_d,kraj_t,broj_naloga) VALUES (1,'2019-01-6','17:30:00',100);	
	
SELECT incident_pocetak.broj_naloga,pocetak_d,pocetak_t, zahvaceni_uredaji, napomena,incident_rjesava,rjesen,korisnik_id,vrsta_id,prioritet_id,kategorija_id,kraj_d,kraj_t 
			FROM EVIDENTIRANJE.INCIDENT_POCETAK INNER JOIN EVIDENTIRANJE.INCIDENT_KRAJ 
			ON EVIDENTIRANJE.INCIDENT_POCETAK.broj_naloga = EVIDENTIRANJE.INCIDENT_KRAJ.broj_naloga;	
	
INSERT INTO EVIDENTIRANJE.KORISNIK(ime,prezime,kontakt_broj, napomena, datum_kreiranja) 
	VALUES ('Matej', 'Bili�', 0911755590, 'Administrator aplikacije, uredno popunjava','2017-05-04');
	
INSERT INTO EVIDENTIRANJE.ADRESA(drzava,grad,ulica,kucni_broj, korisnik_id) 
	VALUES ('Hrvatska','Osijek','Brijestova',13,1);
	
INSERT INTO EVIDENTIRANJE.PRISTUP(korisnicko_ime,lozinka,korisnik_id)
	VALUES ('mbilic','c227114d',1);	
	
	
	
	
	
	
	
	
	
	
	
	
	
	