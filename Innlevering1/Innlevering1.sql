CREATE TABLE BIL (
  REGNR VARCHAR(100) PRIMARY KEY NOT NULL UNIQUE, -- registration number of the car
  BILEIER VARCHAR(100) NOT NULL, -- name of the owner
  ADRESSE VARCHAR(100) NOT NULL, -- owner's address
  EPOST VARCHAR(100) NOT NULL, -- owner's email
  NUMMER VARCHAR(100) NOT NULL -- owner's phone number
);

-- creates a table for information about toll booths and toll booth crossings
CREATE TABLE BOMPASS (
  REGNR VARCHAR(100) NOT NULL, -- foreign key to the owner who belongs to the car that passed the toll
  DATO DATE NOT NULL DEFAULT CURRENT_DATE, -- time of toll passage
  TID TIME NOT NULL DEFAULT CURRENT_TIMESTAMP, -- time of toll passage
  BOMRING INT NOT NULL, -- name/number of toll booth
  CONSTRAINT BOMPASS_PK PRIMARY KEY (REGNR,BOMRING),
  FOREIGN KEY(REGNR) REFERENCES BIL(REGNR)
);

CREATE TABLE ULEST_REGNR (
  REGNR INT PRIMARY KEY NOT NULL UNIQUE, -- unique identifier for each unreadable plate
  DATO DATE NOT NULL DEFAULT CURRENT_TIMESTAMP, -- DATO of the passage
  TID TIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  BOMRING VARCHAR(255) NOT NULL, -- location of the toll booth
  NOTAT VARCHAR(255) -- info om norsk eller utlandsk bil
);

-- inserts test data into the car table
INSERT INTO BIL (REGNR, BILEIER, ADRESSE, EPOST, NUMMER) 
VALUES 
  ('AA10000', 'Ola Nordmann', 'Strandveien 55, 0257 OSLO', 'ola.nordmann@email.com', '12345678'),
  ('BB20000', 'Kari Pettersen', 'BØLERVEIEN 4, 0791 OSLO', 'kari.pettersen@email.com', '23456789'),
  ('CC30000', 'Erik Hansen', 'KONGENS GATE 13, 5004 BERGEN', 'erik.hansen@email.com', '34567890');

-- inserts test data into the toll booth crossings table
INSERT INTO BOMPASS (REGNR, DATO, TID, BOMRING) 
VALUES
  ('AA10000','2023-01-01','10:00:00',100),
  ('BB20000','2023-01-02','10:00:00',150),
  ('CC30000','2023-01-03','10:00:00',200);

-- selects and prints out the information from the BIL and BOMPASS tables
SELECT BOMPASS.REGNR, BIL.BILEIER, BIL.ADRESSE, BIL.EPOST, BIL.NUMMER, 
  BOMPASS.DATO, BOMPASS.TID, BOMPASS.BOMRING, BOMPASS
