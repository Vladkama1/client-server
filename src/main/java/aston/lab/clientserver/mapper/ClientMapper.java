package aston.lab.clientserver.mapper;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    @Mapping(source = "formOwnershipId", target = "formOwnershipId.id")
    @Mapping(source = "okvedId", target = "okvedId.id")
    @Mapping(source = "businessVolId", target = "businessVolId.id")
    @Mapping(source = "clientRepresentativId", target = "clientRepresentativId.id")
    Client toEntity(RequestClientDto dto);


    @Mapping(source = "client.formOwnershipId.id", target = "formOwnershipId")
    @Mapping(source = "client.okvedId.id", target = "okvedId")
    @Mapping(source = "client.businessVolId.id", target = "businessVolId")
    @Mapping(source = "client.clientRepresentativId.id", target = "clientRepresentativId")
    ResponseClientDto toDto(Client client);
}
