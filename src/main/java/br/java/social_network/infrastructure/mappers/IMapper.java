package br.java.social_network.infrastructure.mappers;

public interface IMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO input);
    DTO toDTO(ENTITY input);
}