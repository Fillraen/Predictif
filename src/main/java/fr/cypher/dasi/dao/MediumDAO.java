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
public class MediumDAO {
    public void creerMedium(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }

    public List<Medium> getMediums() {
        TypedQuery<Medium> mediums = JpaUtil.obtenirContextePersistance().createQuery("SELECT m FROM Medium m", Medium.class);
        return mediums.getResultList();
    }

    public List<Medium> getMediums(TypeMedium type) {
        Class<?> typeClass = switch (type) {
            case ASTROLOGUE -> Astrologue.class;
            case CARTOMANCIEN -> Cartomancien.class;
            case SPIRITE -> Spirite.class;
        };
        TypedQuery<Medium> mediums = JpaUtil.obtenirContextePersistance().createQuery("SELECT m FROM Medium m WHERE TYPE(m) = :type", Medium.class);
        mediums.setParameter("type", typeClass);
        return mediums.getResultList();
    }
}
