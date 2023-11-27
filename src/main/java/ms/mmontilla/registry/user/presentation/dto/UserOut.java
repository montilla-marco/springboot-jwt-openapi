package ms.mmontilla.registry.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserOut extends UserIn {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("created")
    private LocalDateTime created;

    @JsonProperty("modified")
    private LocalDateTime modified;

    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    @JsonProperty("token")
    private String token;

    @JsonProperty("isActive")
    private Boolean isActive;

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

    @Schema(description = "")
    public Boolean isActive() {
        return isActive;
    }

    public void isActive(Boolean isActive) {
        this.isActive = isActive;
    }

}

