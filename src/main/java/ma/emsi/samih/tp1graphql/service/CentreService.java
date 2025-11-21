package ma.emsi.samih.tp1graphql.service;

import ma.emsi.samih.tp1graphql.model.Centre;
import ma.emsi.samih.tp1graphql.repositories.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentreService {
    @Autowired
    CentreRepository centreRepository;

    public List<Centre> getCentres() {
        return centreRepository.findAll();
    }

    public Centre getCentre(Long id) {
        return centreRepository.findById(id).orElse(null);
    }
}
