package fr.cypher.dasi.scenario.client;

import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Utilisateur;
import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.metier.service.AuthService;

import java.sql.Date;

public class Connexion {

    private static final AuthService authService = new AuthService();

    public static void executer() {

        // Pré-requis : inscription de Bob
        System.out.println("=== PRÉ-REQUIS : inscription de Bob ===");
        Adresse adresseBob = new Adresse("12", "Rue des Lilas", "69003", "Lyon", "69");
        Client bob = new Client(
                "bob@predictif.fr", "Bob", "secret42", "0611223344",
                "Martin", Date.valueOf("1988-07-15"), adresseBob, Genre.HOMME
        );
        boolean inscrit = authService.Inscrire(bob);
        System.out.println("Inscription Bob : " + (inscrit ? "OK" : "ECHEC"));

        System.out.println();

        // TEST 1 : connexion réussie
        System.out.println("=== TEST 1 : connexion réussie ===");
        Utilisateur u1 = authService.Authentifier("bob@predictif.fr", "secret42");
        System.out.println("Résultat : " + (u1 != null ? "OK - connecté en tant que " + u1.getPrenom() + " " + u1.getNom() : "ECHEC"));

        System.out.println();

        // TEST 2 : mauvais mot de passe
        System.out.println("=== TEST 2 : mauvais mot de passe ===");
        Utilisateur u2 = authService.Authentifier("bob@predictif.fr", "mauvaisMotDePasse");
        System.out.println("Résultat : " + (u2 == null ? "OK - connexion refusée" : "PROBLEME - connexion acceptée à tort"));

        System.out.println();

        // TEST 3 : utilisateur inexistant
        System.out.println("=== TEST 3 : utilisateur inexistant ===");
        Utilisateur u3 = authService.Authentifier("inconnu@predictif.fr", "secret42");
        System.out.println("Résultat : " + (u3 == null ? "OK - utilisateur non trouvé" : "PROBLEME - utilisateur fantôme accepté"));
    }
}
