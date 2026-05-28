package fr.cypher.dasi.metier.modele;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String commentaire;

    private LocalDate date;

    @Column(nullable = false)
    private boolean estTermine = false;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Employe employe;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Medium medium;

    public Consultation(String commentaire, LocalDate date, boolean estTermine, Client client, Employe employe, Medium medium) {
        this.commentaire = commentaire;
        this.date = date;
        this.estTermine = estTermine;
        this.client = client;
        this.employe = employe;
        this.medium = medium;
        client.addConsultation(this);
        employe.addConsultation(this);
        medium.addConsultation(this);
    }

    public Consultation(Client client, Employe employe, Medium medium) {
        this.client = client;
        this.employe = employe;
        this.medium = medium;
        client.addConsultation(this);
        employe.addConsultation(this);
        medium.addConsultation(this);
    }

    public Consultation(String commentaire, LocalDate date, boolean estTermine) {
        this.commentaire = commentaire;
        this.date = date;
        this.estTermine = estTermine;
    }

    public Consultation() {}

    public Long getId() {
        return id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isEstTermine() {
        return estTermine;
    }

    public void setEstTermine(boolean estTermine) {
        this.estTermine = estTermine;
    }

    public Client getClient() {
        return client;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", commentaire='" + commentaire + '\'' +
                ", date=" + date +
                ", estTermine=" + estTermine +
                ", client=" + client +
                ", employe=" + employe +
                ", medium=" + medium +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Consultation that = (Consultation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
