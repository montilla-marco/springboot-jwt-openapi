package ms.mmontilla.registry.user.presentation.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ms.mmontilla.registry.user.presentation.ApiController;
import ms.mmontilla.registry.user.presentation.adapter.UserService;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.utils.UsersFactories;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerHttpTest {

    public static final String USER_NAME = "marcomarco";
    public static final String USER_EMAIL = "marcomarco@marcomarco.blog";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void givenBadUrl_whenSave_thenThrowsNotFound() throws Exception {
        // arrange
        UserIn user = UsersFactories.getDefaultUserIn();
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
        UserIn user = UsersFactories.getDefaultUserIn();
        when(service.createUser(any(UserIn.class))).thenThrow(new NullPointerException());
        //TODO review how to eliminate scape character for this test

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
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
        user.setName(USER_NAME);
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
        user.setName(USER_NAME);
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
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
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
        user.setName(USER_NAME);
        user.setEmail(USER_EMAIL);
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

    //TODO make some test for @Valid, at this instance, doesn't work
}


