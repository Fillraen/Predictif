/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.ConsultationDAO;
import fr.cypher.dasi.dao.EmployeDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.Client;
import fr.cypher.dasi.metier.modele.Consultation;
import fr.cypher.dasi.metier.modele.Employe;
import fr.cypher.dasi.metier.modele.Medium;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;
import fr.cypher.dasi.util.Message;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author clemaire
 */
public class ConsultationService {
    private final ConsultationDAO consultationDAO = new ConsultationDAO();
    private final EmployeDAO employeDAO = new EmployeDAO();

    public boolean demanderConsultation(Client client, Medium medium) {
        boolean result = false;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            Employe employe = consultationDAO.getAvailableEmploye(medium.getGenre());
            consultationDAO.creerConsultation(new Consultation(client, employe, medium));
            employe.setEstDisponible(false);
            employeDAO.modifierEmploye(employe);

            JpaUtil.validerTransaction();
            Message.envoyerNotification(employe.getTelephone(),
                    "Bonjour " +
                    employe.getPrenom() +
                    ". Consultation requise pour " +
                    client.getPronomNomComplet() +
                    ". Médium à incarner : " +
                    medium.getDenomination()
            );
            result = true;
        } catch (NoResultException e) {
            System.out.println("Aucune employé disponible.");
            Message.envoyerNotification(client.getTelephone(),
                "Bonjour " +
                client.getPronomNomComplet() +
                ". Échec de demande de consultation." +
                medium.getDenomination() +
                " n'est pas disponible."
            );
            JpaUtil.annulerTransaction();
        } catch (Exception e) {
            System.err.println("Problème lors de la demande de consultation");
            Message.envoyerNotification(client.getTelephone(),
                "Bonjour " +
                client.getPronomNomComplet() +
                ". Échec de demande de consultation. Veuillez réessayer plus tard."
            );
            e.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }

    public List<Consultation> consulterHistoriqueConsultations(Client client) {
        try {
            JpaUtil.creerContextePersistance();
            return consultationDAO.getConsultations(client);
        } catch (NoResultException e) {
            System.out.println("Aucune consultation.");
            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Impossible de lister l'historique de consultations");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }

    public Consultation consulterConsultationAffectee(Employe employe) {
        try {
            JpaUtil.creerContextePersistance();
            return consultationDAO.getConsultationAffectee(employe);
        } catch (NoResultException e) {
            System.out.println("Aucune consultation.");
        } catch (Exception e) {
            System.err.println("Impossible de consulter la consultation affectée.");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }
}
