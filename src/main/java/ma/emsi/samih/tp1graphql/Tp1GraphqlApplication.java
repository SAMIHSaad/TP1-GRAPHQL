package ma.emsi.samih.tp1graphql;

import ma.emsi.samih.tp1graphql.enums.Genre;
import ma.emsi.samih.tp1graphql.model.Centre;
import ma.emsi.samih.tp1graphql.model.Etudiant;
import ma.emsi.samih.tp1graphql.repositories.CentreRepository;
import ma.emsi.samih.tp1graphql.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tp1GraphqlApplication implements CommandLineRunner {

    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private CentreRepository centreRepository;

    public static void main(String[] args) {
        SpringApplication.run(Tp1GraphqlApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Centre centre1 = Centre.builder().nom("Maarif").adresse("Biranzarane").build();
        centreRepository.save(centre1);

        Centre centre2 = Centre.builder().nom("Oranges").adresse("Oulfa").build();
        centreRepository.save(centre2);

        Etudiant et1 = Etudiant.builder().nom("Adnani").prenom("Brahim").genre(Genre.Homme).centre(centre1).build();
        etudiantRepository.save(et1);

        Etudiant et2 = Etudiant.builder().nom("Alami").prenom("Ilham").genre(Genre.Femme).centre(centre1).build();
        etudiantRepository.save(et2);

        Etudiant et3 = Etudiant.builder().nom("Fadli").prenom("Moad").genre(Genre.Homme).centre(centre2).build();
        etudiantRepository.save(et3);

        Etudiant et4 = Etudiant.builder().nom("Hamdani").prenom("Adil").genre(Genre.Homme).centre(centre2).build();
        etudiantRepository.save(et4);
    }
}
