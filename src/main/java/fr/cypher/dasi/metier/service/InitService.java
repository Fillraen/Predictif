/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.ClientDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.dao.MediumDAO;
import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.enums.Genre;
import fr.cypher.dasi.util.Message;

import java.util.List;

/**
 *
 * @author clemaire
 */
public class InitService {
    private final MediumDAO mediumDAO = new MediumDAO();
    
    public boolean init() {
        boolean result = false;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            mediumDAO.creerMedium(
                    new Spirite(
                            "Gwenaëlle",
                            Genre.FEMME,
                            "Spécialiste des grandes conversations au-delà de TOUTES les frontières",
                            "Boule de cristal"
                    )
            );
            mediumDAO.creerMedium(
                    new Spirite(
                            "Professeur Tran",
                            Genre.HOMME,
                            "Votre avenir est devant vous : regardons-le ensemble !",
                            "Marc de café, boule de cristal, oreilles de lapin"
                    )
            );
            mediumDAO.creerMedium(
                    new Cartomancien(
                            "Mme Irma",
                            Genre.FEMME,
                            "Comprenez votre entourage grâce à mes cartes ! Résultats rapides."
                    )
            );
            mediumDAO.creerMedium(
                    new Cartomancien(
                            "Endora",
                            Genre.FEMME,
                            "Mes cartes répondront à toutes vos questions personnelles."
                    )
            );
            mediumDAO.creerMedium(
                    new Astrologue(
                            "Serena",
                            Genre.FEMME,
                            "Basée à Campigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.",
                            "École Normale Supérieure d'Astrologie (ENS-Astro)",
                            2006
                    )
            );
            mediumDAO.creerMedium(
                    new Astrologue(
                            "Mr M",
                            Genre.HOMME,
                            "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter !",
                            "Instituts des Nouveaux Savoirs Astrologiques",
                            2010
                    )
            );
            JpaUtil.validerTransaction();
            result = true;
        } catch (Exception e) {
            System.err.println("Problème lors de l'init");
            e.printStackTrace();
            JpaUtil.annulerTransaction();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        
        return result;
    }
}
