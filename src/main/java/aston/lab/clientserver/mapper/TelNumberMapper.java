package aston.lab.clientserver.mapper;

import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.model.TelNumber;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TelNumberMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client", source = "client")
    @Mapping(target = "number", source = "number")
    TelNumber toEntity(Client client, String number);

    default List<TelNumber> toListEntity(Client client, List<String> telNumbers) {
        if (telNumbers == null) {
            return Collections.emptyList();
        }
        return telNumbers.stream()
                .map(number -> toEntity(client, number))
                .collect(Collectors.toList());
    }
}
