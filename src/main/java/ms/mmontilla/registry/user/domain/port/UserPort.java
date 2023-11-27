package ms.mmontilla.registry.user.domain.port;

import ms.mmontilla.registry.user.domain.vo.UserVo;

public interface UserPort {
    UserVo save(UserVo userVo);
}
