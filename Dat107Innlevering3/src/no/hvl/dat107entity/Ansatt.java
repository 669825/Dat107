package no.hvl.dat107entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Persistence;
import jakarta.persistence.Table;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(schema = "Innlevering3")
public class Ansatt {

    @Id //Primary key annotiation. Called @Id no matter what the variable is called
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ansatt_Id;
    private String brukernavn;
    private String fornavn;
    private String etternavn;
    private LocalDate datoAnsatt;
    private String stilling;
    private Integer monedslonn;
    private Integer avdeling_Id;
    
    //mange-til-mange forhold mellom ansatt og prosjekt
    @ManyToMany(mappedBy="ansatte")
    private List<Prosjekt> prosjekter;
    
    //mange-til-en forhold mellom ansatt og avdeling
    @ManyToOne
    @JoinColumn(name="avdeling_Id")
    private Avdeling avdeling;
    
    private boolean erSjef;
    
    
    public int getAnsatt_Id() {
    	return ansatt_Id;
    }
    
    public void setAnsatt_Id(Integer ansatt_Id) {
        this.ansatt_Id = ansatt_Id;
    }
    

    public void AnsattskrivUt() {
		System.out.printf("%sAnsatt nr %d: %s", "\n  ", ansatt_Id, fornavn, etternavn);
	}
    
	public void skrivUtMedProsjekter() {
		System.out.println();
		AnsattskrivUt();
		prosjekter.forEach(p -> p.ProsjektskrivUt());
	}
	
	public void leggTilProsjekt(Prosjekt p) {
		prosjekter.add(p);
		
	}

	public void fjernProsjekt(Prosjekt p) {
		prosjekter.remove(p);
	
	}

    public Avdeling getAvdeling() {
    	return avdeling;
    }
   
    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }

    public boolean ErSjef() {
        return erSjef;
    }

    public void setErSjef(boolean erSjef) {
        this.erSjef = erSjef;
    }
    
    
    

    
    
    public Ansatt() {
    }

    public Ansatt(Integer ansatt_Id, String brukernavn, String fornavn, String etternavn, LocalDate datoAnsatt,
                  String stilling, int monedslonn, Integer avdeling_Id) {

        this.ansatt_Id = ansatt_Id;
        this.brukernavn = brukernavn;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.datoAnsatt = datoAnsatt;
        this.stilling = stilling;
        this.monedslonn = monedslonn;
        this.avdeling_Id = avdeling_Id;

    }
    
    public static void LeggTilAnsatt(Ansatt ansatt) {

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Innlevering3");
    	
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = (EntityTransaction) em.getTransaction();

		Ansatt a = ansatt;


		try {
			tx.begin();
			em.persist(a);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}

	}
    
    public Ansatt LeggTilAnsatt(Integer ansatt_Id, String brukernavn, String fornavn, String etternavn,
			LocalDate ansattdato, String stilling, Integer monedslonn, Integer avdeling_Id) {

    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Innlevering3");
    	
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = (EntityTransaction) em.getTransaction();

		Ansatt a = new Ansatt(ansatt_Id, brukernavn, fornavn, etternavn, ansattdato, stilling, monedslonn, avdeling_Id);


		try {
			tx.begin();
			em.persist(a);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return a;

	}

 
    public int getAvdeling_Id() {
        return avdeling_Id;
    }

    public void setAvdeling_Id(Integer avdeling_Id) {
        this.avdeling_Id = avdeling_Id;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getDatoAnsatt() {
        return datoAnsatt;
    }

    public void setDatoAnsatt(LocalDate datoAnsatt) {
        this.datoAnsatt = datoAnsatt;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public Integer getmonedsLonn() {
        return monedslonn;
    }
    
   public void setMonedsLonn(int monedslonn) {
	   this.monedslonn = monedslonn;
   }






	}

	
		
	



	

	
		
	

		
	

