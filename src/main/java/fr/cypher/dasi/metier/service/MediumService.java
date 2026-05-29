package fr.cypher.dasi.metier.service;

import fr.cypher.dasi.dao.JpaUtil;
import fr.cypher.dasi.dao.MediumDAO;
import fr.cypher.dasi.metier.modele.Medium;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;

import java.util.Arrays;
import java.util.List;

public class MediumService {
    private final MediumDAO mediumDao = new MediumDAO();

    public List<Medium> listerMediums() {
        try {
            JpaUtil.creerContextePersistance();
            return mediumDao.getMediums();
        } catch (Exception e) {
            System.err.println("Impossible de lister les médiums");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }

    public List<Medium> listerMediums(TypeMedium type) {
        try {
            JpaUtil.creerContextePersistance();
            return mediumDao.getMediums(type);
        } catch (Exception e) {
            System.err.println("Impossible de lister les médiums");
            e.printStackTrace();
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return null;
    }

    public List<TypeMedium> listerTypeMediums() {
        return Arrays.stream(TypeMedium.values()).toList();
    }
}
