package ma.emsi.samih.tp1graphql.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.samih.tp1graphql.enums.Genre;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom_etudiant", nullable=false)
    private String nom;
    @Column(name="prenom_etudiant")
    private String prenom;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @ManyToOne
    private Centre centre;
}
