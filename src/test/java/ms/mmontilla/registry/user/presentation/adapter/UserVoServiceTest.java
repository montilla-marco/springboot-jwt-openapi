package ms.mmontilla.registry.user.presentation.adapter;

import ms.mmontilla.registry.user.domain.port.UserPort;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.presentation.adapter.impl.UserServiceImpl;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.presentation.mapper.UserMapper;
import ms.mmontilla.registry.user.utils.UsersFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserVoServiceTest {

    @InjectMocks
    private UserServiceImpl service;

    @Mock
    private UserMapper mapper;

    @Mock
    private UserPort userPort;

    @Test
    void givenUserIn_whenCreateUser_thenReturnUserOutInstance() {
        // arrange
        UserIn userIn = new UserIn();
        when(mapper.map(any(UserIn.class))).thenReturn(new UserVo());
        when(userPort.save(any(UserVo.class))).thenReturn(new UserVo());
        when(mapper.map(any(UserVo.class))).thenReturn(new UserOut());

        // act
        UserOut userOut = service.createUser(userIn);

        //assert
        assertThat(userOut).isNotNull();
        assertThat(userOut).isInstanceOf(UserOut.class);
    }

    @Test
    void givenValidUserIn_whenCreateUser_thenReturnUserOutInstance() {
        // arrange
        UserOut userOut = UsersFactories.getDefaultUserOut();
        Phone phoneIn = userOut.getPhones().get(0);

        when(mapper.map(any(UserIn.class))).thenReturn(new UserVo());
        when(userPort.save(any(UserVo.class))).thenReturn(new UserVo());
        when(mapper.map(any(UserVo.class))).thenReturn(userOut);

        // act
        UserOut userResp = service.createUser(new UserIn());

        //assert
        assertThat(userResp.getId()).isEqualTo(userOut.getId());
        assertThat(userResp.getName()).isEqualTo(userOut.getName());
        assertThat(userResp.getEmail()).isEqualTo(userOut.getEmail());
        assertThat(userResp.getPassword()).isEqualTo(userOut.getPassword());
        assertThat(userResp.getToken()).isEqualTo(userOut.getToken());
        assertThat(userResp.isActive()).isEqualTo(userOut.isActive());
        List<Phone> phonesOut = userResp.getPhones();
        Phone phoneOut = phonesOut.get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phoneIn.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phoneIn.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phoneIn.getNumber());
    }
}
