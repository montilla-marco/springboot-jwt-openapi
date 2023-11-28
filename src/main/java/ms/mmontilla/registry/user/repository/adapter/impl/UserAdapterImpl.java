package ms.mmontilla.registry.user.repository.adapter.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.DataSource;
import ms.mmontilla.registry.user.repository.datasource.model.PersonEntity;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import ms.mmontilla.registry.user.repository.mapper.UserEntityMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public class UserAdapterImpl implements UserAdapter {

    private final DataSource dataSource;

    private final UserEntityMapper mapper;

    @Value("${app.jwt.generator.key:}")
    private String jwtSecret;

    public UserAdapterImpl(DataSource dataSource, UserEntityMapper mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    @Override
    public UserVo save(UserVo userVo) {
        UserEntity entity = mapper.map(userVo);
        entity.setId(UUID.randomUUID());
        entity.setAccessToken(getAccessToken(entity.getEmail()));
        entity.isActive(true);
        PersonEntity person = new PersonEntity(UUID.randomUUID(), userVo.getName(), entity.getPerson().getPhones());
        entity.setPerson(person);

        entity = dataSource.save(entity);
        return mapper.map(entity);
    }

    private String getAccessToken(String userName) {
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }
}
