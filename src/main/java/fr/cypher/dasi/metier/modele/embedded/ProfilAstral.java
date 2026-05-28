package fr.cypher.dasi.metier.modele.embedded;

import javax.persistence.Embeddable;

@Embeddable
public class ProfilAstral {
    protected String animalTotem;
    protected String signeZodiac;
    protected String couleurBonheur;
    protected String signeChinois;

    public ProfilAstral(String animalTotem, String signeZodiac, String couleurBonheur, String signeChinois) {
        this.animalTotem = animalTotem;
        this.signeZodiac = signeZodiac;
        this.couleurBonheur = couleurBonheur;
        this.signeChinois = signeChinois;
    }

    public ProfilAstral() {}


    @Override
    public String toString() {
        return "ProfilAstral{" +
                "animalTotem='" + animalTotem + '\'' +
                ", signeZodiac='" + signeZodiac + '\'' +
                ", couleurBonheur='" + couleurBonheur + '\'' +
                ", signeChinois='" + signeChinois + '\'' +
                '}';
    }

    public String getAnimalTotem() {
        return animalTotem;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }

    public String getSigneZodiac() {
        return signeZodiac;
    }

    public void setSigneZodiac(String signeZodiac) {
        this.signeZodiac = signeZodiac;
    }

    public String getCouleurBonheur() {
        return couleurBonheur;
    }

    public void setCouleurBonheur(String couleurBonheur) {
        this.couleurBonheur = couleurBonheur;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }
}
