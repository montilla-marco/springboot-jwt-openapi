package ms.mmontilla.registry.user.repository.datasource.jpa;

import ms.mmontilla.registry.user.repository.datasource.DataSource;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DataSourceImpl implements DataSource {
    @Override
    public UserEntity save(UserEntity user) {
        return null;
    }
}
