package fr.cypher.dasi.scenario.employe;

import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Consultation;
import fr.cypher.dasi.metier.modele.Employe;
import fr.cypher.dasi.metier.modele.Prediction;
import fr.cypher.dasi.metier.modele.Utilisateur;
import fr.cypher.dasi.metier.service.AuthService;
import fr.cypher.dasi.metier.service.ConsultationService;
import fr.cypher.dasi.metier.service.EmployeService;
import fr.cypher.dasi.scenario.stats.PageStats;

import java.util.List;

public class ParcoursEmploye {

    private static final AuthService        authService        = new AuthService();
    private static final ConsultationService consultationService = new ConsultationService();
    private static final EmployeService     employeService     = new EmployeService();

    public static void executer() {
        System.out.println("[DEBUT SCENARIO] ParcoursEmploye");

        Employe camille = connexionEmploye();
        if (camille == null) { System.out.println("[FIN SCENARIO] ParcoursEmploye (abandon)"); return; }
        System.out.println();

        Consultation consultation = voirConsultationAffectee(camille);
        if (consultation == null) { System.out.println("[FIN SCENARIO] ParcoursEmploye (abandon)"); return; }
        System.out.println();

        voirProfilClient(consultation);
        System.out.println();

        declarerPret(consultation);
        System.out.println();

        demanderPredictions(consultation);
        System.out.println();

        terminerConsultation(consultation);
        System.out.println();

        verifierSauvegarde(consultation);
        System.out.println();

        verifierAucuneConsultationRestante(camille);
        System.out.println();

        PageStats.executer();

        System.out.println("[FIN SCENARIO] ParcoursEmploye");
    }

    private static Employe connexionEmploye() {
        System.out.println("=== ÉTAPE 1 : connexion employé ===");
        Utilisateur u = authService.authentifier("camille.martin@predictif.com", "CamilleM!2025");
        if (u instanceof Employe e) {
            System.out.println("Connecté : " + e.getPrenom() + " " + e.getNom()
                    + " | disponible : " + e.isEstDisponible());
            return e;
        }
        System.err.println("ECHEC - connexion employé impossible");
        return null;
    }

    private static Consultation voirConsultationAffectee(Employe employe) {
        System.out.println("=== ÉTAPE 2 : consultation affectée ===");
        Consultation c = consultationService.consulterConsultationAffectee(employe);
        if (c != null) {
            System.out.println("Consultation trouvée :"
                    + "\n  Client  : " + c.getClient().getPronomNomComplet()
                    + "\n  Médium  : " + c.getMedium().getDenomination()
                    + "\n  Date    : " + c.getDateTime());
        } else {
            System.err.println("ECHEC - aucune consultation affectée");
        }
        return c;
    }

    private static void voirProfilClient(Consultation consultation) {
        System.out.println("=== ÉTAPE 3 : profil du client ===");
        Client client = consultation.getClient();
        System.out.println("Profil :"
                + "\n  Nom           : " + client.getPronomNomComplet()
                + "\n  Mail          : " + client.getMail()
                + "\n  Téléphone     : " + client.getTelephone()
                + "\n  Né(e) le      : " + client.getDateDeNaissance()
                + "\n  Adresse       : " + client.getAdresse()
                + "\n  Profil astral : " + client.getProfilAstral());

        System.out.println("Historique des consultations :");
        List<Consultation> historique = consultationService.consulterHistoriqueConsultations(client);
        if (historique == null || historique.isEmpty()) {
            System.out.println("  Aucune consultation précédente.");
        } else {
            historique.forEach(h -> System.out.println("  - [" + (h.isEstTermine() ? "terminée" : "en cours") + "] "
                    + h.getMedium().getDenomination()
                    + " | " + h.getDateTime()
                    + (h.getCommentaire() != null && !h.getCommentaire().isBlank()
                        ? " | \"" + h.getCommentaire() + "\"" : "")));
        }
    }

    private static void declarerPret(Consultation consultation) {
        System.out.println("=== ÉTAPE 4 : déclarer prêt ===");
        boolean ok = consultationService.declarerPret(consultation);
        System.out.println("Résultat : " + (ok ? "OK - client notifié" : "ECHEC"));
    }

    private static void demanderPredictions(Consultation consultation) {
        System.out.println("=== ÉTAPE 5 : demande de prédictions ===");
        // Scores saisis par l'employé lors de la consultation
        int scoreAmour = 3, scoreSante = 4, scoreTravail = 2;
        Prediction prediction = employeService.demanderInspiration(
                consultation.getClient().getProfilAstral(),
                scoreAmour, scoreSante, scoreTravail
        );
        if (prediction != null) {
            System.out.println("Prédictions reçues :"
                    + "\n  Amour   : " + prediction.getAmour()
                    + "\n  Santé   : " + prediction.getSante()
                    + "\n  Travail : " + prediction.getTravail());
        } else {
            System.err.println("ECHEC - impossible de récupérer les prédictions");
        }
    }

    private static void terminerConsultation(Consultation consultation) {
        System.out.println("=== ÉTAPE 6 : terminer la consultation ===");
        boolean ok = consultationService.terminerConsultation(
                consultation,
                "Client très réceptif, bonne énergie. Prédictions bien reçues."
        );
        System.out.println("Résultat : " + (ok ? "OK - consultation terminée" : "ECHEC"));
    }

    private static void verifierSauvegarde(Consultation consultation) {
        System.out.println("=== ÉTAPE 7 : vérification sauvegarde en base ===");
        Consultation saved = consultationService.recupererConsultationParId(consultation.getId());
        if (saved != null) {
            System.out.println("Consultation récupérée depuis la base :"
                    + "\n  Statut     : " + (saved.isEstTermine() ? "terminée" : "en cours")
                    + "\n  Commentaire: \"" + saved.getCommentaire() + "\"");
        } else {
            System.err.println("ECHEC - consultation introuvable en base");
        }
    }

    private static void verifierAucuneConsultationRestante(Employe employe) {
        System.out.println("=== ÉTAPE 8 : vérification aucune consultation restante ===");
        Consultation restante = consultationService.consulterConsultationAffectee(employe);
        if (restante == null) {
            System.out.println("OK - aucune consultation active restante pour " + employe.getPrenom());
        } else {
            System.err.println("ANOMALIE - une consultation est encore affectée : id=" + restante.getId()
                    + " | client=" + restante.getClient().getPronomNomComplet());
        }
    }
}
