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

    public List<Medium> getMediums(TypeMedium type) {
        if (type == null)
            return JpaUtil.obtenirContextePersistance()
                    .createQuery("SELECT m FROM Medium m", Medium.class)
                    .getResultList();
        Class<?> typeClass = switch (type) {
            case ASTROLOGUE   -> Astrologue.class;
            case CARTOMANCIEN -> Cartomancien.class;
            case SPIRITE      -> Spirite.class;
        };
        return JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT m FROM Medium m WHERE TYPE(m) = :type", Medium.class)
                .setParameter("type", typeClass)
                .getResultList();
    }

    public Medium getParId(Long id) {
        TypedQuery<Medium> medium = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Medium c WHERE c.id = :id", Medium.class);
        medium.setParameter("id", id);
        return medium.getSingleResult();
    }
}
