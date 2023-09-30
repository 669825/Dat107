package no.hvl.dat107Main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat107DAO.AnsattDAO;
import no.hvl.dat107DAO.AvdelingDAO;
import no.hvl.dat107DAO.ProsjektDAO;
import no.hvl.dat107entity.Ansatt;
import no.hvl.dat107entity.Avdeling;
import no.hvl.dat107entity.Prosjekt;

public class Main {
	
	private static AnsattDAO ansattDAO;
	private static AvdelingDAO avdelingDAO;
	
	
	public static void klient() {
		
		ansattDAO = new AnsattDAO();
		avdelingDAO = new AvdelingDAO();
		
		Scanner scanner = new Scanner(System.in);
		boolean fortsett = true;

		while (fortsett) {
			System.out.println("Velg en handling:");
			System.out.println("1: Finn ansatt med ID");
			System.out.println("2: Finn ansatt med brukernavn");
			System.out.println("3: List ut alle ansatte");
			System.out.println("4: Oppdater ansatt");
			System.out.println("5: Legg til ny ansatt");
			System.out.println("0: Avslutt");

			int valg = scanner.nextInt();

			switch (valg) {
			case 1:
				System.out.print("Skriv inn ansatt_ID: ");
				int ansatt_Id = scanner.nextInt();
				Ansatt ansatt = ansattDAO.finnAnsattMedId(ansatt_Id);
				if (ansatt == null) {
					System.out.println("Fant ingen ansatt med ID " + ansatt_Id);
				} else {
					System.out.println(
							ansatt.getFornavn() + " " + ansatt.getEtternavn() + " (" + ansatt.getBrukernavn() + ")");
				}
				break;
			case 2:
				System.out.print("Skriv inn brukernavn: ");
				String brukernavn = scanner.next();
				Ansatt ansatt2 = ansattDAO.finnAnsattMedBrukernavn(brukernavn);
				if (brukernavn == null) {
					System.out.println("Fant ingen ansatt med brukernavn " + brukernavn);
				} else {
					System.out.println(
							ansatt2.getFornavn() + " " + ansatt2.getEtternavn() + " (" + ansatt2.getBrukernavn() + ")");
				}
				break;
			case 3:
				List<Ansatt> ansatte = Avdeling.getAnsatte();

				for (Ansatt a : ansatte) {
					if (a != null) {
						System.out.println(a.getFornavn() + " " + a.getEtternavn() + " (" + a.getBrukernavn() + ")");
					} else {
						System.out.println("Ingen ansatte registrert");
					}
				}
				break;
			case 4:
				// implementasjon for å oppdatere ansatt
				System.out.print("Oppgi ansatt-Id for ansatt som skal oppdateres: ");
				int ansatt_Id2 = scanner.nextInt();
				Ansatt a = ansattDAO.finnAnsattMedId(ansatt_Id2);
				if (a == null) {
					System.out.println("Fant ingen ansatt med ID " + ansatt_Id2);
				} else {
					boolean fortsett2 = true;
					while (fortsett2) {
						System.out.println("Velg en handling:");
						System.out.println("1: Oppdater AnsattId");
						System.out.println("2: Oppdater brukernavn");
						System.out.println("3: Oppdater fornavn");
						System.out.println("4: Oppdater etternavn");
						System.out.println("5: Oppdater stilling");
						System.out.println("6: Oppdater lønn ");
						System.out.println("7: Oppdater AvdelingId");
						System.out.println("0: Avslutt");
						int valg2 = scanner.nextInt();
						switch (valg2) {
						case 1:
							int nyAnsattId = scanner.nextInt();
							a.setAnsatt_Id(nyAnsattId);
							break;
						case 2:
							String nyBrukernavn = scanner.next();
							a.setBrukernavn(nyBrukernavn);
							break;
						case 3:
							String nyFornavn = scanner.next();
							a.setFornavn(nyFornavn);
							break;
						case 4:
							String nyEtternavn = scanner.next();
							a.setEtternavn(nyEtternavn);
							break;
						case 5:
							String nyStilling = scanner.next();
							a.setStilling(nyStilling);
							break;
						case 6:
							int nyLonn = scanner.nextInt();
							a.setMonedsLonn(nyLonn);
							break;
						case 7:
							int nyAvdelingId = scanner.nextInt();
							a.setAvdeling_Id(nyAvdelingId);
							break;
						case 0:
							fortsett2 = false;
							break;
						default:
							System.out.println("Ugyldig valg. Prøv igjen.");
							break;
						}
					}

				}
				break;
			case 5://opprette ansatt
				Ansatt b = new Ansatt();

					System.out.println("1: Oppgi AnsattId");
					int nyAnsattId = scanner.nextInt();
					b.setAnsatt_Id(nyAnsattId);
					System.out.println("2: Oppdater brukernavn");
					String nyBrukernavn = scanner.next();
					b.setBrukernavn(nyBrukernavn);
					System.out.println("3: Oppdater fornavn");
					String nyFornavn = scanner.next();
					b.setFornavn(nyFornavn);
					System.out.println("4: Oppdater etternavn");
					String nyEtternavn = scanner.next();
					b.setEtternavn(nyEtternavn);
					System.out.println("5: Oppdater stilling");
					String nyStilling = scanner.next();
					b.setStilling(nyStilling);
					System.out.println("6: Oppdater lønn ");
					int nyLonn = scanner.nextInt();
					b.setMonedsLonn(nyLonn);
					System.out.println("7: Oppdater AvdelingId");
					int nyAvdelingId = scanner.nextInt();
					b.setAvdeling_Id(nyAvdelingId);
					
				  ansattDAO.LeggTilAnsatt(b);
			case 0:
				fortsett = false;
				break;
			default:
				System.out.println("Ugyldig valg. Prøv igjen.");
				break;
			}

			System.out.println();
		}

		scanner.close();
	}

