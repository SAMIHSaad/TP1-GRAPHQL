package ma.emsi.samih.tp1graphql.repository;

import ma.emsi.samih.tp1graphql.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
