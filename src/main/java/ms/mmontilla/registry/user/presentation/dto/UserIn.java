package ms.mmontilla.registry.user.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class UserIn {
    @JsonProperty("name")
    @NotNull(message = "El nombre es un campo obligatorio")
    @Size(min = 5, max = 200)
    private String name;

    @JsonProperty("email")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",
            message = "El formato del correo electronico es invalido")
    private String email;

    @JsonProperty("password")
    @NotNull(message = "La clave es un campo obligatorio")
    private String password;

    @JsonProperty("phones")
    @NotNull(message = "Al menos un numero telefonico debe ser ingresado")
    @Valid
    private List<Phone> phones;

    @Schema(description = "")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Schema(required = true, description = "")
    @NotNull(message = "El correo electronico es un campo obligatorio")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Schema(required = true, description = "")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Schema(required = true, description = "")
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(@Valid List<Phone> phones) {
        this.phones = phones;
    }

}

