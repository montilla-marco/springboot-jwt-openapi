package ms.mmontilla.registry.user.presentation.impl;

import ms.mmontilla.registry.user.presentation.ApiController;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.presentation.service.UserService;
import ms.mmontilla.registry.user.presentation.utils.UsersFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ApiControllerTest {

    @InjectMocks
    private ApiController controller;

    @Mock
    private UserService service;

    @Test
    void givenGoodUser_whenSave_thenReturnsDifferentUserOut() throws Exception {
        // arrange
        UserOut user = UsersFactories.getDefaultUserOut();
        Phone phoneIn = user.getPhones().get(0);
        Mockito.when(service.createUser((UserIn) user)).thenReturn(user);

        // act
        ResponseEntity<UserOut> responseEntity = controller.createUser((UserIn) user);
        UserOut userOut = responseEntity.getBody();

        //assert
        assertThat(userOut).isNotNull();
        assertThat(userOut.getId()).isEqualTo(user.getId());
        assertThat(userOut.getName()).isEqualTo(user.getName());
        assertThat(userOut.getEmail()).isEqualTo(user.getEmail());
        assertThat(userOut.getPassword()).isEqualTo(user.getPassword());
        assertThat(userOut.getToken()).isEqualTo(user.getToken());
        assertThat(userOut.isActive()).isEqualTo(user.isActive());
        List<Phone> phonesOut = userOut.getPhones();
        Phone phoneOut = phonesOut.get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phoneIn.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phoneIn.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phoneIn.getNumber());
    }
}

