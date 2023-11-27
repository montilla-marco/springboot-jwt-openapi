package ms.mmontilla.registry.user.repository.adapter.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.vo.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserAdapterImpl implements UserAdapter {
    @Override
    public User save(User user) {
        return null;
    }
}
