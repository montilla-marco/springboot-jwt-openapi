package ms.mmontilla.registry.user.utils;

import ms.mmontilla.registry.user.domain.vo.PhoneVo;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.repository.datasource.model.PersonEntity;
import ms.mmontilla.registry.user.repository.datasource.model.PhoneEntity;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

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

    public static List<PhoneVo> getDefaultPhonesVo() {
        return getDefaultPhones().stream()
                .map(phone -> new PhoneVo(phone.getNumber(), phone.getCityCode(), phone.getCountryCode()))
                .collect(toList());
    }

    public static List<PhoneEntity> getDefaultPhoneEntities() {
        return getDefaultPhones().stream()
                .map(phone -> new PhoneEntity(phone.getNumber(), phone.getCityCode(), phone.getCountryCode()))
                .collect(toList());
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

    public static UserVo getDefaultUserVo() {
        UserVo userVo = new UserVo();
        userVo.setName("Juan Rodriguez");
        userVo.setEmail("juan@rodriguez.org");
        userVo.setPassword("hunter2");
        userVo.setPhones(getDefaultPhonesVo());
        UUID uuid = UUID.randomUUID();
        userVo.setId(uuid);
        String accessToken = JwtUtils.getAccessToken("juan@rodriguez.org");
        userVo.setAccessToken(accessToken);
        userVo.isActive(true);
        return userVo;
    }

    public static UserEntity getDefaultUserEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("juan@rodriguez.org");
        userEntity.setPassword("hunter2");
        PersonEntity person = new PersonEntity(UUID.randomUUID(), "Juan Rodriguez", getDefaultPhoneEntities());
        userEntity.setPerson(person);
        UUID uuid = UUID.randomUUID();
        userEntity.setId(uuid);
        String accessToken = JwtUtils.getAccessToken("juan@rodriguez.org");
        userEntity.setAccessToken(accessToken);
        userEntity.isActive(true);
        return userEntity;
    }
}
