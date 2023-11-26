package ms.mmontilla.registry.user.presentation.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ms.mmontilla.registry.user.presentation.ApiController;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.service.UserService;
import org.apache.commons.lang3.StringEscapeUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
class ApiControllerHttpTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void givenBadUrl_whenSave_thenThrowsNotFound() throws Exception {
        // arrange
        UserIn user = new UserIn();
        user.setName("marcomarco");
        user.setEmail("marcomarco@marcomarco.blog");
        user.setPassword("secret");
        user.setPhones(new ArrayList<>());
        String expected = "{\"message\":\"No existe contenido en el recurso solicitado\"}";
        when(service.createUser(any(UserIn.class))).thenThrow(new NullPointerException());

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
        //TODO review how to eliminate scape character for this test
//        String expected =
//                StringEscapeUtils.unescapeJava("{\"message\":\"Ocurrio un inconveniente procesando la solicitud\"}");

        // act and assert
        this.mockMvc.perform(post("/presentation/v1/users")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
//                .andExpect(content().string(Matchers.containsString(expected)));
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
}


