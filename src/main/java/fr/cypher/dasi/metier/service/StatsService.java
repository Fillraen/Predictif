package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.ClientDAO;
import fr.cypher.dasi.dao.ConsultationDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.metier.modele.stat.StatDepartement;
import fr.cypher.dasi.metier.modele.stat.StatEmploye;
import fr.cypher.dasi.metier.modele.stat.StatMedium;

import java.util.Collections;
import java.util.List;

public class StatsService {

    private final ConsultationDAO consultationDAO = new ConsultationDAO();
    private final ClientDAO clientDAO = new ClientDAO();

    public List<StatMedium> listerNombreConsultationsParMedium() {
        try {
            JpaUtil.creerContextePersistance();
            return consultationDAO.getNombreConsultationsParMedium();
        } catch (Exception e) {
            System.err.println("[StatsService] Erreur listerNombreConsultationsParMedium : " + e.getMessage());
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return Collections.emptyList();
    }

    public List<StatEmploye> listerRepartitionClientParEmploye() {
        try {
            JpaUtil.creerContextePersistance();
            return consultationDAO.getNombreClientsParEmploye();
        } catch (Exception e) {
            System.err.println("[StatsService] Erreur listerRepartitionClientParEmploye : " + e.getMessage());
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return Collections.emptyList();
    }

    public List<StatMedium> listerMediumsPopulaires(int nbMediums) {
        try {
            JpaUtil.creerContextePersistance();
            return consultationDAO.getMediumsPopulaires(nbMediums);
        } catch (Exception e) {
            System.err.println("[StatsService] Erreur listerMediumsPopulaires : " + e.getMessage());
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return Collections.emptyList();
    }

    public List<StatDepartement> listerRepartitionGeographiqueClients() {
        try {
            JpaUtil.creerContextePersistance();
            return clientDAO.getRepartitionGeographique();
        } catch (Exception e) {
            System.err.println("[StatsService] Erreur listerRepartitionGeographiqueClients : " + e.getMessage());
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return Collections.emptyList();
    }
}
