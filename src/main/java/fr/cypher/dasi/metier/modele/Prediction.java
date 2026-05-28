package fr.cypher.dasi.metier.modele;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Prediction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String amour;
    private String sante;
    private String travail;

    public Prediction(String amour, String sante, String travail) {
        this.amour = amour;
        this.sante = sante;
        this.travail = travail;
    }

    public Prediction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Prediction{" +
                "id=" + id +
                ", amour='" + amour + '\'' +
                ", sante='" + sante + '\'' +
                ", travail='" + travail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prediction other = (Prediction) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
