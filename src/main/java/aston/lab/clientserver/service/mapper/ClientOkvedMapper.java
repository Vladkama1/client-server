package aston.lab.clientserver.service.mapper;

import aston.lab.clientserver.data.model.ClientOkveds;
import aston.lab.clientserver.dto.ClientOkvedDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientOkvedMapper {

    List<ClientOkvedDTO> toListDto(List<ClientOkveds> list);

}
