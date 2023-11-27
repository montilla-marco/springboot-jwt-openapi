package ms.mmontilla.registry.user.repository.mapper;

import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.model.PersonEntity;
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
    void givenFullFilledEmptyUserVo_whenMap_thenReturnFullFilledEntity() {
        //arrange
        UserVo userVo = UsersFactories.getDefaultUserVo();
        UserEntityMapper mapper = new UserEntityMapper();

        //act
        UserEntity userEntity = mapper.map(userVo);

        //assert
        assertThat(userEntity).isNotNull();
        assertThat(userEntity.getId()).isEqualTo(userVo.getId());
        assertThat(userEntity.getId()).isNotNull();
        assertThat(userEntity.getAccessToken()).isEqualTo(userVo.getAccessToken());
        assertThat(userEntity.getAccessToken()).isNotNull();
        //TODO ends the test
    }

    @Test
    void givenEmptyUseEntityr_whenMap_thenReturnEmptyVo() {
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

    //TODO make complete test for fullfilled case
}