package fr.cypher.dasi.scenario.client;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;
import fr.cypher.dasi.metier.service.AuthService;
import fr.cypher.dasi.metier.service.ConsultationService;
import fr.cypher.dasi.metier.service.MediumService;

import java.time.LocalDate;
import java.util.List;

public class ParcoursClient {

    private static final AuthService         authService         = new AuthService();
    private static final MediumService       mediumService       = new MediumService();
    private static final ConsultationService consultationService = new ConsultationService();

    static void main(String[] args) {
        JpaUtil.creerFabriquePersistance();

        executer();

        JpaUtil.fermerFabriquePersistance();
    }

    public static void executer() {
        System.out.println("[DEBUT SCENARIO] ParcoursClient");

        boolean inscrit = inscription();
        if (!inscrit) { System.out.println("[FIN SCENARIO] ParcoursClient (abandon)"); return; }
        System.out.println();

        Client connecte = connexionLea();
        if (connecte == null) { System.out.println("[FIN SCENARIO] ParcoursClient (abandon)"); return; }
        System.out.println();

        listerMediums();
        System.out.println();

        Medium mediumChoisi = filtrerParCartomancien();
        if (mediumChoisi == null) { System.out.println("[FIN SCENARIO] ParcoursClient (abandon)"); return; }
        System.out.println();

        demanderConsultation(connecte, mediumChoisi);
        System.out.println();

        voirHistorique(connecte);

        System.out.println("[FIN SCENARIO] ParcoursClient");
    }

    private static boolean inscription() {
        System.out.println("=== ÉTAPE 1 : inscription ===");
        Adresse adresse = new Adresse("3", "Allée des Roses", "33000", "Bordeaux", "33");
        Client lea = new Client(
                "lea.dubois@gmail.com", "Léa", "Dubois", "Lea!2025",
                "0622334455", Genre.FEMME,
                LocalDate.of(1998, 4, 17),
                adresse
        );
        boolean ok = authService.inscrire(lea);
        System.out.println("Résultat : " + (ok ? "OK - inscrite, profil astral calculé" : "ECHEC"));
        return ok;
    }

    private static Client connexionLea() {
        System.out.println("=== ÉTAPE 2 : connexion ===");
        Utilisateur u = authService.authentifier("lea.dubois@gmail.com", "Lea!2025");
        if (u instanceof Client c) {
            System.out.println("Connecté : " + c.getPronomNomComplet()
                    + "\n  Profil astral : " + c.getProfilAstral());
            return c;
        }
        System.err.println("ECHEC - connexion client impossible");
        return null;
    }

    private static void listerMediums() {
        System.out.println("=== ÉTAPE 3 : liste de tous les médiums ===");
        List<Medium> mediums = mediumService.listerMediums(null);
        if (mediums == null || mediums.isEmpty()) {
            System.out.println("Aucun médium disponible.");
            return;
        }
        mediums.forEach(m -> System.out.println("  - [" + typeMedium(m) + "] "
                + m.getDenomination() + " : " + m.getPresentation()));
    }

    private static Medium filtrerParCartomancien() {
        System.out.println("=== ÉTAPE 4 : filtre par type — " + TypeMedium.CARTOMANCIEN + " ===");
        List<Medium> mediums = mediumService.listerMediums(TypeMedium.CARTOMANCIEN);
        if (mediums == null || mediums.isEmpty()) {
            System.err.println("ECHEC - aucun médium de type " + TypeMedium.CARTOMANCIEN);
            return null;
        }
        mediums.forEach(m -> System.out.println("  - " + m.getDenomination() + " : " + m.getPresentation()));
        Medium choisi = mediums.getFirst();
        System.out.println("Médium sélectionné : " + choisi.getDenomination());
        return choisi;
    }

    private static void demanderConsultation(Client client, Medium medium) {
        System.out.println("=== ÉTAPE 5 : demande de consultation avec " + medium.getDenomination() + " ===");
        boolean ok = consultationService.demanderConsultation(client, medium);
        System.out.println("Résultat : " + (ok ? "OK - consultation créée, employé notifié" : "ECHEC"));
    }

    private static void voirHistorique(Client client) {
        System.out.println("=== ÉTAPE 6 : historique des consultations ===");
        List<Consultation> historique = consultationService.consulterHistoriqueConsultations(client);
        if (historique == null || historique.isEmpty()) {
            System.out.println("Aucune consultation.");
            return;
        }
        historique.forEach(h -> System.out.println("  - [" + (h.isEstTermine() ? "terminée" : "en cours") + "] "
                + h.getMedium().getDenomination()
                + " | " + h.getDateTime()
                + (h.getCommentaire() != null && !h.getCommentaire().isBlank()
                    ? " | \"" + h.getCommentaire() + "\"" : "")));
    }

    private static String typeMedium(Medium m) {
        if (m instanceof Cartomancien) return "CARTOMANCIEN";
        if (m instanceof Spirite)      return "SPIRITE";
        if (m instanceof Astrologue)   return "ASTROLOGUE";
        return "INCONNU";
    }
}
