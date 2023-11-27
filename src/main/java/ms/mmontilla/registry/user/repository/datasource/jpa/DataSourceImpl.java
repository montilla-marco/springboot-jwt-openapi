package ms.mmontilla.registry.user.repository.datasource.jpa;

import ms.mmontilla.registry.user.repository.datasource.DataSource;
import ms.mmontilla.registry.user.repository.datasource.jpa.repository.UserEntityRepo;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import ms.mmontilla.registry.user.repository.exception.DataSourceTierException;
import ms.mmontilla.registry.user.repository.exception.UserAlreadyExistsException;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class DataSourceImpl implements DataSource {

    Logger logger = Logger.getLogger(DataSourceImpl.class.getName());

    private UserEntityRepo repo;

    public DataSourceImpl(UserEntityRepo repo) {
        this.repo = repo;
    }

    @Override
    public UserEntity save(UserEntity user) {
        try {
            if (repo.findByEmail(user.getEmail()) == null) {
                return repo.save(user);
            }
        } catch (Exception e) {
            logger.severe("ERROR IN DATASOURCE TIER: " + e.getMessage());
            throw new DataSourceTierException(e.getMessage());
        }
        logger.severe("ERROR IN DATASOURCE TIER: User Already exist: " + user.getEmail());
        throw new UserAlreadyExistsException("User Already exist");
    }
}
