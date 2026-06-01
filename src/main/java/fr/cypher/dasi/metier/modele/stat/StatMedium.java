package fr.cypher.dasi.metier.modele.stat;

import fr.cypher.dasi.metier.modele.Medium;

public class StatMedium {
    private final Medium medium;
    private final Long nombre;

    public StatMedium(Medium medium, Long nombre) {
        this.medium = medium;
        this.nombre = nombre;
    }

    public Medium getMedium() {
        return medium;
    }

    public Long getNombre() {
        return nombre;
    }
}