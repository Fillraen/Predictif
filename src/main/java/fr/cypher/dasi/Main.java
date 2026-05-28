package fr.cypher.dasi;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.scenario.client.Inscription;
import fr.cypher.dasi.test.MainTest;

public class Main {
    static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        MainTest.executer();
        Inscription.executer();
        JpaUtil.fermerFabriquePersistance();
    }
}
