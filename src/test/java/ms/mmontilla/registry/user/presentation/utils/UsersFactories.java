package ms.mmontilla.registry.user.presentation.utils;

import ms.mmontilla.registry.user.domain.vo.User;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsersFactories {

    public static UserIn getDefaultUserIn() {
        UserIn user = new UserIn();
        user.setName("marcomarco");
        user.setEmail("marcomarco@marcomarco.blog");
        user.setPassword("secret");
        user.setPhones(new ArrayList<>());
        return user;
    }

    public static List<Phone> getDefaultPhones() {
        Phone phone = new Phone();
        phone.setNumber(1234567);
        phone.setCountryCode(57);
        phone.setCityCode(1);

        List<Phone> phones = new ArrayList<>();
        phones.add(phone);
        return phones;
    }

    public static UserOut getDefaultUserOut() {
        UserOut user = new UserOut();
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        user.setPhones(getDefaultPhones());
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        String accessToken = JwtUtils.getAccessToken("juan@rodriguez.org");
        user.setToken(accessToken);
        user.isActive(true);
        return user;
    }

    public static User getDefaultUser() {
        User user = new User();
        user.setName("Juan Rodriguez");
        user.setEmail("juan@rodriguez.org");
        user.setPassword("hunter2");
        user.setPhones(getDefaultPhones());
        UUID uuid = UUID.randomUUID();
        user.setId(uuid);
        String accessToken = JwtUtils.getAccessToken("juan@rodriguez.org");
        user.setAccessToken(accessToken);
        user.isActive(true);
        return user;
    }
}
