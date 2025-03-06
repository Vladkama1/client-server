package aston.lab.clientserver.service.converter;

import aston.lab.clientserver.data.model.FormOwnership;
import aston.lab.clientserver.dto.response.FormOwnershipDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.UUID;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FormOwnershipConverter {

    FormOwnershipDto formOwnershipDto(FormOwnership formOwnership);
    default FormOwnership mapFormOwnershipId(UUID id) {
        return FormOwnership.builder().id(id).build();
    }
}
