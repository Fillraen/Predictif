package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.embedded.ProfilAstral;
import fr.cypher.dasi.metier.modele.enums.Genre;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends Utilisateur implements Serializable {

    private LocalDate dateDeNaissance;

    @Embedded
    private Adresse adresse;

    @Embedded
    private ProfilAstral profilAstral;

    @OneToMany(mappedBy = "client")
    private final List<Consultation> consultations = new ArrayList<>();

    public Client(String mail, String prenom, String motDePasse, String telephone, String nom, LocalDate dateDeNaissance, Adresse adresse, Genre genre) {
        super(mail, prenom, nom, motDePasse, telephone, genre);
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
    }

    public Client(String mail, String prenom, String nom, String motDePasse, String telephone, Genre genre, LocalDate dateDeNaissance, Adresse adresse, ProfilAstral profilAstral) {
        this(mail, prenom, nom, motDePasse, telephone, dateDeNaissance, adresse, genre);
        this.profilAstral = profilAstral;
    }
    
    public Client() {
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void addConsultation(Consultation consultation) {
        this.consultations.add(consultation);
    }

    @Override
    public String toString() {
        return "Client{" + super.toString() +
                ", dateDeNaissance=" + dateDeNaissance +
                ", adresse=" + adresse +
                ", profilAstral=" + profilAstral +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client other = (Client) o;
        return Objects.equals(getId(), other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
