package aston.lab.clientserver.mapper;

import aston.lab.clientserver.dto.ClientDto;
import aston.lab.clientserver.entity.BusinessVolume;
import aston.lab.clientserver.entity.Client;
import aston.lab.clientserver.entity.ClientRepresentativ;
import aston.lab.clientserver.entity.FormOwnership;
import aston.lab.clientserver.entity.Okved;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-13T15:40:16+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public Client toEntity(ClientDto dto) {
        if ( dto == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.formOwnershipId( clientDtoToFormOwnership( dto ) );
        client.okvedId( clientDtoToOkved( dto ) );
        client.businessVolId( clientDtoToBusinessVolume( dto ) );
        client.clientRepresentativId( clientDtoToClientRepresentativ( dto ) );
        if ( dto.getInn() != null ) {
            client.inn( BigDecimal.valueOf( dto.getInn() ) );
        }
        if ( dto.getOgrn() != null ) {
            client.ogrn( BigDecimal.valueOf( dto.getOgrn() ) );
        }
        client.fullNameClient( dto.getFullNameClient() );
        client.nameClient( dto.getNameClient() );
        if ( dto.getCurAccId() != null ) {
            client.curAccId( UUID.fromString( dto.getCurAccId() ) );
        }
        client.addressLegal( dto.getAddressLegal() );
        client.managerId( dto.getManagerId() );
        if ( dto.getRevenue() != null ) {
            client.revenue( BigDecimal.valueOf( dto.getRevenue() ) );
        }

        return client.build();
    }

    @Override
    public ClientDto toDto(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDto.ClientDtoBuilder clientDto = ClientDto.builder();

        UUID id = clientFormOwnershipIdId( client );
        if ( id != null ) {
            clientDto.formOwnershipId( id.toString() );
        }
        clientDto.okvedId( clientOkvedIdId( client ) );
        clientDto.businessVolId( clientBusinessVolIdId( client ) );
        clientDto.clientRepresentativId( clientClientRepresentativIdId( client ) );
        if ( client.getInn() != null ) {
            clientDto.inn( client.getInn().intValue() );
        }
        if ( client.getOgrn() != null ) {
            clientDto.ogrn( client.getOgrn().intValue() );
        }
        clientDto.fullNameClient( client.getFullNameClient() );
        clientDto.nameClient( client.getNameClient() );
        if ( client.getCurAccId() != null ) {
            clientDto.curAccId( client.getCurAccId().toString() );
        }
        clientDto.addressLegal( client.getAddressLegal() );
        clientDto.managerId( client.getManagerId() );
        if ( client.getRevenue() != null ) {
            clientDto.revenue( client.getRevenue().doubleValue() );
        }

        return clientDto.build();
    }

    protected FormOwnership clientDtoToFormOwnership(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        FormOwnership.FormOwnershipBuilder formOwnership = FormOwnership.builder();

        if ( clientDto.getFormOwnershipId() != null ) {
            formOwnership.id( UUID.fromString( clientDto.getFormOwnershipId() ) );
        }

        return formOwnership.build();
    }

    protected Okved clientDtoToOkved(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        Okved.OkvedBuilder okved = Okved.builder();

        okved.id( clientDto.getOkvedId() );

        return okved.build();
    }

    protected BusinessVolume clientDtoToBusinessVolume(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        BusinessVolume.BusinessVolumeBuilder businessVolume = BusinessVolume.builder();

        businessVolume.id( clientDto.getBusinessVolId() );

        return businessVolume.build();
    }

    protected ClientRepresentativ clientDtoToClientRepresentativ(ClientDto clientDto) {
        if ( clientDto == null ) {
            return null;
        }

        ClientRepresentativ.ClientRepresentativBuilder clientRepresentativ = ClientRepresentativ.builder();

        clientRepresentativ.id( clientDto.getClientRepresentativId() );

        return clientRepresentativ.build();
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
