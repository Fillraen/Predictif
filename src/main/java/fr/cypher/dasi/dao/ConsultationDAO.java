package fr.cypher.dasi.dao;

import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.TypedQuery;
import java.util.List;

public class ConsultationDAO {
    public void creerConsultation(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
}
