package ms.mmontilla.registry.user.domain.port.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.vo.User;
import ms.mmontilla.registry.user.presentation.utils.UsersFactories;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserPortDefaultImplTest {

    @InjectMocks
    private UserPortDefaultImpl userPort;

    @Mock
    private UserAdapter adapter;

    @Test
    void givenUser_whenSave_thenReturnUserCreated() {
        //arrange
        User user = UsersFactories.getDefaultUser();
        when(adapter.save(any(User.class))).thenReturn(new User());

        //act
        User userCreated = userPort.save(user);

        //assert
        assertThat(userCreated).isNotNull();
        assertThat(userCreated).isInstanceOf(User.class);
    }

    @Test
    void givenUserWithUserInEntries_whenSave_thenReturnUserFullCreated() {
        //arrange
        User user = UsersFactories.getDefaultUser();
        User dummiUser = UsersFactories.getDefaultUser();
        when(adapter.save(any(User.class))).thenReturn(dummiUser);

        //act
        User userCreated = userPort.save(user);

        //assert
        assertThat(userCreated).isNotNull();
        assertThat(userCreated.getId()).isEqualTo(dummiUser.getId());
        assertThat(userCreated.getAccessToken()).isEqualTo(dummiUser.getAccessToken());
        assertThat(userCreated.isActive()).isEqualTo(dummiUser.isActive());
    }
}