	public static void main(String[] args) {

		// oppretter 3 ansatte

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Innlevering3");
        EntityManager em = emf.createEntityManager();

        AnsattDAO ansattDAO = new AnsattDAO();

        try {
            Ansatt ansatt = ansattDAO.finnAnsattMedId(1);
            System.out.println(ansatt.getFornavn() + " " + ansatt.getEtternavn() + " er ansatt som " + ansatt.getStilling() + " med en månedslønn på " + ansatt.getmonedsLonn() + " kroner.");
        } finally {
            em.close();
            emf.close();
        }

	}

	/*
	private static final String[] FORNAVN = { "Ole", "Maren", "Kari", "Per", "Ingrid", "Lars", "Ida", "Nina", "Petter",
			"Hilde" };
	private static final String[] ETTERNAVN = { "Hansen", "Johansen", "Olsen", "Pedersen", "Andersen", "Sørensen",
			"Larsen", "Berg", "Eriksen", "Nygaard" };
	private static final String[] STILLINGER = { "Leder", "Sekretær", "Selger", "Utvikler", "Designer", "Markedsfører",
			"Prosjektleder", "Konsulent" };
	private static final int[] LONN = { 400000, 450000, 500000, 550000, 600000, 650000, 700000 };

	private static final Random RANDOM = new Random();

	public static List<Ansatt> genererAnsatte(int antall) {
		List<Ansatt> ansatte = new ArrayList<>();
		for (int i = 0; i < antall; i++) {
			ansatte.add(genererAnsatt());
		}
		return ansatte;
	}

	private static Ansatt genererAnsatt() {
		Ansatt ansatt = new Ansatt();
		ansatt.setBrukernavn(genererBrukernavn());
		ansatt.setFornavn(FORNAVN[RANDOM.nextInt(FORNAVN.length)]);
		ansatt.setEtternavn(ETTERNAVN[RANDOM.nextInt(ETTERNAVN.length)]);
		ansatt.setDatoAnsatt(LocalDate.now().minusMonths(RANDOM.nextInt(24)));
		ansatt.setStilling(STILLINGER[RANDOM.nextInt(STILLINGER.length)]);
		ansatt.setMonedslonn(LONN[RANDOM.nextInt(LONN.length)]);
		return ansatt;
	}

	private static String genererBrukernavn() {
		StringBuilder sb = new StringBuilder();
		sb.append(FORNAVN[RANDOM.nextInt(FORNAVN.length)].toLowerCase());
		sb.append(".");
		sb.append(ETTERNAVN[RANDOM.nextInt(ETTERNAVN.length)].toLowerCase());
		sb.append(RANDOM.nextInt(100));
		return sb.toString();
	}
	*/
	
	
	
	
	
}
