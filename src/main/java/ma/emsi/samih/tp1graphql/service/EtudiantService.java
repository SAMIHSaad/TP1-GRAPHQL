package ma.emsi.samih.tp1graphql.service;

import ma.emsi.samih.tp1graphql.model.Etudiant;
import ma.emsi.samih.tp1graphql.dto.EtudiantDTO;
import ma.emsi.samih.tp1graphql.mappers.DtoToEtudiant;
import ma.emsi.samih.tp1graphql.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    DtoToEtudiant dtoToEtudiant;
    @Autowired
    EtudiantRepository etudiantRepository;

    private final Sinks.Many<Etudiant> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<String> sinkRemoved = Sinks.many().multicast().onBackpressureBuffer();

    public List<Etudiant> getStudents() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiant(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant addEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        dtoToEtudiant.toEtudiant(etudiant, etudiantDTO);
        etudiantRepository.save(etudiant);
        sink.tryEmitNext(etudiant);
        return etudiant;
    }

    public Etudiant updateEtudiant(Long id, EtudiantDTO etudiantDTO) {
        if (etudiantRepository.findById(id).isPresent()) {
            Etudiant etudiant = etudiantRepository.findById(id).get();
            dtoToEtudiant.toEtudiant(etudiant, etudiantDTO);
            return etudiantRepository.save(etudiant);
        }
        return null;
    }

    public String deleteEtudiant(Long id) {
        if (etudiantRepository.findById(id).isPresent()) {
            Etudiant et = etudiantRepository.findById(id).get();
            etudiantRepository.deleteById(id);
            String msg = String.format("l'étudiant %s %s vient de quitter l'école !", et.getNom(), et.getPrenom());
            sinkRemoved.tryEmitNext(msg);
            return String.format("l'étudiant %s est bien supprimé !", id);
        }
        return String.format("l'étudiant %s n'existe pas !", id);
    }

    public Flux<Etudiant> getEtudiantAdded() {
        return sink.asFlux();
    }

    public Flux<String> etudiantRemoved() {
        return sinkSuppression.asFlux();
    }
}
