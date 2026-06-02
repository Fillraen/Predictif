package fr.cypher.dasi.scenario;

import fr.cypher.dasi.MainInit;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.scenario.client.Inscription;
import fr.cypher.dasi.scenario.utilisateur.Connexion;

public class MainScenario {

    public static void executer() {
        System.out.println();
        System.out.println("========================================");
        System.out.println();

        Inscription.executer();

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

    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();

        if (!MainInit.executer()) {
            System.err.println("[MainScenario] Initialisation échouée, arrêt.");
            JpaUtil.fermerFabriquePersistance();
            return;
        }

        executer();

        JpaUtil.fermerFabriquePersistance();
    }
}
