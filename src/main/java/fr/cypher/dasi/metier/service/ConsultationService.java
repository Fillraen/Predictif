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
import fr.cypher.dasi.util.Message;

import javax.persistence.NoResultException;
import java.time.format.DateTimeFormatter;
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
            // TODO : décommenter pour limiter à 1 consultation en cours par client
            // boolean dejaEnCours = consultationDAO.hasConsultationEnCours(client);
            // if (dejaEnCours) throw new IllegalStateException("Le client a déjà une consultation en cours.");
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

    public boolean declarerPret(Consultation consultation) {
        boolean result = false;
        try {
            if (consultation == null || consultation.isEstTermine()) {
                System.err.println("Impossible de se déclarer prêt, la consultation est déjà terminée ou null : " + consultation);
                return false;
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH'h'mm");
            Message.envoyerNotification(consultation.getClient().getTelephone(),
                    "Bonjour " +
                            consultation.getClient().getPrenom() +
                            ". J'ai bien reçu votre demande de consultation du " +
                            consultation.getDateTime().format(dateFormatter) +
                            " à " +
                            consultation.getDateTime().format(timeFormatter) +
                            ". Vous pouvez dès maintenant me contacter au " +
                            consultation.getEmploye().getTelephone() +
                            ". À tout de suite ! Médiumiquement vôtre, " +
                            consultation.getMedium().getDenomination()
            );
            result = true;
        } catch (Exception e) {
            System.err.println("Problème pour déclarer prêt pour la consultation : " + consultation);
            e.printStackTrace();
        }
        return result;
    }

    public Consultation recupererConsultationParId(Long id) {
        try {
            JpaUtil.creerContextePersistance();
            return consultationDAO.getParId(id);
        } catch (NoResultException e) {
            System.err.println("[ConsultationService] Consultation introuvable : id=" + id);
        } catch (Exception e) {
            System.err.println("[ConsultationService] Erreur recupererConsultationParId : " + e.getMessage());
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }

    public boolean terminerConsultation(Consultation consultation, String commentaire) {
        boolean result = false;
        try {
            JpaUtil.creerContextePersistance();
            if (consultation == null || consultation.isEstTermine()) {
                System.err.println("Impossible de terminer la consultation, elle est déjà terminée ou null : " + consultation);
                return false;
            }
            JpaUtil.ouvrirTransaction();
            consultation.setEstTermine(true);
            consultation.setCommentaire(commentaire);
            consultationDAO.modifierConsultation(consultation);
            Employe employe = consultation.getEmploye();
            employe.setEstDisponible(true);
            employeDAO.modifierEmploye(employe);
            JpaUtil.validerTransaction();
            result = true;
        } catch (Exception e) {
            System.err.println("Problème pour déclarer prêt pour la consultation : " + consultation);
            e.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
    }
}
