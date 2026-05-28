package fr.cypher.dasi;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.scenario.client.Connexion;
import fr.cypher.dasi.scenario.client.Inscription;
import fr.cypher.dasi.test.MainTest;

public class Main {
    static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        MainTest.executer();

        Inscription.executer();

        System.out.println();
        System.out.println("========================================");
        System.out.println();

        Connexion.executer();

        JpaUtil.fermerFabriquePersistance();
    }
}
