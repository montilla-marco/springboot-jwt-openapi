package ms.mmontilla.registry.user.domain.port.impl;

import ms.mmontilla.registry.user.domain.adapter.UserAdapter;
import ms.mmontilla.registry.user.domain.exception.InvalidPasswordFormatException;
import ms.mmontilla.registry.user.domain.port.UserPort;
import ms.mmontilla.registry.user.domain.vo.UserVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserPortDefaultImpl implements UserPort {

    private final UserAdapter userAdapter;

    @Value("${app.user.validations.regexp.password}")
    private String passWordRegexp;


    public UserPortDefaultImpl(UserAdapter userAdapter) {
        this.userAdapter = userAdapter;
    }

    @Override
    public UserVo save(UserVo userVo) {
        validatePasswordFormat(userVo.getPassword());
        return userAdapter.save(userVo);
    }

    private void validatePasswordFormat(String password) {

        Pattern pattern = Pattern.compile(passWordRegexp);
        Matcher matcher = pattern.matcher("password");
        if(!matcher.find()) {
            throw new InvalidPasswordFormatException("Invalid Password Format");
        }
    }
}
