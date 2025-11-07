package ma.emsi.samih.tp1graphql.controller;

import ma.emsi.samih.tp1graphql.dto.EtudiantDTO;
import ma.emsi.samih.tp1graphql.model.Centre;
import ma.emsi.samih.tp1graphql.model.Etudiant;
import ma.emsi.samih.tp1graphql.repository.CentreRepository;
import ma.emsi.samih.tp1graphql.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EtudiantGraphQLController {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private CentreRepository centreRepository;

    @QueryMapping
    public List<Etudiant> findAllEtudiants(){
        return etudiantRepository.findAll();
    }

    @QueryMapping
    public Etudiant getEtudiantById(@Argument Long id){
        return etudiantRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Etudiant %s not found", id)));
    }

    @QueryMapping
    public List<Centre> listCentres(){
        return centreRepository.findAll();
    }

    @QueryMapping
    public Centre getCentreById(@Argument Long id){
        return centreRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Centre %s not found", id)));
    }

    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant){
        Centre centre = centreRepository.findById(etudiant.centreId()).orElse(null);
        Etudiant newEtudiant = new Etudiant();
        newEtudiant.setNom(etudiant.nom());
        newEtudiant.setPrenom(etudiant.prenom());
        newEtudiant.setGenre(etudiant.genre());
        newEtudiant.setCentre(centre);
        return etudiantRepository.save(newEtudiant);
    }

    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id, @Argument EtudiantDTO etudiant){
        Centre centre = centreRepository.findById(etudiant.centreId()).orElse(null);
        Etudiant existingEtudiant = etudiantRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("Etudiant %s not found", id)));
        existingEtudiant.setNom(etudiant.nom());
        existingEtudiant.setPrenom(etudiant.prenom());
        existingEtudiant.setGenre(etudiant.genre());
        existingEtudiant.setCentre(centre);
        return etudiantRepository.save(existingEtudiant);
    }

    @MutationMapping
    public String deleteEtudiant(@Argument Long id){
        etudiantRepository.deleteById(id);
        return "Etudiant with id " + id + " has been deleted";
    }
}
