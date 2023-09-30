
package no.hvl.dat107DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import no.hvl.dat107entity.Ansatt;
import no.hvl.dat107entity.Prosjekt;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AnsattDAO {

	private EntityManagerFactory emf;

	public AnsattDAO() {
		emf = Persistence.createEntityManagerFactory("Innlevering3");

	}

	public Ansatt finnAnsattMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Ansatt a = null;

		try {
			a = em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
		return a;

	}

	public void registrerProsjektdeltagelse(Integer ansatt_Id, Integer prosjekt_Id) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = (EntityTransaction) em.getTransaction();

		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansatt_Id);
			Prosjekt p = em.find(Prosjekt.class, prosjekt_Id);

			a.leggTilProsjekt(p);
			p.leggTilAnsatt(a);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}

		} finally {
			em.close();
		}

	}

	public void slettProsjektDeltagelse(Integer ansatt_Id, Integer prosjekt_Id) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			Ansatt a = em.find(Ansatt.class, ansatt_Id);
			Prosjekt p = em.find(Prosjekt.class, prosjekt_Id);

			a.fjernProsjekt(p);
			p.fjernAnsatt(a);

			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}

	
	public Ansatt finnAnsattMedBrukernavn(String brukernavn) {

		EntityManager em = emf.createEntityManager();

		// kaller teksten for queryString for å forenkle for bruk etterpå
		String queryString = "select a from Ansatt a Where a.brukernavn= :brukernavn";

		try {

			em.getTransaction().begin();
			TypedQuery<Ansatt> query = (TypedQuery<Ansatt>) em.createQuery(queryString, Ansatt.class);

			query.setParameter("brukernavn", brukernavn);

			em.getTransaction().commit();

			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	

	public Ansatt nyLønnOgStilling(int ansatt_Id, int monedslonn, String stilling) {

		EntityManager em = emf.createEntityManager();

		Ansatt a = finnAnsattMedId(ansatt_Id);

		if (a != null) {
			if (monedslonn != 0) {
				a.setMonedsLonn(monedslonn);
			}
			if (stilling != null) {
				a.setStilling(stilling);
			}
		} else {
			System.out.println("Fant ingen ansatt med id " + ansatt_Id);
		}

		try {
			em.getTransaction().begin();

			em.merge(a);

			em.getTransaction().commit();

			return a;

		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public List<Ansatt> alleAnsatte() {

		EntityManager em = emf.createEntityManager();

		String queryString = "select a from Ansatt a";

		try {
			em.getTransaction().begin();

			TypedQuery<Ansatt> query = (TypedQuery<Ansatt>) em.createQuery(queryString, Ansatt.class);
			em.getTransaction().commit();

			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	 public void LeggTilAnsatt(Ansatt ansatt) {

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
	    
	public void slettAnsatt(int id) {

		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Ansatt a = em.find(Ansatt.class, id);
			if (a != null) {
				em.remove(a);
				em.getTransaction().commit();
				System.out.println("Ansatt er blitt slettet fra databasen");
			} else {
				System.out.println("Fant ingen ansatt med id " + id);
			}

		} finally {
			em.close();
			emf.close();
		}
	}

	
	

}
