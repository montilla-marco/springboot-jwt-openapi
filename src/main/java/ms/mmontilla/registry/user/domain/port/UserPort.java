package ms.mmontilla.registry.user.domain.port;

import ms.mmontilla.registry.user.domain.vo.User;

public interface UserPort {
    User save(User user);
}
