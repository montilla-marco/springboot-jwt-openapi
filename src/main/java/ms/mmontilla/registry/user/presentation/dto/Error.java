package ms.mmontilla.registry.user.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class Error {
    @JsonProperty("message")
    @NotNull
    private String message;

    public Error(String message) {
        this.message = message;
    }

    @Schema(required = true, description = "")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}