package ms.mmontilla.registry.user.repository.datasource.jpa;

import ms.mmontilla.registry.user.repository.datasource.DataSource;
import ms.mmontilla.registry.user.repository.datasource.jpa.repository.UserEntityRepo;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class DataSourceImpl implements DataSource {

    private UserEntityRepo repo;

    public DataSourceImpl(UserEntityRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserEntity save(UserEntity user) {
        //TODO make validation if the user already exist!!!
        return repo.save(user);
    }
}
