package fr.cypher.dasi;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.scenario.client.Inscription;

public class Main {
    public static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();
        Inscription.executer();
        JpaUtil.fermerFabriquePersistance();
    }
}
