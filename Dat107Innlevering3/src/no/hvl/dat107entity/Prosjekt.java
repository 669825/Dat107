package no.hvl.dat107entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "Innlevering3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prosjekt_Id;

	private String navn;
	private String beskrivelse;
	private String deltagelse;

	// prosjekt som owning-side
	@ManyToMany
	@JoinTable(name = "Innlevering3.prosjektdeltagelse", joinColumns = @JoinColumn(name = "prosjekt_Id"), inverseJoinColumns = @JoinColumn(name = "ansatt_Id"))
	private List<Ansatt> ansatte;
	
	

	public Integer getProsjekt_Id() {
		return prosjekt_Id;
	}

	
	public void ProsjektskrivUt() {
		System.out.printf("%sProsjekt nr %d: %s", "\n  ", prosjekt_Id, navn);
	}
	
	public void skrivUtMedAnsatte() {
		System.out.println();
		ProsjektskrivUt();
		ansatte.forEach(a -> a.AnsattskrivUt());
	}
	
	public void leggTilAnsatt(Ansatt a) {
		ansatte.add(a);

	}

	public void fjernAnsatt(Ansatt a) {
		ansatte.remove(a);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void setProsjekt_Id(Integer prosjekt_Id) {
		this.prosjekt_Id = prosjekt_Id;
	}

	
	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public String getDeltagelse() {
		return deltagelse;
	}

	public void setDeltagelse(String deltagelse) {
		this.deltagelse = deltagelse;
	}
	


	
	


	


	

	}

	


