package ms.mmontilla.registry.user.presentation.mapper;

import ms.mmontilla.registry.user.domain.vo.User;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import org.springframework.stereotype.Component;

import java.util.UUID;

//TODO Complete this mapper using MapStruct
@Component
public class UserMapper {

    public User map(UserIn userIn) {
        User user = new User();
        user.setName(userIn.getName());
        user.setEmail(userIn.getEmail());
        user.setPassword(userIn.getPassword());
        user.setPhones(userIn.getPhones());
        return user;
    }

    public UserOut map(User user) {
        UserOut userOut = new UserOut();
        userOut.setName(user.getName());
        userOut.setEmail(user.getEmail());
        userOut.setPassword(user.getPassword());
        userOut.setId(user.getId());
        userOut.setToken(user.getAccessToken());
        userOut.isActive(user.isActive());
        userOut.setPhones(user.getPhones());
        return userOut;
    }

}
