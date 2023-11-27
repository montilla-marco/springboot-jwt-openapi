package ms.mmontilla.registry.user.repository.datasource.jpa.repository;


import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.model.PersonEntity;
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

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserEntityRepoTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserEntityRepo repository;

    private final static String NULL_UUID = null;


    @Test
    void givenNullId_whenFindById_thenThrownInvalidDataAccessApiUsageException() {
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

    //TODO more related test like above

    @Test
    void givenFullFilled_whenSave_thenReturnFullFilled() {
        UserVo user = UsersFactories.getDefaultUserVo();
        PersonEntity person = new PersonEntity(UUID.randomUUID(), user.getName(), UsersFactories.getDefaultPhoneEntities());

        UserEntityMapper mapper = new UserEntityMapper();
        UserEntity userEntity = mapper.map(user);
        userEntity.setPerson(person);

        UserEntity savedUser = repository.save(userEntity);

    }
}