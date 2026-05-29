package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.EmployeDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.Employe;
import fr.cypher.dasi.metier.modele.Prediction;
import fr.cypher.dasi.metier.modele.embedded.ProfilAstral;
import fr.cypher.dasi.util.API.IfAstroNetApi;

import java.util.List;

public class EmployeService {
    private final EmployeDAO employeDAO = new EmployeDAO();

    public List<Employe> listerEmployes() {
        try {
            JpaUtil.creerContextePersistance();
            return employeDAO.getEmployes();
        } catch (Exception e) {
            System.err.println("Impossible de lister les employés");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }

    public Prediction demanderInspiration(ProfilAstral profilAstral, int scoreAmour, int scoreSante, int scoreTravail) {
        try {
            return IfAstroNetApi.obtenirPredictions(profilAstral.getCouleurBonheur(), profilAstral.getAnimalTotem(), scoreAmour, scoreSante, scoreTravail);
        } catch (Exception e) {
            System.err.println("[AuthService] Impossible de récupérer les prédictions : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
