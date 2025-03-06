package aston.lab.clientserver.service.converter;

import aston.lab.clientserver.data.model.BusinessVolume;
import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.model.ClientRepresentativ;
import aston.lab.clientserver.data.model.FormOwnership;
import aston.lab.clientserver.data.model.Okved;
import aston.lab.clientserver.data.model.TelNumber;

import aston.lab.clientserver.dto.request.ClientRequestDto;
import aston.lab.clientserver.dto.response.ClientResponseDto;
import org.mapstruct.MappingTarget;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientConverter {


    @Mapping(target = "telNumbers", ignore = true) // Обрабатывается отдельно
    Client clientRequestDtoToClient(ClientRequestDto clientRequestDto);

    @Mapping(target = "formOwnershipId", source = "formOwnershipId.id")
    @Mapping(target = "okvedId", source = "okvedId.id")
    @Mapping(target = "businessVolId", source = "businessVolId.id")
    @Mapping(target = "clientRepresentativId", source = "clientRepresentativId.id")
    @Mapping(target = "telNumber", source = "telNumbers")
    ClientResponseDto clientToClientResponseDto(Client client);

    @Mapping(target = "id", ignore = true) // Игнорируем ID, т.к. это существующая сущность
    @Mapping(target = "telNumbers", ignore = true)
    void updateClientFromDto(ClientRequestDto clientRequestDto, @MappingTarget Client client);


    @Mapping(target = "formOwnershipId", source = "client.formOwnershipId.id")
    @Mapping(target = "okvedId", source = "client.okvedId.id")
    @Mapping(target = "businessVolId", source = "client.businessVolId.id")
    @Mapping(target = "clientRepresentativId", source = "client.clientRepresentativId.id")
    @Mapping(target = "telNumber", source = "telNumbers")
    ClientFindByInnAndOgrnResponseDto clientFindByInnAndOgrnResponseDto (Client client);

    // Кастомные методы преобразования

    default FormOwnership mapFormOwnershipId(UUID id) {
        return FormOwnership.builder().id(id).build();
    }

    default Okved mapOkved(UUID id) {
        return Okved.builder().id(id).build();
    }


    default ClientRepresentativ mapClientRepresentativId(UUID id) {
        return ClientRepresentativ.builder().id(id).build();
    }

    default BusinessVolume mapBusinessVolId(UUID id) {
        return BusinessVolume.builder().id(id).build();
    }

    default List<String> mapTelNumbers(List<TelNumber> numbers) {
        return numbers.stream()
                .map(TelNumber::getNumber)
                .collect(Collectors.toList());
    }

}
