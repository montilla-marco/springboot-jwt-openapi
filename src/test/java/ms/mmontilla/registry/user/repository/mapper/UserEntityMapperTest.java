package ms.mmontilla.registry.user.repository.mapper;

import ms.mmontilla.registry.user.domain.vo.PhoneVo;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.model.PersonEntity;
import ms.mmontilla.registry.user.repository.datasource.model.PhoneEntity;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import ms.mmontilla.registry.user.utils.UsersFactories;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserEntityMapperTest {

    @Test
    void givenEmptyUserVo_whenMap_thenReturnEmptyEntity() {
        //arrange
        UserVo userVo = new UserVo();
        userVo.setPhones(UsersFactories.getDefaultPhonesVo());
        UserEntityMapper mapper = new UserEntityMapper();

        //act
        UserEntity userEntity = mapper.map(userVo);

        //assert
        assertThat(userEntity).isNotNull();
        assertThat(userEntity).isInstanceOf(UserEntity.class);
    }

    @Test
    void givenFullFilledUserVo_whenMap_thenReturnFullFilledEntity() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        UserEntityMapper mapper = new UserEntityMapper();

        //act
        UserEntity userEntity = mapper.map(userVo);

        //assert
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getId()).isEqualTo(userVo.getId());
        assertThat(userEntity.getAccessToken()).isEqualTo(userVo.getAccessToken());
        assertThat(userEntity.getEmail()).isEqualTo(userVo.getEmail());
        assertThat(userEntity.getPassword()).isEqualTo(userVo.getPassword());
        PersonEntity userEntityPerson = userEntity.getPerson();
        assertThat(userEntityPerson.getName()).isEqualTo(userVo.getName());
        PhoneEntity phoneSaved = userEntityPerson.getPhones().get(0);
        PhoneVo phone = userVo.getPhones().get(0);
        assertThat(phoneSaved.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneSaved.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneSaved.getNumber()).isEqualTo(phone.getNumber());
    }

    @Test
    void givenEmptyUseEntity_whenMap_thenReturnEmptyVo() {
        //arrange
        UserEntity userEntity = new UserEntity();
        PersonEntity person = new PersonEntity(null, null, UsersFactories.getDefaultPhoneEntities());
        userEntity.setPerson(person);
        UserEntityMapper mapper = new UserEntityMapper();

        //act
        UserVo userVo = mapper.map(userEntity);

        //assert
        assertThat(userVo).isNotNull();
        assertThat(userVo).isInstanceOf(UserVo.class);
    }

    @Test
    void givenFullFilledUserEntity_whenMap_thenReturnFullFilledVo() {
        //arrange
        UserEntity userEntity = UsersFactories.getDefaultUserEntity();
        UserEntityMapper mapper = new UserEntityMapper();

        //act
        UserVo userVo = mapper.map(userEntity);

        //assert
        assertThat(userVo).isNotNull();
        assertThat(userVo.getId()).isEqualTo(userEntity.getId());
        assertThat(userVo.getAccessToken()).isEqualTo(userEntity.getAccessToken());
        assertThat(userVo.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(userVo.getPassword()).isEqualTo(userEntity.getPassword());
        PersonEntity userEntityPerson = userEntity.getPerson();
        assertThat(userVo.getName()).isEqualTo(userEntityPerson.getName());
        PhoneVo phoneSaved = userVo.getPhones().get(0);
        PhoneEntity phone = userEntityPerson.getPhones().get(0);
        assertThat(phoneSaved.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneSaved.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneSaved.getNumber()).isEqualTo(phone.getNumber());
    }
}