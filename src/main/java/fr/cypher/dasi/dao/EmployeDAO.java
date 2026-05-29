/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cypher.dasi.dao;

import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * @author clemaire
 */
public class EmployeDAO {
    public void creerEmploye(Employe employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }

    public List<Employe> getEmployes() {
        TypedQuery<Employe> employes = JpaUtil.obtenirContextePersistance().createQuery("SELECT e FROM Employe e", Employe.class);
        return employes.getResultList();
    }

    public void modifierEmploye(Employe employe) {
        JpaUtil.obtenirContextePersistance().merge(employe);
    }
}
