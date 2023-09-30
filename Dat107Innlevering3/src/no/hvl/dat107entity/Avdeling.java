package no.hvl.dat107entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "finnAvdelingMedId", query = "SELECT a FROM Avdeling a WHERE a.avdeling_Id = :avdeling_Id)"),
	})

public class Avdeling {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "avdeling_id", updatable = false, nullable = false)
	private Integer avdeling_Id;
	
	private String navn;
	
	@ManyToOne
	@JoinColumn(name = "sjef_Id")
	private Ansatt sjef;

	@OneToMany(mappedBy = "avdeling", cascade = CascadeType.PERSIST)
	private static List<Ansatt> ansatte;

	public static List<Ansatt> getAnsatte() {
        return ansatte;
    }

    public void setAnsatte(List<Ansatt> ansatte) {
        this.ansatte = ansatte;
    }

    public void leggTilAnsattListe(Ansatt ansatt) {
        ansatte.add(ansatt);
        ansatt.setAvdeling(this);
    }

    public void fjernAnsattListe(Ansatt ansatt) {
        if (ansatte.size() > 1 && !ansatt.ErSjef()) {
            ansatte.remove(ansatt);
            ansatt.setAvdeling(null);
        }
    }
	

	public Avdeling(Integer avdeling_Id, String navn, Ansatt sjef) {
		this.avdeling_Id = avdeling_Id;
		this.sjef = sjef;
		this.navn = navn;
	}

	@Override
	public String toString() {
		return "avdeling [avdeling_Id=" + avdeling_Id + ", navn=" + navn + ", sjef=" + sjef + "]" + "\n";
	}

	

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public Ansatt getSjef() {
		return sjef;
	}

	public void setSjef(Ansatt sjef) {
		this.sjef = sjef;
	}

}
