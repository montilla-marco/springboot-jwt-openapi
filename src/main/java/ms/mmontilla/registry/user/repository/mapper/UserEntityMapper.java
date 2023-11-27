package ms.mmontilla.registry.user.repository.mapper;

import ms.mmontilla.registry.user.domain.vo.PhoneVo;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import ms.mmontilla.registry.user.repository.datasource.model.PersonEntity;
import ms.mmontilla.registry.user.repository.datasource.model.PhoneEntity;
import ms.mmontilla.registry.user.repository.datasource.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityMapper {

    public UserEntity map(UserVo user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setCreated(user.getCreated());
        entity.setModified(user.getModified());
        entity.setLastLogin(user.getLastLogin());
        entity.setAccessToken(user.getAccessToken());
        entity.isActive(user.isActive());
        List<PhoneEntity> phones =
                user.getPhones()
                        .stream()
                        .map(phone -> new PhoneEntity(phone.getNumber(), phone.getCityCode(), phone.getCountryCode()))
                        .collect(Collectors.toList());
        PersonEntity person = new PersonEntity(user.getId(), user.getName(), phones);
        entity.setPerson(person);
        return entity;
    }

    public UserVo map(UserEntity user) {
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setName(user.getPerson().getName());
        userVo.setEmail(user.getEmail());
        userVo.setPassword(user.getPassword());
        userVo.setCreated(user.getCreated());
        userVo.setModified(user.getModified());
        userVo.setLastLogin(user.getLastLogin());
        userVo.setAccessToken(user.getAccessToken());
        userVo.isActive(user.isActive());
        List<PhoneVo> phones =
                user.getPerson()
                        .getPhones()
                        .stream()
                        .map(phone -> new PhoneVo(phone.getNumber(), phone.getCityCode(), phone.getCountryCode()))
                        .collect(Collectors.toList());
        return userVo;
    }
}
