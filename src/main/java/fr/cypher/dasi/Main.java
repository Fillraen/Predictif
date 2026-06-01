package fr.cypher.dasi;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.scenario.client.Inscription;
import fr.cypher.dasi.scenario.client.ParcoursClient;
import fr.cypher.dasi.scenario.employe.ParcoursEmploye;
import fr.cypher.dasi.scenario.utilisateur.Connexion;
import fr.cypher.dasi.test.MainTest;

public class Main {
    static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();

        if (!MainInit.executer()) {
            System.err.println("[Main] Initialisation échouée, arrêt.");
            JpaUtil.fermerFabriquePersistance();
            return;
        }

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

        ParcoursEmploye.executer();

        System.out.println();
        System.out.println("========================================");
        System.out.println();

        ParcoursClient.executer();

        System.out.println();
        System.out.println("========================================");
        System.out.println();

        MainTest.executer();

        JpaUtil.fermerFabriquePersistance();
    }
}
