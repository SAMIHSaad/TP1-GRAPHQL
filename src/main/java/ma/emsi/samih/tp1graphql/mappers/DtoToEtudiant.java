package ma.emsi.samih.tp1graphql.mappers;

import ma.emsi.samih.tp1graphql.model.Centre;
import ma.emsi.samih.tp1graphql.model.Etudiant;
import ma.emsi.samih.tp1graphql.dto.EtudiantDTO;
import ma.emsi.samih.tp1graphql.repositories.CentreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoToEtudiant {
    @Autowired
    CentreRepository centreRepository;

    public void toEtudiant(Etudiant et, EtudiantDTO dto) {
        Centre centre = centreRepository.findById(dto.centreId()).orElse(null);
        if (centre != null) {
            BeanUtils.copyProperties(dto, et);
            et.setCentre(centre);
        }
    }
}
