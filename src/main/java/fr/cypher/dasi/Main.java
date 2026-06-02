package fr.cypher.dasi;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.scenario.MainScenario;

public class Main {
    static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();

        if (!MainInit.executer()) {
            System.err.println("[Main] Initialisation échouée, arrêt.");
            JpaUtil.fermerFabriquePersistance();
            return;
        }

        MainScenario.executer();

        JpaUtil.fermerFabriquePersistance();
    }
}
