package ms.mmontilla.registry.user.repository.adapter.impl;

import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.DataSource;
import ms.mmontilla.registry.user.repository.mapper.UserEntityMapper;
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
class UserVoAdapterImplTest {

    @InjectMocks
    private UserAdapterImpl adapter;

    @Mock
    private DataSource dataSource;

    @Mock
    private UserEntityMapper mapper;

    @Test
    void givenUser_whenSave_thenReturnUserCreated() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        UserVo dummiUserVo = UsersFactories.getDefaultUserVo();

        when(adapter.save(any(UserVo.class))).thenReturn(dummiUserVo);

        //act
        UserVo userVoCreated = adapter.save(userVo);

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
        UserVo userVoCreated = adapter.save(userVo);

        //assert
        assertThat(userVoCreated).isNotNull();
        assertThat(userVoCreated.getId()).isEqualTo(dummiUserVo.getId());
        assertThat(userVoCreated.getAccessToken()).isEqualTo(dummiUserVo.getAccessToken());
        assertThat(userVoCreated.isActive()).isEqualTo(dummiUserVo.isActive());
        //TODO ends test
    }
}