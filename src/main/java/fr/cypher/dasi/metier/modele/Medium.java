package fr.cypher.dasi.metier.modele;

import fr.cypher.dasi.metier.modele.enums.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Medium {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Nom du médium. Exemple : Professeur Tran
    private String denomination;

    // This is kinda risky, if we rename any enum value, this will "corrupt" the database
    // Indeed, data will be based on enum value names.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    // Phrase d'accroche. Exemple : Votre avenir est devant vous : regardons-le ensemble !
    private String presentation;

    @OneToMany(mappedBy = "medium")
    private final List<Consultation> consultations = new ArrayList<>();

    public Medium(String denomination, Genre genre, String presentation) {
        this.denomination = denomination;
        this.genre = genre;
        this.presentation = presentation;
    }

    public Medium() { }

    public Long getId() {
        return id;
    }

    public String getDenomination() {
        return denomination;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public List<Consultation> getConsultations() {
        return this.consultations;
    }

    public void addConsultation(Consultation consultation) {
        this.consultations.add(consultation);
    }

    @Override
    public String toString() {
        return "Medium{" +
                "id=" + id +
                ", denomination='" + denomination + '\'' +
                ", genre=" + genre +
                ", presentation='" + presentation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Medium medium = (Medium) o;
        return Objects.equals(id, medium.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
