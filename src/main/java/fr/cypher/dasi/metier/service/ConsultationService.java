/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.ConsultationDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Consultation;
import fr.cypher.dasi.metier.modele.Employe;
import fr.cypher.dasi.metier.modele.Medium;
import fr.cypher.dasi.util.Message;

import java.util.List;

/**
 *
 * @author clemaire
 */
public class ConsultationService {
    private final ConsultationDAO consultationDAO = new ConsultationDAO();

    public boolean demanderConsultation(Client client, Medium medium) {
        boolean result = false;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Employe employe = null; // TODO
            consultationDAO.creerConsultation(new Consultation(client, employe, medium));
            JpaUtil.validerTransaction();
            Message.envoyerNotification(client.getTelephone(), "Nouvelle consultation TODO");
            result = true;
        } catch (Exception e) {
            System.err.println("Problème lors de la demande de consultation");
            e.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
}
