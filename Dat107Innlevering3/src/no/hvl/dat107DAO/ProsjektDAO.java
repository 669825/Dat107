package no.hvl.dat107DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import no.hvl.dat107entity.Prosjekt;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ProsjektDAO {
	
	private EntityManagerFactory emf;
	
	
	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("Innlevering3");
	}
	
	
	
	public Prosjekt finnProsjektMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Prosjekt p = null;

		try {
			p = em.find(Prosjekt.class, id);
		} finally {
			em.close();
		}
		return p;

	}

	








}



