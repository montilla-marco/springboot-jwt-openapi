package ms.mmontilla.registry.user.repository.datasource.model;

import java.util.List;
import java.util.UUID;

public class PersonEntity {

    private UUID id = null;

    private String name;

    private List<PhoneEntity> phones;

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
