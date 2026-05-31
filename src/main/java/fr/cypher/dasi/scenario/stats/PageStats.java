package fr.cypher.dasi.scenario.stats;

import fr.cypher.dasi.metier.modele.stat.StatDepartement;
import fr.cypher.dasi.metier.modele.stat.StatEmploye;
import fr.cypher.dasi.metier.modele.stat.StatMedium;
import fr.cypher.dasi.metier.service.StatsService;

import java.util.List;

public class PageStats {

    private static final StatsService statsService = new StatsService();

    public static void executer() {
        System.out.println("[DEBUT SCENARIO] PageStats");
        consultationsParMedium();
        System.out.println();
        repartitionClientsParEmploye();
        System.out.println();
        mediumsPopulaires();
        System.out.println();
        repartitionGeographique();
        System.out.println("[FIN SCENARIO] PageStats");
    }

    private static void consultationsParMedium() {
        System.out.println("=== Nombre de consultations par médium ===");
        List<StatMedium> stats = statsService.listerNombreConsultationsParMedium();
        if (stats.isEmpty()) {
            System.out.println("  Aucune donnée.");
        } else {
            stats.forEach(s -> System.out.println("  " + s.medium().getDenomination() + " : " + s.nombre()));
        }
    }

    private static void repartitionClientsParEmploye() {
        System.out.println("=== Répartition des clients par employé ===");
        List<StatEmploye> stats = statsService.listerRepartitionClientParEmploye();
        if (stats.isEmpty()) {
            System.out.println("  Aucune donnée.");
        } else {
            stats.forEach(s -> System.out.println("  " + s.employe().getPrenom() + " " + s.employe().getNom() + " : " + s.nombre() + " client(s)"));
        }
    }

    private static void mediumsPopulaires() {
        System.out.println("=== Top 3 médiums les plus populaires ===");
        List<StatMedium> stats = statsService.listerMediumsPopulaires(3);
        if (stats.isEmpty()) {
            System.out.println("  Aucune donnée.");
        } else {
            int rang = 1;
            for (StatMedium s : stats) {
                System.out.println("  #" + rang++ + " " + s.medium().getDenomination() + " (" + s.nombre() + " consultation(s))");
            }
        }
    }

    private static void repartitionGeographique() {
        System.out.println("=== Répartition géographique des clients ===");
        List<StatDepartement> stats = statsService.listerRepartitionGeographiqueClients();
        if (stats.isEmpty()) {
            System.out.println("  Aucune donnée.");
        } else {
            stats.forEach(s -> System.out.println("  Département " + s.codeDepartement() + " : " + s.nombre() + " client(s)"));
        }
    }
}
