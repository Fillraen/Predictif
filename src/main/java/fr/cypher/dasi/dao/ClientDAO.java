/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cypher.dasi.dao;

import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.stat.StatDepartement;

import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author clemaire
 */
public class ClientDAO {
    public void creerClient(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }

    public List<Client> attrapezLesTous() {
        TypedQuery<Client> clients = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Client c", Client.class);
        return clients.getResultList();
    }

    public Client getParId(Long id) {
        TypedQuery<Client> client = JpaUtil.obtenirContextePersistance().createQuery("SELECT c FROM Client c WHERE c.id = :id", Client.class);
        client.setParameter("id", id);
        return client.getSingleResult();
    }

    public List<StatDepartement> getRepartitionGeographique() {
        return JpaUtil.obtenirContextePersistance()
                .createQuery("SELECT NEW fr.cypher.dasi.metier.modele.stat.StatDepartement(c.adresse.codeDepartement, COUNT(c)) FROM Client c GROUP BY c.adresse.codeDepartement", StatDepartement.class)
                .getResultList();
    }
}
