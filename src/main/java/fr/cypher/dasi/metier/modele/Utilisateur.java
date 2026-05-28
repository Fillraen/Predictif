package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.enums.Genre;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Utilisateur implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String mail;

    private String prenom;
    private String motDePasse;
    private String telephone;

    // This is kinda risky, if we rename any enum value, this will "corrupt" the database
    // Indeed, data will be based on enum value names.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    public Utilisateur(String mail, String prenom, String motDePasse, String telephone, Genre genre) {
        this.mail = mail;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.genre = genre;
    }

    public Utilisateur() {
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "telephone='" + telephone + '\'' +
                ", id=" + id +
                ", mail='" + mail + '\'' +
                ", prenom='" + prenom + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
