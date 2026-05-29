package fr.cypher.dasi.metier.modele;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Prediction implements Serializable {
    private String amour;
    private String sante;
    private String travail;

    public Prediction(String amour, String sante, String travail) {
        this.amour = amour;
        this.sante = sante;
        this.travail = travail;
    }

    public String getAmour() {
        return amour;
    }

    public void setAmour(String amour) {
        this.amour = amour;
    }

    public String getSante() {
        return sante;
    }

    public void setSante(String sante) {
        this.sante = sante;
    }

    public String getTravail() {
        return travail;
    }

    public void setTravail(String travail) {
        this.travail = travail;
    }

    @Override
    public String toString() {
        return "=== Prediction : ===\n" +
                "\tAmour : " + amour + "\n" +
                "\tSante : " + sante + "\n" +
                "\tTravail : " + travail + "\n" +
                "===   - ~ ~ -   ===";
    }
}
