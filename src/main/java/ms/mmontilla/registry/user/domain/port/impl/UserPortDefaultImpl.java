package ms.mmontilla.registry.user.domain.port.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.port.UserPort;
import ms.mmontilla.registry.user.domain.vo.User;
import org.springframework.stereotype.Service;

@Service
public class UserPortDefaultImpl implements UserPort {

    private final UserAdapter userAdapter;

    public UserPortDefaultImpl(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public User save(User user) {
        return userAdapter.save(user);
    }
}
