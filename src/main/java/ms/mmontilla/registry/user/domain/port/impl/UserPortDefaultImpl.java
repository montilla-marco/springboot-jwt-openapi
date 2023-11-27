package ms.mmontilla.registry.user.domain.port.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.port.UserPort;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import org.springframework.stereotype.Service;

@Service
public class UserPortDefaultImpl implements UserPort {

    private final UserAdapter userAdapter;

    public UserPortDefaultImpl(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public UserVo save(UserVo userVo) {
        //TODO make password validation through Regexp with property configuration
        return userAdapter.save(userVo);
    }
}
