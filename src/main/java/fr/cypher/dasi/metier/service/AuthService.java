package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.ClientDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Utilisateur;
import fr.cypher.dasi.util.API.IfAstroNetApi;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class AuthService {
    private final ClientDAO clientDao = new ClientDAO();

    public boolean Inscrire(Client c){
        boolean result = false;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            c.setProfilAstral(IfAstroNetApi.obtenirProfilAstral(c.getPrenom(), c.getDateDeNaissance()));
            clientDao.creerClient(c);
            JpaUtil.validerTransaction();
            result = true;
        } catch (Exception e) {
            System.err.println("[AuthService] Échec inscription : " + e.getMessage());
            e.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
}
