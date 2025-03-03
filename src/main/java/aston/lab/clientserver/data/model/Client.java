package aston.lab.clientserver.data.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NamedEntityGraph(
        name = "Client.fullInfo",
        attributeNodes = {
                @NamedAttributeNode("formOwnershipId"),
                @NamedAttributeNode("businessVolId"),
                @NamedAttributeNode("okvedId"),
                @NamedAttributeNode("clientRepresentativId")
        }
)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    @Column(name = "ogrn", length = 15, nullable = false)
    private String ogrn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_ownership_id", referencedColumnName = "id", nullable = false)
    private FormOwnership formOwnershipId;

    @Column(name = "full_name_client")
    private String fullNameClient;

    @Column(name = "name_client")
    private String nameClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "okved_id", referencedColumnName = "id", nullable = false)
    private Okved okvedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_vol_id", referencedColumnName = "id", nullable = false)
    private BusinessVolume businessVolId;

    @Column(name = "cur_acc_id", length = 20, nullable = false)
    private String curAccId;

    @Column(name = "address_legal")
    private String addressLegal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_representativ_id", referencedColumnName = "id", nullable = false)
    private ClientRepresentativ clientRepresentativId;

    @Column(name = "manager_id", nullable = false)
    private UUID managerId;

    @Column(name = "revenue")
    private Float revenue;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "employee_id", nullable = false)
    private UUID employeeId;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "clientId", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<TelNumber> telNumbers = new ArrayList<>();

}
