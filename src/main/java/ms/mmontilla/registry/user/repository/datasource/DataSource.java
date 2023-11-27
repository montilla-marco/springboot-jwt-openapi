package ms.mmontilla.registry.user.repository.datasource;

import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;

public interface DataSource {

    public UserEntity save(UserEntity user);
}
