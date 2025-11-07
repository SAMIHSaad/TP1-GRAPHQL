package ma.emsi.samih.tp1graphql.dto;

import ma.emsi.samih.tp1graphql.enums.Genre;

public record EtudiantDTO(
        String nom,
        String prenom,
        Genre genre,
        Long centreId
) {
}
