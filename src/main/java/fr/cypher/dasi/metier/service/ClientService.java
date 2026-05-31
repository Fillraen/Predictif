/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.ClientDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.util.Message;
import java.util.List;

/**
 *
 * @author clemaire
 */
public class ClientService {
    private final ClientDAO clientDao = new ClientDAO();

    public List<Client> listerClients() {
        try {
            JpaUtil.creerContextePersistance();
            return clientDao.attrapezLesTous();
        } catch (Exception e) {
            System.err.println("Problème lors de la récupération des clients");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }
}
