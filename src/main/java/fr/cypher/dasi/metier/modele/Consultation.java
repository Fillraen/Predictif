package fr.cypher.dasi.metier.modele;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String commentaire;

    private LocalDateTime dateTime;

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

    public Consultation(String commentaire, LocalDateTime dateTime, boolean estTermine, Client client, Employe employe, Medium medium) {
        this.commentaire = commentaire;
        this.dateTime = dateTime;
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
        this.dateTime = LocalDateTime.now();
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime date) {
        this.dateTime = date;
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
                ", date=" + dateTime +
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
