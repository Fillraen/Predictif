package fr.cypher.dasi.metier.modele.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class Adresse {
    protected String numeroDeVoie;
    protected String nomDeVoie;
    protected String codePostal;
    protected String ville;
    protected String codeDepartement;

    public Adresse(String numeroDeVoie, String rue, String codePostal, String ville, String codeDepartement) {
        this.numeroDeVoie = numeroDeVoie;
        this.nomDeVoie = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.codeDepartement = codeDepartement;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "numeroDeVoie='" + numeroDeVoie + '\'' +
                ", rue='" + nomDeVoie + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", codeDepartement='" + codeDepartement + '\'' +
                '}';
    }

    public Adresse() {
    }


    public String getNumeroDeVoie() {
        return numeroDeVoie;
    }

    public String getNomDeVoie() {
        return nomDeVoie;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public String getVille() {
        return ville;
    }

    public void setNumeroDeVoie(String numeroDeVoie) {
        this.numeroDeVoie = numeroDeVoie;
    }

    public void setNomDeVoie(String rue) {
        this.nomDeVoie = rue;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setCodeDepartement(String codeDepartement) {
        this.codeDepartement = codeDepartement;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
}
