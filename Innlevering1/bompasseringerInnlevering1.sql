 
 --a)
 --Tabell for bil og bileier:

CREATE TABLE bil (
regnr VARCHAR(100) UNIQUE, -- registreringsnummer for bilen
bileier VARCHAR(100) NOT NULL, -- navn på eieren
adresse VARCHAR(100) NOT NULL, -- adresse til eieren
epost VARCHAR(100) NOT NULL, -- e-post til eieren
nummer INT(100) NOT NULL, --telefonnummer til bileier
CONSTRAINT PK_regnr PRIMARY KEY(regnr)
);

-- Tabell for bompasseringer
CREATE TABLE bompass (
regnr VARCHAR(100) REFERENCES bil(regnr),
passtid DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
bomring VARCHAR(100) NOT NULL,
CONSTRAINT PK_bompass PRIMARY KEY (regnr,passtid),
CONSTRAINT FK_bompass FOREIGN KEY (regnr) REFERENCES bil(regnr)
);


--d)
--legger inn kolonne for om registreringsnummer/skiltnummer ble lest	 
ALTER TABLE bompass
ADD COLUMN SKILTLEST BOOLEAN DEFAULT TRUE;

--legger inn data om uregistrert skiltnummer
INSERT INTO bil (regnr, bileier, adresse, epost, nummer)
VALUES ('N/A', 'Per Olavsen', 'Kallestadveien 137, 6530 Averøy','Per.Olavsen@gmail.com', 65937263);

INSERT INTO bompass (regnr, passtid, bomring, skiltlest)
VALUES ('N/A', '2023-02-02 12:00:00', 'BOMRING1' , false);

	 
--b)
--legge inn testdata
INSERT INTO bil (regnr, bileier, adresse, epost, nummer) 
VALUES 
  ('AA10000', 'Ola Nordmann', 'Strandveien 55, 0257 OSLO', 'ola.nordmann@email.com', '12345678'),
  ('BB20000', 'Kari Pettersen', 'BØLERVEIEN 4, 0791 OSLO', 'kari.pettersen@email.com', '23456789'),
  ('CC30000', 'Erik Hansen', 'KONGENS GATE 13, 5004 BERGEN', 'erik.hansen@email.com', '34567890');


INSERT INTO bompass (regnr, passtid, bomring) 
VALUES
  ('AA10000',CURRENT_TIMESTAMP,'bomring2'),
  ('BB20000',CURRENT_TIMESTAMP,'bomring2'),
  ('CC30000',CURRENT_TIMESTAMP,'bomring3');
	 
--c)
--fjerne telefonnummer fra bil tabell
--ALTER TABLE bil DROP COLUMN NUMMER;
	 

--e)
-- skriver ut info fra bil/bompass tabeller, inkludert skilt som ikke ble lest
SELECT bil.regnr, bil.bileier, bil.adresse, bil.epost, bil.nummer, bompass.passtid, bompass.bomring
FROM bil
JOIN bompass
ON bil.regnr = bompass.regnr
WHERE bompass.skiltlest = true OR
bompass.skiltlest = false;

--f)
SELECT bil.regnr, bil.bileier, bil.epost, bompass.passtid, bompass.bomring
FROM bil
JOIN bompass
ON bil.regnr = bompass.regnr
WHERE bompass.skiltlest = true;

--g)
--σ(skiltlest = true)(bil ⨝ bompass)
--proj(bil.regnr, bil.bileier, bil.epost, bompass.passtid, bompass.bomring)

--h)
--skrive ut antall passeringer i bomringen
SELECT bil.regnr, COUNT(bompass.regnr) AS antallpass
FROM bil
JOIN bompass
ON bil.regnr = bompass.regnr
GROUP BY bil.regnr;
	 
--i)
--Skrive ut informasjon om passeringer en spesifikk bil har hatt
SELECT bil
FROM bil
JOIN bompass
ON bil.regnr = bompass.regnr
WHERE bil.regnr = 'AA10000'
ORDER BY bompass.passtid DESC

	 
--j)
--skriver ut antall passeringer der skiltnummer ikke ble registrert
SELECT COUNT(bompass.regnr) AS antallSkiltLest
FROM bompass
WHERE SKILTLEST = false;

--k)
--man kan legge inn kreterier for regnr som oppfyller krav til registreringsnummer 
--for norske biler, derretter kan man ha en egen tabell for registreringsnummer
--som ikke oppfyller disse vilkårene og da oppbevarer data om disse.
--Det kan imidlertidig være utfordrende å skille mellom disse igjen, 
--i og med ulike land kan ha ulike format av regnr og det kan være 
--vanskelig å holde oversikt over alle disse. 

--l)
--En evnt. Løsning til oppgave d) var å opprette en egen tabell til skilt som ikke ble registrert lest. 
--Andre tabeller som kunne vært relevant å opprette, kunne vært en egen tabell for bomringer med info. 
--Om beliggenhet, ulike satser på avgifter etc. 
--Det ville også vært relevant å inkludere en tabell med oversikt 
--over bompengekunder, med oversikt over fakturaer, betalinger og evnt. 
--Bompengeavtaler.
--Som nevnt i oppgave k) så kan det også være relevant å opprette 
--en egen tabell for informasjon om regnr som ikke oppfyller vilkår for
--norske regnr.



