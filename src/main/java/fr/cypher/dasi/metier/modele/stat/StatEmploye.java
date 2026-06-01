package fr.cypher.dasi.metier.modele.stat;

import fr.cypher.dasi.metier.modele.Employe;

public class StatEmploye {
    private final Employe employe;
    private final Long nombre;

    public StatEmploye(Employe employe, Long nombre) {
        this.employe = employe;
        this.nombre = nombre;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Long getNombre() {
        return nombre;
    }
}