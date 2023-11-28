package ms.mmontilla.registry.user.repository.adapter.impl;

import ms.mmontilla.registry.user.domain.vo.PhoneVo;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.DataSource;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
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
class UserAdapterImplTest {

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
        userVo.setPhones(UsersFactories.getDefaultPhonesVo());
        UserEntity userEntity = UsersFactories.getDefaultUserEntity();

        when(mapper.map(any(UserVo.class))).thenReturn(userEntity);
        when(dataSource.save(any(UserEntity.class))).thenReturn(userEntity);
        when(mapper.map(any(UserEntity.class))).thenReturn(userVo);

        //act
        UserVo voCreated = adapter.save(userVo);

        //assert
        assertThat(voCreated).isNotNull();
        assertThat(voCreated).isInstanceOf(UserVo.class);
    }

    @Test
    void givenUserWithUserInEntries_whenSave_thenReturnUserFullCreated() {
        UserVo userVo = UsersFactories.getDefaultUserVo();
        userVo.setPhones(UsersFactories.getDefaultPhonesVo());
        UserEntity userEntity = UsersFactories.getDefaultUserEntity();

        when(mapper.map(any(UserVo.class))).thenReturn(userEntity);
        when(dataSource.save(any(UserEntity.class))).thenReturn(userEntity);
        when(mapper.map(any(UserEntity.class))).thenReturn(userVo);

        //act
        UserVo voCreated = adapter.save(userVo);

        //assert
        assertThat(voCreated).isNotNull();
        assertThat(voCreated.getName()).isEqualTo(userVo.getName());
        assertThat(voCreated.getEmail()).isEqualTo(userVo.getEmail());
        assertThat(voCreated.getPassword()).isEqualTo(userVo.getPassword());
        PhoneVo phoneSaved = voCreated.getPhones().get(0);
        PhoneVo phone = userVo.getPhones().get(0);
        assertThat(phoneSaved.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneSaved.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneSaved.getNumber()).isEqualTo(phone.getNumber());
        assertThat(voCreated.getId()).isEqualTo(userVo.getId());
        assertThat(voCreated.getAccessToken()).isEqualTo(userVo.getAccessToken());
        assertThat(voCreated.isActive()).isEqualTo(userVo.isActive());
    }
}