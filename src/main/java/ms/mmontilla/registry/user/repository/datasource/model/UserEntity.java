package ms.mmontilla.registry.user.repository.datasource.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserEntity {

    private UUID id = null;

    private String accessToken = null;

    private String email;

    private String password;

    private LocalDateTime created = null;

    private LocalDateTime modified = null;

    private LocalDateTime lastLogin = null;

    private Boolean isActive = null;

    private PersonEntity person;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void isActive(Boolean isactive) {
        this.isActive = isactive;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
}
