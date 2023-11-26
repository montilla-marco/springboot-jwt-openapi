package ms.mmontilla.registry.user.presentation.service.impl;

import ms.mmontilla.registry.user.domain.port.UserPort;
import ms.mmontilla.registry.user.domain.vo.User;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.presentation.mapper.UserMapper;
import ms.mmontilla.registry.user.presentation.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;

    private final UserPort userPort;

    public UserServiceImpl(UserMapper mapper, UserPort userPort) {
        this.mapper = mapper;
        this.userPort = userPort;
    }

    @Override
    public UserOut createUser(UserIn userIn) {
        User user = mapper.map(userIn);
        User userSaved = userPort.save(user);
        UserOut userOut = mapper.map(userSaved);
        return userOut;
    }
}
