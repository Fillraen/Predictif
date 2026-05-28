package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.embedded.ProfilAstral;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Client extends Utilisateur implements Serializable {

    private LocalDate dateDeNaissance;

    @Embedded
    private Adresse adresse;

    @Embedded
    private ProfilAstral profilAstral;

    public Client(String mail, String prenom, String motDePasse, String telephone, String nom, LocalDate dateDeNaissance, Adresse adresse) {
        super(mail, prenom, motDePasse, telephone, nom);
        this.dateDeNaissance = dateDeNaissance;
        this.adresse = adresse;
    }

    public Client(String mail, String prenom, String motDePasse, String telephone, String nom, LocalDate dateDeNaissance, Adresse adresse, ProfilAstral profilAstral) {
        this(mail, prenom, motDePasse, telephone, nom, dateDeNaissance, adresse);
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

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", mail='" + getMail() + '\'' +
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
