package fr.cypher.dasi.dao;

import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.TypedQuery;
import java.util.List;

public class ConsultationDAO {
    public void creerConsultation(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
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

    public List<Consultation> getConsultations(Client client) {
        TypedQuery<Consultation> consultations = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.client = :client", Consultation.class);
        consultations.setParameter("client", client);
        return consultations.getResultList();
    }

    public Consultation getParId(Long id) {
        TypedQuery<Consultation> consultation = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.id = :id", Consultation.class);
        consultation.setParameter("id", id);
        return consultation.getSingleResult();
    }

    public Consultation getConsultationAffectee(Employe employe) {
        TypedQuery<Consultation> consultations = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Consultation c WHERE c.employe = :employe AND c.estTermine = false", Consultation.class);
        consultations.setParameter("employe", employe);
        return consultations.getSingleResult();
    }

    public void modifierConsultation(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().merge(consultation);
    }
}
