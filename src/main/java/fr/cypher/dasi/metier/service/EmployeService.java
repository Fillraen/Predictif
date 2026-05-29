package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.EmployeDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.dao.MediumDAO;
import fr.cypher.dasi.metier.modele.Employe;
import fr.cypher.dasi.metier.modele.Medium;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;

import java.util.Arrays;
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
}
