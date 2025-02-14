package aston.lab.clientserver.mapper;

import aston.lab.clientserver.dto.ClientDto;
import aston.lab.clientserver.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    @Mapping(source = "formOwnershipId", target = "formOwnershipId.id")
    @Mapping(source = "okvedId", target = "okvedId.id")
    @Mapping(source = "businessVolId", target = "businessVolId.id")
    @Mapping(source = "clientRepresentativId", target = "clientRepresentativId.id")
    Client toEntity(ClientDto dto);

    @Mapping(source = "formOwnershipId.id", target = "formOwnershipId")
    @Mapping(source = "okvedId.id", target = "okvedId")
    @Mapping(source = "businessVolId.id", target = "businessVolId")
    @Mapping(source = "clientRepresentativId.id", target = "clientRepresentativId")
    ClientDto toDto(Client client);
}
