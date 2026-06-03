package fr.cypher.dasi.dao;

import fr.cypher.dasi.metier.modele.Utilisateur;

import javax.persistence.TypedQuery;
import java.util.List;

public class UtilisateurDAO {

    /**
     * Retourne tous les utilisateurs en appliquant uniquement les filtres non-null.
     * Passer null pour ignorer un filtre.
     */
    public List<Utilisateur> getAllByMail(String mail) {
        TypedQuery<Utilisateur> query = JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT u FROM Utilisateur u WHERE u.mail = :mail", Utilisateur.class);
        query.setParameter("mail",   mail);
        return query.getResultList();
    }

    public Utilisateur getParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Utilisateur.class, id);
    }
}
