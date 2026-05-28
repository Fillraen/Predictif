package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.Entity;

@Entity
public class Astrologue extends Medium {

    private String formation;

    private int promotion;

    public Astrologue() {}

    public Astrologue(String formation, int promotion) {
        this.formation = formation;
        this.promotion = promotion;
    }

    public Astrologue(String denomination, Genre genre, String presentation, String formation, int promotion) {
        super(denomination, genre, presentation);
        this.formation = formation;
        this.promotion = promotion;
    }

    @Override
    public String toString() {
        return super.toString() + " : " + "Astrologue{" +
                "formation='" + formation + '\'' +
                ", promotion=" + promotion +
                '}';
    }
}
