package fr.cypher.dasi;

import fr.cypher.dasi.dao.ClientDAO;
import fr.cypher.dasi.dao.ConsultationDAO;
import fr.cypher.dasi.dao.EmployeDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.dao.MediumDAO;
import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.embedded.Adresse;
import fr.cypher.dasi.metier.modele.embedded.ProfilAstral;
import fr.cypher.dasi.metier.modele.enums.Genre;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MainInit {

    public static boolean executer() {
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            MediumDAO     mediumDAO     = new MediumDAO();
            EmployeDAO    employeDAO    = new EmployeDAO();
            ClientDAO     clientDAO     = new ClientDAO();
            ConsultationDAO consultDAO  = new ConsultationDAO();

            // ── Médiums ────────────────────────────────────────────────────────────
            Spirite gwenaelle = new Spirite(
                    "Gwenaëlle", Genre.FEMME,
                    "Spécialiste des grandes conversations au-delà de TOUTES les frontières.",
                    "Boule de cristal"
            );
            Spirite professseurTran = new Spirite(
                    "Professeur Tran", Genre.HOMME,
                    "Votre avenir est devant vous : regardons-le ensemble !",
                    "Marc de café, boule de cristal, oreilles de lapin"
            );
            Cartomancien mmeIrma = new Cartomancien(
                    "Mme Irma", Genre.FEMME,
                    "Comprenez votre entourage grâce à mes cartes ! Résultats rapides."
            );
            Cartomancien endora = new Cartomancien(
                    "Endora", Genre.FEMME,
                    "Mes cartes répondront à toutes vos questions personnelles."
            );
            Astrologue serena = new Astrologue(
                    "Serena", Genre.FEMME,
                    "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.",
                    "École Normale Supérieure d'Astrologie (ENS-Astro)",
                    2006
            );
            Astrologue mrM = new Astrologue(
                    "Mr M", Genre.HOMME,
                    "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !",
                    "Institut des Nouveaux Savoirs Astrologiques",
                    2010
            );

            mediumDAO.creerMedium(gwenaelle);
            mediumDAO.creerMedium(professseurTran);
            mediumDAO.creerMedium(mmeIrma);
            mediumDAO.creerMedium(endora);
            mediumDAO.creerMedium(serena);
            mediumDAO.creerMedium(mrM);

            // ── Employés ───────────────────────────────────────────────────────────
            Employe camille   = new Employe("camille.martin@predictif.com",       "Camille",    "Martin",    "CamilleM!2025",  "0655447788", Genre.FEMME);
            Employe marine    = new Employe("marine.lepen@predictif.com",          "Marine",     "Le Pen",    "MarineLP!2025",  "0112233221", Genre.FEMME);
            Employe geraldine = new Employe("geraldine.tulipe@predictif.com",      "Géraldine",  "Tulipe",    "GeraldineT!25",  "0133445566", Genre.NON_SPECIFIE);
            Employe brice     = new Employe("brice.nice@predictif.com",            "Brice",      "Nice",      "BriceN!2025",    "0677667766", Genre.HOMME);
            Employe emmanuel  = new Employe("emmanuel.macaron@predictif.com",      "Emmanuel",   "Macaron",   "EmmanuelM!2025", "0142928100", Genre.HOMME);
            Employe jeanLuc   = new Employe("jean-luc.melenchon@predictif.com",    "Jean-Luc",   "Mélenchon", "JeanLucM!2025",  "0144335522", Genre.HOMME);
            Employe matthieu  = new Employe("matthieu.maranzana@predictif.com",    "Matthieu",   "Maranzana", "MatthieuM!2025", "0955664477", Genre.HOMME);
            Employe frederic  = new Employe("frederic.prost@predictif.com",        "Frédéric",   "Prost",     "FredericP!2025", "0877663344", Genre.HOMME);

            employeDAO.creerEmploye(camille);
            employeDAO.creerEmploye(marine);
            employeDAO.creerEmploye(geraldine);
            employeDAO.creerEmploye(brice);
            employeDAO.creerEmploye(emmanuel);
            employeDAO.creerEmploye(jeanLuc);
            employeDAO.creerEmploye(matthieu);
            employeDAO.creerEmploye(frederic);

            // ── Client : Alice PASCAL ──────────────────────────────────────────────
            // Née le 05/02/1995 → Verseau (20 jan – 18 fév) + Cochon (année chinoise 1995)
            Adresse adresseAlice = new Adresse("42", "Rue Lecourbe", "75015", "Paris", "75");
            ProfilAstral profilAlice = new ProfilAstral("Marmotte", "Verseau", "Bleu", "Cochon");
            Client alice = new Client(
                    "alice.pascal@free.fr", "Alice", "Pascal", "Alice!2025",
                    "0688774455", Genre.FEMME,
                    LocalDate.of(1995, 2, 5),
                    adresseAlice, profilAlice
            );
            clientDAO.creerClient(alice);

            // ── Consultations ──────────────────────────────────────────────────────
            // En cours : Alice / Camille / Gwenaëlle
            consultDAO.creerConsultation(new Consultation(
                    null,
                    LocalDateTime.of(2025, 5, 28, 10, 0),
                    false,
                    alice, camille, gwenaelle
            ));

            // Terminée : Alice / Brice / Professeur Tran
            consultDAO.creerConsultation(new Consultation(
                    "Excellente séance, le client était très réceptif aux prédictions.",
                    LocalDateTime.of(2025, 5, 20, 14, 30),
                    true,
                    alice, brice, professseurTran
            ));

            // Terminée : Alice / Marine / Serena
            consultDAO.creerConsultation(new Consultation(
                    "Bonne consultation, l'avenir s'annonce prometteur selon les astres.",
                    LocalDateTime.of(2025, 5, 10, 9, 0),
                    true,
                    alice, marine, serena
            ));

            // Terminée : Alice / Emmanuel / Mme Irma
            consultDAO.creerConsultation(new Consultation(
                    "Très bonne lecture des cartes, la cliente a été touchée par la précision des révélations.",
                    LocalDateTime.of(2025, 4, 22, 11, 0),
                    true,
                    alice, emmanuel, mmeIrma
            ));

            // Terminée : Alice / Matthieu / Endora
            consultDAO.creerConsultation(new Consultation(
                    "Séance apaisante, les cartes ont confirmé les intuitions de la cliente.",
                    LocalDateTime.of(2025, 3, 15, 16, 0),
                    true,
                    alice, matthieu, endora
            ));

            // Terminée : Alice / Frédéric / Mr M
            consultDAO.creerConsultation(new Consultation(
                    "Prédictions astrologiques très précises, la cliente repart avec des clés pour l'avenir.",
                    LocalDateTime.of(2025, 2, 8, 10, 30),
                    true,
                    alice, frederic, mrM
            ));

            // Terminée : Alice / Jean-Luc / Professeur Tran (2e passage)
            consultDAO.creerConsultation(new Consultation(
                    "Deuxième consultation avec le Professeur Tran, la cliente progresse dans sa démarche.",
                    LocalDateTime.of(2025, 1, 20, 14, 0),
                    true,
                    alice, jeanLuc, professseurTran
            ));

            // ── Client : Marc DUPONT (Lyon, Rhône) ────────────────────────────────
            // Né le 12/07/1988 → Cancer (21 juin – 22 juil) + Dragon (année chinoise 1988)
            Adresse adresseMarc = new Adresse("7", "Rue de la République", "69001", "Lyon", "69");
            ProfilAstral profilMarc = new ProfilAstral("Aigle", "Cancer", "Rouge", "Dragon");
            Client marc = new Client(
                    "marc.dupont@orange.fr", "Marc", "Dupont", "MarcD!2025",
                    "0612345678", Genre.HOMME,
                    LocalDate.of(1988, 7, 12),
                    adresseMarc, profilMarc
            );
            clientDAO.creerClient(marc);

            // Terminée : Marc / Brice / Gwenaëlle
            consultDAO.creerConsultation(new Consultation(
                    "Premier contact avec la spirite, le client est curieux et ouvert.",
                    LocalDateTime.of(2025, 5, 5, 10, 0),
                    true,
                    marc, brice, gwenaelle
            ));

            // Terminée : Marc / Camille / Serena
            consultDAO.creerConsultation(new Consultation(
                    "La lecture des astres confirme une période de transition professionnelle.",
                    LocalDateTime.of(2025, 4, 1, 15, 0),
                    true,
                    marc, camille, serena
            ));

            JpaUtil.validerTransaction();
            System.out.println("[MainInit] Données de démo insérées avec succès.");
            return true;

        } catch (Exception e) {
            System.err.println("[MainInit] Échec de l'initialisation : " + e.getMessage());
            e.printStackTrace();
            JpaUtil.annulerTransaction();
            return false;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
    }
}
