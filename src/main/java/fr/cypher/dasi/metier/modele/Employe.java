package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.Entity;

@Entity
public class Employe extends Utilisateur {

    private boolean estDisponible;

    public Employe() {}

    public Employe(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public Employe(String mail, String prenom, String motDePasse, String telephone, Genre genre, boolean estDisponible) {
        super(mail, prenom, motDePasse, telephone, genre);
        this.estDisponible = estDisponible;
    }

    @Override
    public String toString() {
        return super.toString() + " : " + "Astrologue{" +
                "estDisponible='" + estDisponible +
                '}';
    }
}
