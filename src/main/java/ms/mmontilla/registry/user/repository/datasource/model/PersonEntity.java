package ms.mmontilla.registry.user.repository.datasource.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "persons")
public class PersonEntity {

    @Id
    private UUID id = null;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy="person")
    private List<PhoneEntity> phones;

    public PersonEntity() {
    }

    public PersonEntity(UUID id, String name, List<PhoneEntity> phones) {
        this.id = id;
        this.name = name;
        this.phones = phones;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhoneEntity> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneEntity> phones) {
        this.phones = phones;
    }
}
