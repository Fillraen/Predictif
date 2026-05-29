package fr.cypher.dasi.dao;

import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;

import javax.persistence.TypedQuery;
import java.util.List;

public class ConsultationDAO {
    public void creerConsultation(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }

    public List<Medium> getMediums() {
        TypedQuery<Medium> mediums = JpaUtil.obtenirContextePersistance().createQuery("SELECT m FROM Medium m", Medium.class);
        return mediums.getResultList();
    }

    public Employe getAvailableEmploye(Genre genre) {
        TypedQuery<Employe> employeTypedQuery = JpaUtil
                .obtenirContextePersistance()
                .createQuery("""
                    SELECT e
                    FROM Employe e
                    WHERE e.estDisponible = true
                        AND e.genre = :genre
                    ORDER BY (
                        SELECT count(c)
                        FROM Consultation c
                        WHERE c.employe = e
                    )
                """, Employe.class);
        employeTypedQuery.setParameter("genre", genre);
        employeTypedQuery.setMaxResults(1);
        return employeTypedQuery.getSingleResult();
    }
}
