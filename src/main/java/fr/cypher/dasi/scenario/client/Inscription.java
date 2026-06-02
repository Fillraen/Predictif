package fr.cypher.dasi.scenario.client;

import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.metier.service.AuthService;
import fr.cypher.dasi.metier.service.ClientService;

import java.time.LocalDate;
import java.util.List;

public class Inscription {

    private static final AuthService   authService   = new AuthService();
    private static final ClientService clientService = new ClientService();

    public static void executer() {
        System.out.println("[DEBUT SCENARIO] Inscription");
        inscriptionOK();
        System.out.println();
        inscriptionDoublon();
        System.out.println("[FIN SCENARIO] Inscription");
    }

    private static void inscriptionOK() {
        System.out.println("=== TEST 1 : inscription réussie ===");
        Adresse adresse = new Adresse("12", "Rue des Lilas", "69003", "Lyon", "69");
        Client bob = new Client(
                "bob.martin@free.fr", "Bob", "Martin", "Bob!2025",
                "0611223344", Genre.HOMME,
                LocalDate.of(1988, 7, 15),
                adresse, null
        );
        boolean res = authService.inscrire(bob);
        System.out.println("Résultat : " + (res ? "OK - client inscrit" : "ECHEC"));
        listerClients();
    }

    private static void inscriptionDoublon() {
        System.out.println("=== TEST 2 : double inscription (mail déjà en base) ===");
        Adresse adresse = new Adresse("42", "Rue Lecourbe", "75015", "Paris", "75");
        Client aliceDoublon = new Client(
                "alice.pascal@free.fr", "Alice", "Pascal", "autreMotDePasse",
                "0600000099", Genre.FEMME,
                LocalDate.of(1995, 2, 5),
                adresse, null
        );
        boolean res = authService.inscrire(aliceDoublon);
        System.out.println("Résultat : " + (res ? "PROBLEME - doublon accepté" : "OK - doublon refusé"));
        listerClients();
    }

    private static void listerClients() {
        System.out.println("--- Liste des clients ---");
        List<Client> clients = clientService.listerClients();
        if (clients != null && !clients.isEmpty()) {
            clients.forEach(System.out::println);
        } else {
            System.out.println("Aucun client.");
        }
        System.out.println("-------------------------");
    }
}
