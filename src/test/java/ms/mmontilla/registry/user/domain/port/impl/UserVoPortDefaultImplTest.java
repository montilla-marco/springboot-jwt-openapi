package ms.mmontilla.registry.user.domain.port.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.utils.UsersFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserVoPortDefaultImplTest {

    @InjectMocks
    private UserPortDefaultImpl userPort;

    @Mock
    private UserAdapter adapter;

    @Test
    void givenUser_whenSave_thenReturnUserCreated() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
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
}