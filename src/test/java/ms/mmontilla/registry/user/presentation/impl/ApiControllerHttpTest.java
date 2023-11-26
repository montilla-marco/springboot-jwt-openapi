package ms.mmontilla.registry.user.presentation.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import ms.mmontilla.registry.user.presentation.ApiController;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.presentation.service.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApiController.class)
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void givenGoodUser_whenSave_thenReturnsUserOut() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marcomarco");
        user.setEmail("marcomarco@marcomarco.blog");
        user.setPassword("secret");
        ArrayList<Phone> phones = new ArrayList<>();
        Phone phone = new Phone();
        phone.setNumber(92324567);
        phone.setCountryCode(56);
        phone.setCityCode(2);
        user.setPhones(phones);

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.isactive").value(true));
    }

    @Test
    void givenGoodUser_whenSave_thenReturnsDifferentUserOut() throws Exception {
        // arrange
        UserOut user = new UserOut();
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        ArrayList<Phone> phones = new ArrayList<>();
        Phone phone = new Phone();
        phone.setNumber(1234567);
        phone.setCountryCode(57);
        phone.setCityCode(1);
        user.setPhones(phones);
        user.setId(UUID.randomUUID());
        user.setToken(getAccessToken("juan@rodriguez.org"));
        user.isActive(true);

        UserService userService = Mockito.mock(UserService.class);
        Mockito.when(userService.createUser((UserIn) user)).thenReturn(user);

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString((UserIn) user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.password").value(user.getPassword()))
                .andExpect(jsonPath("$.isactive").value(true));
    }

    @Test
    void givenEmptyUser_whenSave_thenThrowsBadRequest() throws Exception {
        // arrange
        UserIn user = new UserIn();
        String expected = "{\"message\":\"El nombre es un campo obligatorio\"}";

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    void givenNoEmail_whenSave_thenThrowsBadRequest() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marco");
        String expected = "{\"message\":\"El correo electronico es un campo obligatorio\"}";

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    void givenBadEmailFormat_whenSave_thenThrowsBadRequest() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marco");
        user.setEmail("XXX");
        String expected = "{\"message\":\"El formato del correo electronico es invalido\"}";

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    void givenNoPassword_whenSave_thenThrowsBadRequest() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marcomarco");
        user.setEmail("marcomarco@marcomarco.blog");
        String expected = "{\"message\":\"La clave es un campo obligatorio\"}";

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    void givenNoPhones_whenSave_thenThrowsBadRequest() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marcomarco");
        user.setEmail("marcomarco@marcomarco.blog");
        user.setPassword("secret");
        String expected = "{\"message\":\"Al menos un numero telefonico debe ser ingresado\"}";

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(Matchers.notNullValue()));
    }

    @Test
    void givenBadUrl_whenSave_thenThrowsNotFound() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marcomarco");
        user.setEmail("marcomarco@marcomarco.blog");
        user.setPassword("secret");
        user.setPhones(new ArrayList<>());
        String expected = "{\"message\":\"No existe contenido en el recurso solicitado\"}";

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users/one")
                        .content("{}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(Matchers.containsString(expected)));
    }

    @Test
    void givenInternalServerError_whenSave_thenThrowsNotFound() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marcomarco");
        user.setEmail("marcomarco@marcomarco.blog");
        user.setPassword("secret");
        user.setPhones(new ArrayList<>());
        String expected = "{\"message\":\"Ocurrio un inconveniente procesando la solicitud\"}";

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string(Matchers.containsString(expected)));
    }

    private String getAccessToken(String userName) {
        Algorithm algorithm = Algorithm.HMAC256("secret-jwt".getBytes());
        return JWT.create().withSubject(userName).withExpiresAt(new Date(System.currentTimeMillis())).toString();
    }
}

