/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.EmployeDAO;
import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.dao.MediumDAO;
import fr.cypher.dasi.metier.modele.*;
import fr.cypher.dasi.metier.modele.enums.Genre;

/**
 *
 * @author clemaire
 */
public class InitService {
    private final MediumDAO mediumDAO = new MediumDAO();
    private final EmployeDAO employeDAO = new EmployeDAO();

    public boolean init() {
        boolean result = false;
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            initMediums();
            initEmploye();
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

    private void initEmploye() {
        employeDAO.creerEmploye(
            new Employe(
                "camille.martin@predictif.com",
                "Camille",
                "Martin",
                "JeSuisLaMeilleureEmploye(non)!",
                "0655447788",
                Genre.FEMME
            )
        );
        employeDAO.creerEmploye(
            new Employe(
                "brice.nice@predictif.com",
                "Brice",
                "Nice",
                "JT'aiCassé(non)!",
                "0677667766",
                Genre.HOMME
            )
        );
        employeDAO.creerEmploye(
            new Employe(
                "emmanuel.macaron@predictif.com",
                "Emmanuel",
                "Macaron",
                "NousSommesEnGuerreFOR_SURE!",
                "0142928100",
                Genre.HOMME
            )
        );
        employeDAO.creerEmploye(
            new Employe(
                "marine.lepen@predictif.com",
                "Marine",
                "Le Pen",
                "IlsSontDansVillesDansLesCampagnes...SUR_LES_RESEAUX_SOCIAUX!",
                "0112233221",
                Genre.FEMME
            )
        );
        employeDAO.creerEmploye(
            new Employe(
                "jean-luc.melenchon@predictif.com",
                "Jean-Luc",
                "Mélenchon",
                "LA_REPUBLIQUE_C'EST_MOI!",
                "0144335522",
                Genre.HOMME
            )
        );
        employeDAO.creerEmploye(
            new Employe(
                "geraldine.tulipe@predictif.com",
                "Géraldine",
                "Tulipe",
                "JeSuIsPaRtOuT...&é'(§è!çà",
                "0133445566",
                Genre.NON_SPECIFIE
            )
        );
        employeDAO.creerEmploye(
            new Employe(
                "matthieu.maranzana@predictif.com",
                "Matthieu",
                "Maranzana",
                "DÉclaration et DÉfinition...",
                "0955664477",
                Genre.HOMME
            )
        );
        employeDAO.creerEmploye(
            new Employe(
                "frederic.prost@predictif.com",
                "Frederic",
                "Prost",
                "function fun() {static int = 40; i--;} for (fun();fun();fun()) fun(); Que fait ce programme ?",
                "0877663344",
                Genre.HOMME
            )
        );
    }

    private void initMediums() {
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
    }
}
