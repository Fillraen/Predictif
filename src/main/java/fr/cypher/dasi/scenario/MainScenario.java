package fr.cypher.dasi.scenario;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.scenario.utilisateur.Connexion;

public class MainScenario {

    public static void executer() {
        System.out.println();
        System.out.println("========================================");
        System.out.println();

        Connexion.executer();

        System.out.println();
        System.out.println("========================================");
        System.out.println();

        ParcoursBoutEnBout.executer();

        System.out.println();
        System.out.println("========================================");
    }

     static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();

        executer();

        JpaUtil.fermerFabriquePersistance();
    }
}
