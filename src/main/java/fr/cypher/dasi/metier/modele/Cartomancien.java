package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.Entity;

@Entity
public class Cartomancien extends Medium {

    public Cartomancien() {}

    public Cartomancien(String denomination, Genre genre, String presentation) {
        super(denomination, genre, presentation);
    }
}
