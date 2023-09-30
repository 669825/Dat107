package no.hvl.dat107DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import no.hvl.dat107entity.Ansatt;
import no.hvl.dat107entity.Avdeling;
import no.hvl.dat107entity.Prosjekt;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class AvdelingDAO {
	
	private EntityManagerFactory emf;

	public AvdelingDAO() {
		emf = Persistence.createEntityManagerFactory("Innlevering3");

	}

	public Avdeling finnAvdelingMedId(Integer avdeling_Id) {

		EntityManager em = emf.createEntityManager();

		Avdeling avdeling = null;

		try {
			avdeling = em.find(Avdeling.class, avdeling_Id);
		} finally {
			em.close();
		}
		return avdeling;

	}


	public List<Avdeling> alleAvdelinger() {

		EntityManager em = emf.createEntityManager();

		String queryString = "select a from Avdeling a";

		try {
			em.getTransaction().begin();

			TypedQuery<Avdeling> query = (TypedQuery<Avdeling>) em.createQuery(queryString, Avdeling.class);
			em.getTransaction().commit();

			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	

}



