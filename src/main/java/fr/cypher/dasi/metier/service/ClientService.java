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
    
    public boolean inscrireClient(Client client) {
        boolean result = false;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDao.creerClient(client);
            JpaUtil.validerTransaction();
            Message.envoyerMail("eric.guerin@67.fr", client.getMail(), "Bvn dans le clan", "<h1>SIX SEVEEEEEEEEEEN</h1>");
            result = true;
        } catch (Exception e) {
            System.err.println("bug");
            e.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return result;
    }
    
    public List<Client> listerClients() {
        try {
            JpaUtil.creerContextePersistance();
            return clientDao.attrapezLesTous();
        } catch (Exception e) {
            System.err.println("bug");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }
}
