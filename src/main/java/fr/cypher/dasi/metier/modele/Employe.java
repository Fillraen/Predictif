package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employe extends Utilisateur {

    private boolean estDisponible;

    @OneToMany(mappedBy = "employe")
    private final List<Consultation> consultations = new ArrayList<>();

    public Employe() {}

    public Employe(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public Employe(String mail, String prenom, String nom, String motDePasse, String telephone, Genre genre, boolean estDisponible) {
        super(mail, prenom, nom, motDePasse, telephone, genre);
        this.estDisponible = estDisponible;
    }

    public Employe(String mail, String prenom, String nom, String motDePasse, String telephone, Genre genre) {
        this(mail, prenom, nom, motDePasse, telephone, genre, true);
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public List<Consultation> getConsultations() {
        return this.consultations;
    }

    public void addConsultation(Consultation consultation) {
        this.consultations.add(consultation);
    }

    @Override
    public String toString() {
        return "Employe{" + super.toString() +
                ", estDisponible='" + estDisponible +
                '}';
    }
}
