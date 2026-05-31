package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.ClientDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.dao.UtilisateurDAO;
import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Utilisateur;
import fr.cypher.dasi.util.API.DataGouvApi;
import fr.cypher.dasi.util.API.IfAstroNetApi;
import fr.cypher.dasi.util.Message;

import javax.persistence.NoResultException;
import java.util.List;

public class AuthService {
    private final ClientDAO clientDao = new ClientDAO();
    private final UtilisateurDAO utilisateurDao = new UtilisateurDAO();

    public boolean Inscrire(Client c){
        boolean succes = false;
        String raisonEchec = null;

        if (DataGouvApi.obtenirCoordonnees(c.getAdresse()) == null) {
            raisonEchec = "l’adresse fournie est introuvable.\nMerci de vérifier votre adresse et de recommencer.";
            System.err.println("[AuthService] Inscription refusée : adresse introuvable (" + c.getAdresse() + ")");
        } else {
            try {
                JpaUtil.creerContextePersistance();
                JpaUtil.ouvrirTransaction();
                c.setProfilAstral(IfAstroNetApi.obtenirProfilAstral(c.getPrenom(), c.getDateDeNaissance()));
                clientDao.creerClient(c);
                JpaUtil.validerTransaction();
                succes = true;
            } catch (Exception e) {
                raisonEchec = "une erreur technique s’est produite.\nMerci de recommencer ultérieurement.";
                System.err.println("[AuthService] Échec inscription : " + e.getMessage());
                e.printStackTrace();
                JpaUtil.annulerTransaction();
            } finally {
                JpaUtil.fermerContextePersistance();
            }
        }

        if (succes) {
            Message.envoyerMail("contact@predict.if",
                    c.getMail(),
                    "Bienvenue chez PREDICT’IF",
                    "Bonjour " + c.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF. Rendez-vous vite sur notre site pour consulter votre profil astrologique et profiter des dons\n" +
                            "incroyables de nos mediums");
        } else {
            Message.envoyerMail("contact@predict.if",
                    c.getMail(),
                    "Echec de l’inscription chez PREDICT’IF",
                    "Bonjour " + c.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué...\n" +
                            "Merci de recommencer ultérieurement. \n");
        }

        return succes;
    }

    public Utilisateur Authentifier(String mail, String motDePasse){
        Utilisateur u = null;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            List<Utilisateur> utilisateurs = utilisateurDao.getAllWithFilter(null, null, mail, null);
            if (!utilisateurs.isEmpty()) {
                u = utilisateurs.get(0);
                if (!u.getMotDePasse().equals(motDePasse)) {
                    u = null;
                }
            }
            JpaUtil.validerTransaction();
        } catch (NoResultException e) {
            System.err.println("[AuthService] Authentification échouée : utilisateur non trouvé");
        } catch (Exception e) {
            System.err.println("bug");
            e.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return u;
    }
}
