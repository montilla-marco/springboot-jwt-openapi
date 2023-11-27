package ms.mmontilla.registry.user.repository.datasource.jpa.repository;

import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepo extends JpaRepository<UserEntity, String> {
}
