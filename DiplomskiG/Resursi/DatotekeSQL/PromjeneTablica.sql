ALTER TABLE EVIDENTIRANJE.ADRESA ALTER COLUMN grad VARCHAR(30) NOT NULL;
ALTER TABLE EVIDENTIRANJE.ADRESA ALTER COLUMN ulica VARCHAR(30) NOT NULL;
ALTER TABLE EVIDENTIRANJE.ADRESA ALTER COLUMN korisnik_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY;
ALTER TABLE EVIDENTIRANJE.PRISTUP ALTER COLUMN lozinka CHAR(8) NOT NULL;

SELECT ime,prezime,kontakt_broj,napomena,datum_kreiranja,drzava,grad,ulica,kucni_broj,korisnicko_ime,lozinka FROM EVIDENTIRANJE.KORISNIK INNER JOIN EVIDENTIRANJE.ADRESA ON EVIDENTIRANJE.KORISNIK.korisnik_id = EVIDENTIRANJE.ADRESA.korisnik_id INTER JOIN EVIDENTIRANJE.PRISTUP ON EVIDENTIRANJE.ADRESA.korisnik_id = EVIDENTIRANJE.PRISTUP.korisnik_id;

UPDATE EVIDENTIRANJE.KORISNIK SET prezime = 'Bilić' WHERE korisnik_ID = 1;

ALTER TABLE EVIDENTIRANJE.INCIDENT_POÈETAK RENAME TO INCIDENT_POCETAK;