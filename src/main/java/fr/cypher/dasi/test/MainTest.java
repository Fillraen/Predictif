package fr.cypher.dasi.test;

import fr.cypher.dasi.metier.modele.Medium;
import fr.cypher.dasi.metier.modele.enums.TypeMedium;
import fr.cypher.dasi.metier.service.InitService;
import fr.cypher.dasi.metier.service.MediumService;

import java.util.List;

public class MainTest {
    static void mediums() {
        MediumService mediumService = new MediumService();
        InitService initService = new InitService();
        initService.init();
        List<Medium> mediums = mediumService.listerMediums();
        for (Medium medium : mediums) {
            System.out.println(medium);
        }
        System.out.println("--------");
        List<Medium> spirites = mediumService.listerMediums(TypeMedium.SPIRITE);
        for (Medium spirite : spirites) {
            System.out.println(spirite);
        }
        System.out.println("--------");
        List<TypeMedium> types = mediumService.listerTypeMediums();
        for (TypeMedium type : types) {
            System.out.println(type);
        }
    }

    public static void executer() {
        mediums();
    }
}
