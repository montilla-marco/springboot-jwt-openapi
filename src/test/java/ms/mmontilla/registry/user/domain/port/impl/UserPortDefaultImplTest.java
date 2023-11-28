package ms.mmontilla.registry.user.domain.port.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.exception.InvalidPasswordFormatException;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.utils.UsersFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserPortDefaultImplTest {

    private static final String password = "MarcoMarco2020";

    @InjectMocks
    private UserPortDefaultImpl userPort;

    @Mock
    private UserAdapter adapter;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(userPort,
                "passwordRegexp",
                "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}$");
    }

    @Test
    void givenUser_whenSave_thenReturnUserCreated() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        userVo.setPassword(password);
        userVo.setPassword(password);
        when(adapter.save(any(UserVo.class))).thenReturn(new UserVo());

        //act
        UserVo userVoCreated = userPort.save(userVo);

        //assert
        assertThat(userVoCreated).isNotNull();
        assertThat(userVoCreated).isInstanceOf(UserVo.class);
    }

    @Test
    void givenUserWithUserInEntries_whenSave_thenReturnUserFullCreated() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        userVo.setPassword(password);
        UserVo dummiUserVo = UsersFactories.getDefaultUserVo();
        when(adapter.save(any(UserVo.class))).thenReturn(dummiUserVo);

        //act
        UserVo userVoCreated = userPort.save(userVo);

        //assert
        assertThat(userVoCreated).isNotNull();
        assertThat(userVoCreated.getId()).isEqualTo(dummiUserVo.getId());
        assertThat(userVoCreated.getAccessToken()).isEqualTo(dummiUserVo.getAccessToken());
        assertThat(userVoCreated.isActive()).isEqualTo(dummiUserVo.isActive());
    }

    @Test
    void givenUserWithInvalidPassword_whenSave_thenThrowsInvalidPasswordFormatException() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        userVo.setPassword("Xxx");

        // assert
        Exception exception = assertThrows(InvalidPasswordFormatException.class, () -> {
            // act
            userPort.save(userVo);
        });

        // assert
        assertTrue(exception.getMessage().contains("Invalid Password Format"));
    }

    @Test
    void givenUserWithValidPassword_whenSave_then() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        userVo.setPassword(password);
        when(adapter.save(any(UserVo.class))).thenReturn(userVo);

        // act
        UserVo saved = userPort.save(userVo);

        // assert
        assertThat(saved).isEqualTo(userVo);
    }
}