package fr.cypher.dasi.metier.modele.stat;

public class StatDepartement {
    private final String codeDepartement;
    private final Long nombre;

    public StatDepartement(String codeDepartement, Long nombre) {
        this.codeDepartement = codeDepartement;
        this.nombre = nombre;
    }

    public String getCodeDepartement() {
        return codeDepartement;
    }

    public Long getNombre() {
        return nombre;
    }
}
