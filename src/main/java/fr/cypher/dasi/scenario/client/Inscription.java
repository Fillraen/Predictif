package fr.cypher.dasi.scenario.client;

import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.metier.service.AuthService;
import fr.cypher.dasi.metier.service.ClientService;

import java.sql.Date;
import java.util.List;

public class Inscription {

    private static final AuthService   authService   = new AuthService();
    private static final ClientService clientService = new ClientService();

    public static void executer() {

        System.out.println("=== TEST 1 : inscription réussie ===");
        Adresse adresseAlice = new Adresse("20", "Avenue Albert Einstein", "69100", "Villeurbanne", "69");
        Client alice = new Client(
                "alice@predictif.fr", "Alice", "motdepasse123", "0600000001",
                "Dupont", Date.valueOf("1995-12-10"), adresseAlice, Genre.FEMME
        );
        boolean res1 = authService.Inscrire(alice);
        System.out.println("Résultat : " + (res1 ? "OK - client inscrit" : "ECHEC"));
        listerClients();

        System.out.println();
        System.out.println("=== TEST 2 : double inscription (même mail) ===");
        Adresse adresseDoublon = new Adresse("5", "Rue de la Paix", "75001", "Paris", "75");
        Client aliceDoublon = new Client(
                "alice@predictif.fr", "Alice", "autreMotDePasse", "0600000002",
                "Martin", Date.valueOf("1990-05-20"), adresseDoublon, Genre.FEMME
        );
        boolean res2 = authService.Inscrire(aliceDoublon);
        System.out.println("Résultat : " + (res2 ? "PROBLEME - doublon accepté" : "OK - doublon refusé"));
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
