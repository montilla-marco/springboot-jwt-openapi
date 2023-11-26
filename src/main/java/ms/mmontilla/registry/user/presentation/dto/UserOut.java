package ms.mmontilla.registry.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserOut extends UserIn {
    @JsonProperty("id")
    private UUID id = null;

    @JsonProperty("created")
    private LocalDateTime created = null;

    @JsonProperty("modified")
    private LocalDateTime modified = null;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin = null;

    @JsonProperty("token")
    private String token = null;

    @JsonProperty("isactive")
    private Boolean isActive = null;

    @Schema(description = "")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    @Schema(description = "")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Schema(description = "")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Schema(description = "")
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Schema(description = "")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserOut isActive(Boolean isactive) {
        this.isActive = isactive;
        return this;
    }

    @Schema(description = "")
    public Boolean isActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

}

