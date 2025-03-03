package aston.lab.clientserver.service.converter;

import aston.lab.clientserver.data.model.BusinessVolume;
import aston.lab.clientserver.data.model.Client;
import aston.lab.clientserver.data.model.ClientRepresentativ;
import aston.lab.clientserver.data.model.FormOwnership;
import aston.lab.clientserver.data.model.Okved;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-03T14:14:52+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.12 (Oracle Corporation)"
)
@Component
public class ClientConverterImpl implements ClientConverter {

    @Override
    public ClientFindByInnAndOgrnResponseDto clientFindByInnAndOgrnResponseDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientFindByInnAndOgrnResponseDto.ClientFindByInnAndOgrnResponseDtoBuilder clientFindByInnAndOgrnResponseDto = ClientFindByInnAndOgrnResponseDto.builder();

        clientFindByInnAndOgrnResponseDto.formOwnershipId( clientFormOwnershipIdId( client ) );
        clientFindByInnAndOgrnResponseDto.okvedId( clientOkvedIdId( client ) );
        clientFindByInnAndOgrnResponseDto.businessVolId( clientBusinessVolIdId( client ) );
        clientFindByInnAndOgrnResponseDto.clientRepresentativId( clientClientRepresentativIdId( client ) );
        clientFindByInnAndOgrnResponseDto.telNumber( mapTelNumbers( client.getTelNumbers() ) );
        clientFindByInnAndOgrnResponseDto.fullNameClient( client.getFullNameClient() );
        clientFindByInnAndOgrnResponseDto.inn( client.getInn() );
        clientFindByInnAndOgrnResponseDto.ogrn( client.getOgrn() );
        clientFindByInnAndOgrnResponseDto.nameClient( client.getNameClient() );
        clientFindByInnAndOgrnResponseDto.addressLegal( client.getAddressLegal() );
        clientFindByInnAndOgrnResponseDto.revenue( client.getRevenue() );
        clientFindByInnAndOgrnResponseDto.curAccId( client.getCurAccId() );
        clientFindByInnAndOgrnResponseDto.managerId( client.getManagerId() );
        clientFindByInnAndOgrnResponseDto.isActive( client.getIsActive() );

        return clientFindByInnAndOgrnResponseDto.build();
    }

    private UUID clientFormOwnershipIdId(Client client) {
        if ( client == null ) {
            return null;
        }
        FormOwnership formOwnershipId = client.getFormOwnershipId();
        if ( formOwnershipId == null ) {
            return null;
        }
        UUID id = formOwnershipId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID clientOkvedIdId(Client client) {
        if ( client == null ) {
            return null;
        }
        Okved okvedId = client.getOkvedId();
        if ( okvedId == null ) {
            return null;
        }
        UUID id = okvedId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID clientBusinessVolIdId(Client client) {
        if ( client == null ) {
            return null;
        }
        BusinessVolume businessVolId = client.getBusinessVolId();
        if ( businessVolId == null ) {
            return null;
        }
        UUID id = businessVolId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID clientClientRepresentativIdId(Client client) {
        if ( client == null ) {
            return null;
        }
        ClientRepresentativ clientRepresentativId = client.getClientRepresentativId();
        if ( clientRepresentativId == null ) {
            return null;
        }
        UUID id = clientRepresentativId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
