package br.java.social_network.application.mapper;

public interface IMapper<ENTITY, DTO> {
    ENTITY toEntity(DTO input);
    DTO toDTO(ENTITY input);
}