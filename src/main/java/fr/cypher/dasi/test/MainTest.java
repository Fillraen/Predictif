package fr.cypher.dasi.test;

import fr.cypher.dasi.MainInit;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;
import fr.cypher.dasi.metier.service.*;

import java.time.LocalDate;
import java.util.List;

public class MainTest {
    static void mediums() {
        MediumService mediumService = new MediumService();
        List<Medium> mediums = mediumService.listerMediums(null);
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
        EmployeService employeService = new EmployeService();
        Client client = clientService.listerClients().getFirst();
        Medium medium = mediumService.listerMediums(null).getFirst();
        List<Employe> employes = employeService.listerEmployes();

        // 2 employees Genre.FEMME available
        boolean result = consultationService.demanderConsultation(client, medium);
        if (result) System.out.println("[OK] Consultation demandée avec succès");
        else System.err.println("[ERREUR] Echec de demande de consultation (pourtant employé disponible normalement)");

        result = consultationService.demanderConsultation(client, medium);
        if (result) System.out.println("[OK] Consultation demandée avec succès");
        else System.err.println("[ERREUR] Echec de demande de consultation (pourtant employé disponible normalement)");

        result = consultationService.demanderConsultation(client, medium);
        if (result) System.err.println("[ERREUR] Consultation demandée avec succès (il n'aurait pas dû réussir, employé non disponible)");
        else System.out.println("[OK] Echec de demande de consultation (ce qu'on cherchait, aucun employé disponible)");

        List<Consultation> consultations = consultationService.consulterHistoriqueConsultations(client);
        for (Consultation consultation : consultations) {
            System.out.println(consultation);
        }

        Employe camille = employes.get(0);
        Employe marine = employes.get(1);
        Employe brice = employes.get(3);

        Consultation consultationCamille = consultationService.consulterConsultationAffectee(camille);
        if (consultationCamille != null) System.out.println("[OK] Consultation : " + consultationCamille);
        else System.err.println("[ERREUR] Aucune consultation pour Camille (elle doit en avoir une normalement)");

        Consultation consultationMarine = consultationService.consulterConsultationAffectee(marine);
        if (consultationMarine != null) System.out.println("[OK] Consultation : " + consultationMarine);
        else System.err.println("[ERREUR] Aucune consultation pour Marine (elle doit en avoir une normalement)");

        Consultation consultationBrice = consultationService.consulterConsultationAffectee(brice);
        if (consultationBrice != null) System.err.println("[ERREUR] Consultation (normalement aucune) : " + consultationBrice);
        else System.out.println("[OK] Aucune consultation pour Brice (comportement attendu)");

        if(consultationService.declarerPret(consultationCamille)) System.out.println("[OK] Prêt !");
        else System.err.println("[ERREUR] Impossible de se mettre prêt.");
        if(consultationService.declarerPret(consultationBrice)) System.err.println("[ERROR] Prêt ! (censé être null)");
        else System.out.println("[OK] Impossible de se mettre prêt.");

        if (consultationService.terminerConsultation(consultationCamille, "Banger")) System.out.println("[OK] Consultation finie !");
        else System.err.println("[ERROR] Consultation non terminée.");
        if (consultationService.terminerConsultation(consultationBrice, "Décevant")) System.err.println("[ERROR] Consultation finie (pas censé être le cas)");
        else System.out.println("[OK] Consultation non terminée (résultat attendu).");

        System.out.println(employeService.demanderInspiration(client.getProfilAstral(), 2, 4, 3));
    }

    public static void executer() {
        // Registering one client to use it
        Adresse adresseAlice = new Adresse("20", "Avenue Albert Einstein", "69100", "Villeurbanne", "69");
        Client alice = new Client(
                "alice@predictif.fr", "Alice", "motdepasse123", "0600000001",
                "Dupont", LocalDate.of(1995, 12, 10), adresseAlice, Genre.FEMME
        );
        new AuthService().inscrire(alice);
        mediums();
        consultations();
    }


    static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();

        if (!MainInit.executer()) {
            System.err.println("[Main] Initialisation échouée, arrêt.");
            JpaUtil.fermerFabriquePersistance();
            return;
        }

        executer();

        JpaUtil.fermerFabriquePersistance();
    }
}
