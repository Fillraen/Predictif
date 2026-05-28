package fr.cypher.dasi.dao;

import fr.cypher.dasi.metier.modele.Utilisateur;
import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.TypedQuery;
import java.util.List;

public class UtilisateurDAO {

    /**
     * Retourne tous les utilisateurs en appliquant uniquement les filtres non-null.
     * Passer null pour ignorer un filtre.
     */
    public List<Utilisateur> getAllWithFilter(String nom, String prenom, String mail, Genre genre) {

        StringBuilder jpql = new StringBuilder("SELECT u FROM Utilisateur u WHERE 1=1");

        if (nom    != null) jpql.append(" AND u.nom = :nom");
        if (prenom != null) jpql.append(" AND u.prenom = :prenom");
        if (mail   != null) jpql.append(" AND u.mail = :mail");
        if (genre  != null) jpql.append(" AND u.genre = :genre");

        TypedQuery<Utilisateur> query = JpaUtil.obtenirContextePersistance()
                .createQuery(jpql.toString(), Utilisateur.class);

        if (nom    != null) query.setParameter("nom",    nom);
        if (prenom != null) query.setParameter("prenom", prenom);
        if (mail   != null) query.setParameter("mail",   mail);
        if (genre  != null) query.setParameter("genre",  genre);

        return query.getResultList();
    }
}
