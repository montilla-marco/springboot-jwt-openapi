package ms.mmontilla.registry.user.repository.datasource.jpa.repository;

import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.model.PersonEntity;
import ms.mmontilla.registry.user.repository.datasource.model.PhoneEntity;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import ms.mmontilla.registry.user.repository.mapper.UserEntityMapper;
import ms.mmontilla.registry.user.utils.UsersFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class UserEntityRepoTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserEntityRepo repository;

    private final static String NULL_UUID = null;


    @Test
    void givenNullId_whenSave_thenThrowInvalidDataAccessApiUsageException() {
        // arrange
        String expectedMessage = "The given id must not be null!";

        // assert
        Exception exception = assertThrows(InvalidDataAccessApiUsageException.class, () -> {
            // act
            Optional<UserEntity> found = repository.findById(NULL_UUID);
        });

        // assert
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void givenEmptyEntity_whenSave_thenThrowInvalidDataAccessApiUsageException() {
        // arrange
        String expectedMessage = "ids for this class must be manually assigned before calling save";
        UserEntity entity = new UserEntity();

        // assert
        Exception exception = assertThrows(org.springframework.orm.jpa.JpaSystemException.class, () -> {
            // act
            repository.save(entity);
        });

        // assert
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void givenFullFilled_whenSave_thenReturnFullFilled() {
        //arrange
        UserVo user = UsersFactories.getDefaultUserVo();
        PersonEntity person = new PersonEntity(UUID.randomUUID(), user.getName(), UsersFactories.getDefaultPhoneEntities());

        UserEntityMapper mapper = new UserEntityMapper();
        UserEntity userEntity = mapper.map(user);
        userEntity.setPerson(person);

        //act
        UserEntity savedUser = repository.save(userEntity);

        //assert
        assertThat(savedUser).isNotNull();
    }

    @Test
    void givenCreatedUser_whenSave_thenReturnFullFilledEntity() {
        //arrange
        UserVo user = UsersFactories.getDefaultUserVo();
        PersonEntity person =
                new PersonEntity(UUID.randomUUID(), user.getName(), UsersFactories.getDefaultPhoneEntities());
        UserEntityMapper mapper = new UserEntityMapper();
        UserEntity userEntity = mapper.map(user);
        userEntity.setPerson(person);

        //act
        repository.save(userEntity);
        UserEntity savedUser = repository.findByEmail(user.getEmail());

        //assert
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getPerson().getName()).isEqualTo(userEntity.getPerson().getName());
        assertThat(savedUser.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(savedUser.getPassword()).isEqualTo(userEntity.getPassword());
        PhoneEntity phoneOut = savedUser.getPerson().getPhones().stream().findFirst().get();
        PhoneEntity phone = userEntity.getPerson().getPhones().get(0);
        assertThat(phoneOut.getCityCode()).isEqualTo(phone.getCityCode());
        assertThat(phoneOut.getCountryCode()).isEqualTo(phone.getCountryCode());
        assertThat(phoneOut.getNumber()).isEqualTo(phone.getNumber());

        assertThat(savedUser.getCreated()).isEqualTo(userEntity.getCreated());
        assertThat(savedUser.getModified()).isEqualTo(userEntity.getModified());
        assertThat(savedUser.getLastLogin()).isEqualTo(userEntity.getLastLogin());
        assertThat(savedUser.isActive()).isEqualTo(userEntity.isActive());
    }

}