package ms.mmontilla.registry.user.presentation.mapper;

import ms.mmontilla.registry.user.domain.vo.PhoneVo;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

//TODO Complete this mapper using MapStruct
@Component
public class UserMapper {

    public UserVo map(UserIn userIn) {
        UserVo userVo = new UserVo();
        userVo.setName(userIn.getName());
        userVo.setEmail(userIn.getEmail());
        userVo.setPassword(userIn.getPassword());
        List<PhoneVo> phones = userIn.getPhones()
                .stream()
                .map(phone -> new PhoneVo(phone.getNumber(), phone.getCityCode(), phone.getCountryCode()))
                .collect(toList());
        ;
        userVo.setPhones(phones);
        return userVo;
    }

    public UserOut map(UserVo userVo) {
        UserOut userOut = new UserOut();
        userOut.setName(userVo.getName());
        userOut.setEmail(userVo.getEmail());
        userOut.setPassword(userVo.getPassword());
        userOut.setId(userVo.getId());
        userOut.setToken(userVo.getAccessToken());
        userOut.isActive(userVo.isActive());
        List<Phone> phones = userVo.getPhones()
                .stream()
                .map(phoneVo -> new Phone(phoneVo.getNumber(), phoneVo.getCityCode(), phoneVo.getCountryCode()))
                .collect(toList());
        userOut.setPhones(phones);
        return userOut;
    }

}
