package ms.mmontilla.registry.user.presentation.mapper;

import ms.mmontilla.registry.user.domain.vo.User;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.presentation.utils.JwtUtils;
import ms.mmontilla.registry.user.presentation.utils.UsersFactories;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserMapperTest {

    @Test
    void giverUserIn_whenMap_thenReturnUserVo() {
        // arrange
        UserMapper mapper = new UserMapper();
        UserIn userIn = new UserIn();

        // act
        User user = mapper.map(userIn);

        //assert
        assertThat(user).isNotNull();
        assertThat(user).isInstanceOf(User.class);
    }

    @Test
    void giverValidUserIn_whenMap_thenValidReturnUserVo() {
        // arrange
        UserMapper mapper = new UserMapper();
        UserIn userIn = new UserOut();
        userIn.setName("Juan Rodriguez");
        userIn.setEmail("juan@rodriguez.org");
        userIn.setPassword("hunter2");
        Phone phone = UsersFactories.getDefaultPhones().get(0);
        userIn.setPhones(UsersFactories.getDefaultPhones());

        // act
        User user = mapper.map(userIn);

        //assert
        assertThat(user.getName()).isEqualTo(userIn.getName());
        assertThat(user.getEmail()).isEqualTo(userIn.getEmail());
        assertThat(user.getPassword()).isEqualTo(userIn.getPassword());
        List<Phone> phonesOut = user.getPhones();
        Phone phoneOut = phonesOut.get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phone.getNumber());
    }

    @Test
    void giverUser_whenMap_thenReturnUserOut() {
        // arrange
        UserMapper mapper = new UserMapper();
        User user = new User();

        // act
        UserOut userOut = mapper.map(user);

        //assert
        assertThat(userOut).isNotNull();
        assertThat(userOut).isInstanceOf(UserOut.class);
    }

    @Test
    void giverValidUser_whenMap_thenValidReturnUserOut() {
        // arrange
        UserMapper mapper = new UserMapper();
        User user = UsersFactories.getDefaultUser();
        Phone phone = UsersFactories.getDefaultPhones().get(0);

        // act
        UserOut userOut = mapper.map(user);

        //assert
        assertThat(userOut.getId()).isEqualTo(user.getId());
        assertThat(userOut.getName()).isEqualTo(user.getName());
        assertThat(userOut.getEmail()).isEqualTo(user.getEmail());
        assertThat(userOut.getPassword()).isEqualTo(user.getPassword());
        assertThat(userOut.getToken()).isEqualTo(user.getAccessToken());
        assertThat(userOut.isActive()).isEqualTo(user.isActive());
        List<Phone> phonesOut = userOut.getPhones();
        Phone phoneOut = phonesOut.get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phone.getNumber());

    }
}