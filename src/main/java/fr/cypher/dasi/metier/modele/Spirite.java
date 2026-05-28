package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.Entity;

@Entity
public class Spirite extends Medium {

    private String support;

    public Spirite() {}

    public Spirite(String support) {
        this.support = support;
    }

    public Spirite(String denomination, Genre genre, String presentation, String support) {
        super(denomination, genre, presentation);
        this.support = support;
    }

    @Override
    public String toString() {
        return super.toString() + " : " + "Astrologue{" +
                "support='" + support +
                '}';
    }
}
