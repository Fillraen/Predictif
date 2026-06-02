package fr.cypher.dasi.scenario;

import fr.cypher.dasi.scenario.client.ParcoursClient;
import fr.cypher.dasi.scenario.employe.ParcoursEmploye;

public class ParcoursBoutEnBout {

    public static void executer() {
        System.out.println("[DEBUT SCENARIO] ParcoursBoutEnBout");
        System.out.println();

        // Phase 1 : le client s'inscrit, choisit un médium et demande une consultation
        // → une employée disponible (Camille) est automatiquement assignée
        ParcoursClient.executer();

        System.out.println();
        System.out.println("========================================");
        System.out.println();

        // Phase 2 : Camille se connecte, voit la consultation affectée et la traite
        ParcoursEmploye.executer();

        System.out.println();
        System.out.println("[FIN SCENARIO] ParcoursBoutEnBout");
    }
}
