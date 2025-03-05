package aston.lab.clientserver.util;

import aston.lab.clientserver.data.model.FormOwnership;
import aston.lab.clientserver.dto.response.FormOwnershipDto;

import java.util.List;
import java.util.UUID;

public class TestUtils {
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


}
