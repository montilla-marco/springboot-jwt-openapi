package ms.mmontilla.registry.user.repository.adapter.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.DataSource;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import ms.mmontilla.registry.user.repository.mapper.UserEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserAdapterImpl implements UserAdapter {

    private final DataSource dataSource;

    private final UserEntityMapper mapper;

    public UserAdapterImpl(DataSource dataSource, UserEntityMapper mapper) {
        this.dataSource = dataSource;
        this.mapper = mapper;
    }

    @Override
    public UserVo save(UserVo userVo) {
        UserEntity entity = mapper.map(userVo);
        entity = dataSource.save(entity);
        return mapper.map(entity);
    }
}
