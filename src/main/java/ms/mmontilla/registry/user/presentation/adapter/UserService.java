package ms.mmontilla.registry.user.presentation.adapter;

import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;

public interface UserService {

    UserOut createUser(UserIn user);
}
