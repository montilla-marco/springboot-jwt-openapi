package ms.mmontilla.registry.user.presentation.mapper;

import ms.mmontilla.registry.user.domain.vo.PhoneVo;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.utils.UsersFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserMapperTest {

    private UserMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new UserMapper();
    }

    @Test
    void giverUserIn_whenMap_thenReturnUserVo() {
        // arrange
        UserIn userIn = new UserIn();
        userIn.setPhones(UsersFactories.getDefaultPhones());

        // act
        UserVo userVo = mapper.map(userIn);

        //assert
        assertThat(userVo).isNotNull();
        assertThat(userVo).isInstanceOf(UserVo.class);
    }

    @Test
    void giverValidUserIn_whenMap_thenValidReturnUserVo() {
        // arrange
        UserIn userIn = new UserOut();
        userIn.setName("Juan Rodriguez");
        userIn.setEmail("juan@rodriguez.org");
        userIn.setPassword("hunter2");
        userIn.setPhones(UsersFactories.getDefaultPhones());

        // act
        UserVo userVo = mapper.map(userIn);

        //assert
        assertThat(userVo.getName()).isEqualTo(userIn.getName());
        assertThat(userVo.getEmail()).isEqualTo(userIn.getEmail());
        assertThat(userVo.getPassword()).isEqualTo(userIn.getPassword());
        PhoneVo phoneOut = userVo.getPhones().stream().findFirst().get();
        Phone phone = userIn.getPhones().get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phone.getNumber());
    }

    @Test
    void giverUser_whenMap_thenReturnUserOut() {
        // arrange
        UserVo userVo = new UserVo();
        userVo.setPhones(UsersFactories.getDefaultPhonesVo());

        // act
        UserOut userOut = mapper.map(userVo);

        //assert
        assertThat(userOut).isNotNull();
        assertThat(userOut).isInstanceOf(UserOut.class);
    }

    @Test
    void giverValidUser_whenMap_thenValidReturnUserOut() {
        // arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        userVo.setPhones(UsersFactories.getDefaultPhonesVo());

        // act
        UserOut userOut = mapper.map(userVo);

        //assert
        assertThat(userOut.getId()).isEqualTo(userVo.getId());
        assertThat(userOut.getName()).isEqualTo(userVo.getName());
        assertThat(userOut.getEmail()).isEqualTo(userVo.getEmail());
        assertThat(userOut.getPassword()).isEqualTo(userVo.getPassword());
        assertThat(userOut.getToken()).isEqualTo(userVo.getAccessToken());
        assertThat(userOut.isActive()).isEqualTo(userVo.isActive());
        Phone phoneOut = userOut.getPhones().get(0);
        PhoneVo phone = userVo.getPhones().get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phone.getNumber());

    }
}