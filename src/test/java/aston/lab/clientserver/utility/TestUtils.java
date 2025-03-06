package aston.lab.clientserver.utility;

import aston.lab.clientserver.data.model.*;
import aston.lab.clientserver.dto.request.ClientRequestDto;
import aston.lab.clientserver.dto.response.ClientResponseDto;

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
}
