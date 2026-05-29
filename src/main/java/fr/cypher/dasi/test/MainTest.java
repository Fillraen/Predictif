package fr.cypher.dasi.test;

import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Medium;
import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;
import fr.cypher.dasi.metier.service.*;

import java.time.LocalDate;
import java.util.List;

public class MainTest {
    static void mediums() {
        MediumService mediumService = new MediumService();
        List<Medium> mediums = mediumService.listerMediums();
        for (Medium medium : mediums) {
            System.out.println(medium);
        }
        System.out.println("--------");
        List<Medium> spirites = mediumService.listerMediums(TypeMedium.SPIRITE);
        for (Medium spirite : spirites) {
            System.out.println(spirite);
        }
        System.out.println("--------");
        List<TypeMedium> types = mediumService.listerTypeMediums();
        for (TypeMedium type : types) {
            System.out.println(type);
        }
    }

    static void consultations() {
        ConsultationService consultationService = new ConsultationService();
        MediumService mediumService = new MediumService();
        ClientService clientService = new ClientService();
        boolean result = consultationService.demanderConsultation(
                clientService.listerClients().getFirst(),
                mediumService.listerMediums().getFirst()
        );
        if (result) System.out.println("Consultation demandée avec succès");
        else System.out.println("Echec de demande de consultation");
    }

    public static void executer() {
        if (!(new InitService()).init()) {
            System.out.println("[ERROR] Problème lors de l'init des données. Annulation des tests.");
            return;
        }
        // Registering one client to use it
        Adresse adresseAlice = new Adresse("20", "Avenue Albert Einstein", "69100", "Villeurbanne", "69");
        Client alice = new Client(
                "alice@predictif.fr", "Alice", "motdepasse123", "0600000001",
                "Dupont", LocalDate.of(1995, 12, 10), adresseAlice, Genre.FEMME
        );
        new AuthService().Inscrire(alice);
        mediums();
        consultations();
    }
}
