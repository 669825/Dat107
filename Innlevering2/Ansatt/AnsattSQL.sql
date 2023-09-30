CREATE TABLE Ansatt (
    ansattID SERIAL PRIMARY KEY,
    navn VARCHAR(50) NOT NULL,
    fødselsdato DATE NOT NULL,
    kontor VARCHAR(50),
    telefon VARCHAR(50) NOT NULL,
    epost VARCHAR(50),
    ansattdato DATE,
    lønn DECIMAL(10,2)
);

CREATE TABLE Prosjekt (
    prosjektID SERIAL PRIMARY KEY,
    navn VARCHAR(50)
);

CREATE TABLE ProsjektAnsatt (
    id SERIAL PRIMARY KEY,
    prosjektID INT REFERENCES Prosjekt(prosjektID),
    ansattID INT REFERENCES Ansatt(ansattID),
    UNIQUE (prosjektID, ansattID)
);
