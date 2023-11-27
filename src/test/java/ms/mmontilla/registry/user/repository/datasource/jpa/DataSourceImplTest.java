package ms.mmontilla.registry.user.repository.datasource.jpa;


import ms.mmontilla.registry.user.repository.datasource.jpa.repository.UserEntityRepo;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import ms.mmontilla.registry.user.repository.exception.DataSourceTierException;
import ms.mmontilla.registry.user.repository.exception.UserAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.orm.jpa.JpaSystemException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataSourceImplTest {

    @InjectMocks
    private DataSourceImpl dataSource;

    @Mock
    private UserEntityRepo repository;

    @Test
    void givenEmptyUserEntity_whenSave_then() {
        //arrange
        UserEntity user = new UserEntity();
        String expectedMessage = "The given entity must not be null!";
        when(repository.save(any(UserEntity.class)))
                .thenThrow(new JpaSystemException(new RuntimeException("The given entity must not be null!")));

        // assert
        Exception exception = assertThrows(DataSourceTierException.class, () -> {
            // act
            dataSource.save(user);
        });

        // assert
        assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void givenFullFilledUserEntity_whenSave_then() {
        //arrange
        UserEntity user = new UserEntity();
        when(repository.save(any(UserEntity.class))).thenReturn(user);

        // act
        UserEntity saved = dataSource.save(user);

        // assert
        assertThat(saved).isEqualTo(user);
    }

    @Test
    void givenCreatedUserEntity_whenSave_then() {
        //arrange
        UserEntity user = new UserEntity();
        user.setEmail("marcomarco@marcomarco.blog");
        when(repository.findByEmail(any(String.class))).thenReturn(user);

        // assert
        Exception exception = assertThrows(UserAlreadyExistsException.class, () -> {
            // act
            dataSource.save(user);
        });

        // assert
        assertTrue(exception.getMessage().contains("User Already exist"));
    }
}