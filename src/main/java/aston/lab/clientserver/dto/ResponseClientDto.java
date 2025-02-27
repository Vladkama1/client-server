package aston.lab.clientserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseClientDto {
    private Long inn;
    private Long ogrn;
    private String formOwnershipId;
    private String fullNameClient;
    private String nameClient;
    private UUID okvedId;
    private UUID businessVolId;
    private String curAccId;
    private String addressLegal;
    private UUID clientRepresentativId;
    private List<String> telNumber;
    private UUID managerId;
    private Double revenue;
}
