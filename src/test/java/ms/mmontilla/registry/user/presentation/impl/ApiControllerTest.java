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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    @InjectMocks
    private ApiController controller;

    @Mock
    private UserService service;

    @Test
    void givenGoodUser_whenSave_thenReturnsDifferentUserOut() throws Exception {
        // arrange
        UserOut user = new UserOut();
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        List<Phone> phones = new ArrayList<>();
        Phone phone = new Phone();
        phone.setNumber(1234567);
        phone.setCountryCode(57);
        phone.setCityCode(1);
        phones.add(phone);
        user.setPhones(phones);
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        String accessToken = getAccessToken("juan@rodriguez.org");
        user.setToken(accessToken);
        user.isActive(true);
        Mockito.when(service.createUser((UserIn) user)).thenReturn(user);

        // act
        ResponseEntity<UserOut> responseEntity = controller.createUser((UserIn) user);
        UserOut userOut = responseEntity.getBody();

        //assert
        assertThat(userOut).isNotNull();
        assertThat(userOut.getId()).isEqualTo(uuid);
        assertThat(userOut.getName()).isEqualTo(user.getName());
        assertThat(userOut.getEmail()).isEqualTo(user.getEmail());
        assertThat(userOut.getPassword()).isEqualTo(user.getPassword());
        assertThat(userOut.getToken()).isEqualTo(user.getToken());
        assertThat(userOut.isActive()).isEqualTo(user.isActive());
        List<Phone> phonesOut = user.getPhones();
        Phone phoneOut = phonesOut.get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phone.getNumber());
    }

    private String getAccessToken(String userName) {
        Algorithm algorithm = Algorithm.HMAC256("secret-jwt".getBytes());
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }
}

