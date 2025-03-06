package aston.lab.clientserver.utility;

import aston.lab.clientserver.data.model.*;
import aston.lab.clientserver.dto.RequestClientDto;
import aston.lab.clientserver.dto.ResponseClientDto;
import aston.lab.clientserver.dto.request.ClientRequestDto;
import aston.lab.clientserver.dto.response.ClientResponseDto;
import aston.lab.clientserver.dto.response.FormOwnershipDto;
import aston.lab.clientserver.dto.responsedto.ClientFindByInnAndOgrnResponseDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static UUID clientId = UUID.randomUUID();;
    public static ClientRequestDto clientRequestDto = new ClientRequestDto();
    public static String userId = "testUser";
    public static String userRole = "ADMIN";
    public static Client client;
    public static ClientResponseDto clientResponseDto;

    public TestUtils() {
        setUp();
    }

    void setUp() {
        clientRequestDto.setNameClient("Test Client");
        clientRequestDto.setInn("1234567890");

        // Создаем зависимые объекты для Client
        FormOwnership formOwnership = new FormOwnership();
        formOwnership.setId(UUID.randomUUID());
        formOwnership.setName("ООО");
        formOwnership.setFullName("OOO Test");

        Okved okved = new Okved();
        okved.setId(UUID.randomUUID());
        okved.setCode("12.34");
        okved.setName("Name");

        BusinessVolume businessVolume = new BusinessVolume();
        businessVolume.setId(UUID.randomUUID());
        businessVolume.setCategory("Малый бизнес");

        ClientRepresentativ clientRepresentativ = new ClientRepresentativ();
        clientRepresentativ.setId(UUID.randomUUID());
        clientRepresentativ.setFullName("John Smith");
        clientRepresentativ.setJobTitle("John");
        clientRepresentativ.setEmail("john.smith@gmail.com");

        List<TelNumber> telNumbers = new ArrayList<>();
        TelNumber telNumber = new TelNumber();
        telNumber.setId(UUID.randomUUID());
        telNumber.setNumber("+88005553535");
        telNumbers.add(telNumber);

        // Создаем Client с заполненными данными
        client = Client.builder()
                .id(clientId)
                .inn("9876543210")
                .ogrn("1122334455")
                .formOwnershipId(formOwnership)
                .fullNameClient("Test Client Full Name")
                .nameClient("Old Test Client")
                .okvedId(okved)
                .businessVolId(businessVolume)
                .curAccId("123456789101112")
                .addressLegal("Test Address")
                .clientRepresentativId(clientRepresentativ)
                .managerId(UUID.randomUUID())
                .revenue(1000000.0f)
                .startDate(LocalDateTime.now().minusDays(30))
                .updatedAt(LocalDateTime.now())
                .employeeId(UUID.randomUUID())
                .endDate(null)
                .isActive(true)
                .telNumbers(telNumbers)
                .build();

        clientResponseDto = new ClientResponseDto(); // Initialize with necessary data
        clientResponseDto.setId(clientId);
        clientResponseDto.setNameClient("Test Client");
        clientResponseDto.setInn("1234567890");
    }

    public static List<FormOwnershipDto> formOwnershipDtoList =
            List.of(
                    new FormOwnershipDto("b2b145c9-95d6-4dbc-938c-1f4fea2f5922",
                            "Общество с ограниченной ответственностью",
                            "ООО"),
                    new FormOwnershipDto("e6c88dac-7b91-4f21-87ea-a55b3bee15f3",
                            "Индивидуальный предприниматель",
                            "ИП"),
                    new FormOwnershipDto("6d35eac8-a289-44df-b1c1-4cbd65687bd8",
                            "Акционерное общество",
                            "АО")
            );
    public static List<FormOwnership> formOwnershipList =
            List.of(
                    new FormOwnership(UUID.randomUUID(),
                            "Общество с ограниченной ответственностью",
                            "ООО"),
                    new FormOwnership(UUID.randomUUID(),
                            "Индивидуальный предприниматель",
                            "ИП"),
                    new FormOwnership(UUID.randomUUID(),
                            "Акционерное общество",
                            "АО")
            );


    public static RequestClientDto requestClientDto = RequestClientDto.builder()
            .inn("6321323773")
            .ogrn("11363200L")
            .formOwnershipId("123e4567-e89b-12d3-a456-426614174001")
            .fullNameClient("ИнструментТекстиль")
            .nameClient("ООО ИТекстиль")
            .okvedId("123e4567-e89b-12d3-a456-426614174020")
            .businessVolId("123e4567-e89b-12d3-a456-426614174003")
            .curAccId("55084002941471644000")
            .addressLegal("Самарская область, г. Тольятти")
            .clientRepresentativId("123e4567-e89b-12d3-a456-426614174004")
            .telNumber(List.of("12345678912", "73536402326"))
            .managerId("123e4567-e89b-12d3-a456-426614174005")
            .revenue(77.03)
            .build();

    public static ResponseClientDto responseClientDto = ResponseClientDto.builder()
            .inn("6321323773")
            .ogrn("11363200L")
            .formOwnershipId("123e4567-e89b-12d3-a456-426614174001")
            .fullNameClient("ИнструментТекстиль")
            .nameClient("ООО ИТекстиль")
            .okvedId(UUID.fromString("123e4567-e89b-12d3-a456-426614174020"))
            .businessVolId(UUID.fromString("123e4567-e89b-12d3-a456-426614174003"))
            .curAccId("55084002941471644000")
            .addressLegal("Самарская область, г. Тольятти")
            .clientRepresentativId(UUID.fromString("123e4567-e89b-12d3-a456-426614174004"))
            .telNumber(List.of("12345678912", "73536402326"))
            .managerId(UUID.fromString("123e4567-e89b-12d3-a456-426614174005"))
            .revenue(77.03)
            .build();

    public static ClientFindByInnAndOgrnResponseDto responseDto = ClientFindByInnAndOgrnResponseDto.builder()
            .clientRepresentativId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .addressLegal("445047, Самарская область, г. Тольятти, Тополиная ул.,влд 1а, ком.53")
            .fullNameClient("Общество с ограниченной ответственностью ИнструментТекстиль")
            .nameClient("ООО ИнструментТекстиль")
            .businessVolId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .formOwnershipId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .okvedId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .curAccId("40702810000000000001")
            .revenue(50.5f)
            .telNumber(List.of("+12345678912", "+98765432198"))
            .managerId(UUID.fromString("f40b55ab-f9ce-488b-8d36-3615b2599487"))
            .isActive(true)
            .inn("6321323773")
            .ogrn("11363200L")
            .build();

    public static ClientFindByInnAndOgrnResponseDto responsesDto = ClientFindByInnAndOgrnResponseDto.builder()
            .fullNameClient("Общество с ограниченной ответственностью ИнструментТекстиль")
            .nameClient("ООО ИнструментТекстиль")
            .inn("6321323773")
            .ogrn("11363200L")
            .build();

//    public static Client client = Client.builder()
//            .nameClient("ООО ИнструментТекстиль")
//            .fullNameClient("Общество с ограниченной ответственностью ИнструментТекстиль")
//            .inn("6321323773")
//            .ogrn("11363200L")
//            .build();
}
