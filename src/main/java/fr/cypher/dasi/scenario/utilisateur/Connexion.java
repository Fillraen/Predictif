package fr.cypher.dasi.scenario.utilisateur;

import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Employe;
import fr.cypher.dasi.metier.modele.Utilisateur;
import fr.cypher.dasi.metier.service.AuthService;

public class Connexion {

    private static final AuthService authService = new AuthService();

    public static void executer() {
        System.out.println("[DEBUT SCENARIO] Connexion");
        connexionOK();
        System.out.println();
        connexionMdpFaux();
        System.out.println();
        connexionUtilisateurInexistant();
        System.out.println();
        connexionDifferenciationType();
        System.out.println("[FIN SCENARIO] Connexion");
    }

    private static void connexionOK() {
        System.out.println("=== TEST 1 : connexion réussie (client) ===");
        Utilisateur u = authService.authentifier("alice.pascal@free.fr", "Alice!2025");
        System.out.println("Résultat : " + (u != null ? "OK - connecté en tant que " + u.getPrenom() + " " + u.getNom() : "ECHEC"));
    }

    private static void connexionMdpFaux() {
        System.out.println("=== TEST 2 : mauvais mot de passe ===");
        Utilisateur u = authService.authentifier("alice.pascal@free.fr", "mauvaisMotDePasse");
        System.out.println("Résultat : " + (u == null ? "OK - connexion refusée" : "PROBLEME - connexion acceptée à tort"));
    }

    private static void connexionUtilisateurInexistant() {
        System.out.println("=== TEST 3 : utilisateur inexistant ===");
        Utilisateur u = authService.authentifier("inconnu@predictif.fr", "Alice!2025");
        System.out.println("Résultat : " + (u == null ? "OK - utilisateur non trouvé" : "PROBLEME - utilisateur fantôme accepté"));
    }

    private static void connexionDifferenciationType() {
        System.out.println("=== TEST 4 : différenciation Client / Employé ===");
        afficherTypeUtilisateur(authService.authentifier("alice.pascal@free.fr", "Alice!2025"));
        afficherTypeUtilisateur(authService.authentifier("camille.martin@predictif.com", "CamilleM!2025"));
    }

    private static void afficherTypeUtilisateur(Utilisateur u) {
        if (u instanceof Client client) {
            System.out.println(client.getPrenom() + " " + client.getNom() + " -> CLIENT");
        } else if (u instanceof Employe employe) {
            System.out.println(employe.getPrenom() + " " + employe.getNom() + " -> EMPLOYÉ (disponible : " + employe.isEstDisponible() + ")");
        } else {
            System.out.println("Connexion échouée ou type inconnu");
        }
    }
}
